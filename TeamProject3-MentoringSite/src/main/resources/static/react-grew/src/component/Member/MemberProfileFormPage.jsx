import "../../css/memberPage.css";
import React, { useEffect, useState } from "react";
import { getCookie } from "../../util/cookieUtil";
import { memberProfile, updateAction, sendVerificationCode, verificationInputCode } from "../../api/memberApi";
import * as categoryApi from "../../api/categoryApi";
import * as responseStatus from "../../api/responseStatusCode";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";


const MemberProfileFormPage = () => {
  const navigate = useNavigate();
  const memberCookie = getCookie("member");
  const token = memberCookie.accessToken;

  /* 관심사 */
  const [interests, setInterests] = useState([]); // 관심사 데이터
  const [selectedInterests, setSelectedInterests] = useState([]); // 선택된 관심사
  const [interestMessage, setInterestMessage] = useState(""); // 조건부 메시지 상태

  /* 이메일 인증 관련 상태 */
  const [originalEmail, setOriginalEmail] = useState("");
  const [isEmailChanged, setIsEmailChanged] = useState(false);
  const [isEmailVerified, setIsEmailVerified] = useState(false); // 이메일 인증 성공 여부
  const [verificationCode, setVerificationCode] = useState("");
  const [sentCode, setSentCode] = useState(false); // 인증번호 전송 여부

  /* 멤버 start */
  const [member, setMember] = useState({
    memberNo: "",
    memberName: "",
    memberId: "",
    memberEmail: "",
    memberPassword: "",
    memberPassword2: "",
    interests: [],
  });
  /* 멤버 end */

  /* 이메일 변경 핸들러 */
  const handleEmailChange = (e) => {
    const newEmail = e.target.value;

    setMember((prevMember) => ({
      ...prevMember,
      memberEmail: newEmail,
    }));

    setIsEmailChanged(newEmail !== originalEmail); // 이메일 변경 여부 확인
    setIsEmailVerified(false); // 이메일 변경 시 인증 완료 상태 초기화
  };
  
   /* 인증번호 요청 핸들러 */
   const handleSendVerificationCode = async () => {
     alert("인증번호가 이메일로 전송되었습니다.");
    try {
      await sendVerificationCode(member.memberEmail); // 서버에 이메일 전송 요청
      setSentCode(true); // 인증번호 전송 여부 업데이트
    } catch (error) {
      console.log("인증번호 에러 : ", error);
    }
  };

  /* 인증번호 검증 핸들러 */
  const handleVerifyCode = async () => {
    try {
      const response = await verificationInputCode(member.memberEmail, verificationCode); // 서버에 인증번호 검증 요청
      switch(response.status)  {
        case responseStatus.INPUTCODE_CONFIRM_FAIL:
          toast.error("인증번호가 올바르지 않습니다.");
          break;
        case responseStatus.MEMBER_PROVIDER_IS_NOT_EMAIL:
          toast.error("SNS사용자는 이메일을 수정할 수 없습니다.")
          break;
        case responseStatus.INPUTCODE_CONFIRM_SUCCESS:
          toast.success("이메일이 인증되었습니다.");
          setIsEmailVerified(true); // 인증 완료 상태 업데이트
          break;
        default:
          toast.error("default에러가 발생하였습니다.");
          break;
      }
    } catch (error) {
        toast.error("catch에러가 발생하였습니다.");
        console.log("catch에러 : ", error);
    }
  };

  // 기본 관심사 필터링 및 메시지 설정
  const filterInterests = (data) => {
    const filtered = data.interests.filter(
      (interest) => ![19, 20, 21].includes(interest.categoryNo)
    );

    // 관심사가 [19, 20, 21]에만 해당하면 메시지 설정
    if (filtered.length === 0 && data.interests.length > 0) {
      setInterestMessage("3개의 관심사를 선택해주세요.");
    } else {
      setInterestMessage("");
    }

    return filtered;
  };

  /***** 사용자 정보 가져오기 *****/
  const fetchProfileData = async () => {
    const response = await memberProfile(token);

    const { data } = response;

    const filteredInterests = filterInterests(data); // 필터링 및 메시지 설정

    setMember({
      memberNo: data.memberNo,
      memberName: data.memberName,
      memberId: data.memberId,
      memberEmail: data.memberEmail,
      memberPassword: data.memberPassword,
      memberPassword2: data.memberPassword,
      interests: filteredInterests || [],
    });

    //기존 이메일을 setOriginalEmail에 설정
    setOriginalEmail(data.memberEmail);
    // 기존 관심사를 selectedInterests에 설정
    setSelectedInterests(filteredInterests.map((interest) => interest.categoryNo));
  };

  // 관심사 클릭 이벤트 핸들러
  const handleInterestClick = (categoryNo) => {
    const updatedSelectedInterests = selectedInterests.includes(categoryNo)
      ? selectedInterests.filter((interest) => interest !== categoryNo)
      : [...selectedInterests, categoryNo];

    setSelectedInterests(updatedSelectedInterests);

    // member.interests 동기화
    setMember((prevMember) => ({
      ...prevMember,
      interests: updatedSelectedInterests.map((no) => ({ categoryNo: no })),
    }));
  };

  /***** 수정폼 핸들러 *****/
  const onChangeMemberModifyForm = (e) => {
    setMember({
      ...member,
      [e.target.name]: e.target.value,
    });
  };

  /***** 업데이트 액션 *****/
  const updateMember = () => {
    console.log("업데이트 시 멤버 : ", member);

    if (member.memberPassword !== member.memberPassword2) {
      alert("비밀번호를 다시 확인해주세요.");
      return;
    }

    if (selectedInterests.length !== 3) {
      alert("3개의 관심사를 선택해주세요.");
      return;
    }

    // 관심사를 업데이트 전에 동기화
    const updatedMember = {
      ...member,
      interests: selectedInterests.map((categoryNo) => ({ categoryNo })),
    };

    updateAction(updatedMember, token).then((responseJsonObject) => {
      console.log("Server response:", responseJsonObject);
      switch (responseJsonObject.status) {
        case responseStatus.UPDATE_MEMBER_SUCCESS:
          navigate("/member/profile");
          alert("회원 정보가 수정되었습니다.");
          break;
        case responseStatus.AUTHENTICATION_FAILED:
          navigate("/main");
          alert("잘못된 사용자입니다.");
          break;
        case responseStatus.UPDATE_MEMBER_FAIL:
          navigate("/main");
          alert("회원 정보 수정이 정상적으로 이루어지지 않았습니다.");
          break;
        default:
          alert("알 수 없는 오류가 발생했습니다.");
      }
    });
  };

  useEffect(() => {
    async function fetchInterests() {
      try {
        const response = await categoryApi.ListCategory();
        const data = response.data;

        // categoryDepth가 2인 항목만 필터링
        const filteredInterests = data.filter(
          (category) => category.categoryDepth === 2
        );

        setInterests(filteredInterests);
      } catch (error) {
        console.error("관심사 데이터를 가져오는 중 오류 발생:", error);
      }
    }

    fetchInterests();
    fetchProfileData();
  }, []);

  return (
    <div className="profile-content-layout">
      {/* 오른쪽 프로필 수정 폼 */}
        <h2 className="profile-title">개인 정보 관리</h2>
        <form className="profile-form">
          <div className="profile-form-group">
            <p className="profile-form-label">
              이름<span className="red-star">*</span>
            </p>
            <input
              type="text"
              name="memberName"
              placeholder="이름을 입력하세요"
              value={member.memberName || ""}
              onChange={onChangeMemberModifyForm}
              required
            />
          </div>

          <div className="profile-form-group">
            <p className="profile-form-label">
              아이디<span className="red-star">*</span>
            </p>
            <input
              type="text"
              name="memberId"
              value={member.memberId || ""}
              readOnly
              disabled
            />
          </div>

          <div className="profile-form-group">
            <p className="profile-form-label">
              비밀번호<span className="red-star">*</span>
            </p>
            <input
              type="password"
              name="memberPassword"
              placeholder="비밀번호 입력"
              onChange={onChangeMemberModifyForm}
            />
          </div>

          <div className="profile-form-group">
            <p className="profile-form-label">
              비밀번호 확인<span className="red-star">*</span>
            </p>
            <input
              type="password"
              name="memberPassword2"
              placeholder="비밀번호 확인"
              onChange={onChangeMemberModifyForm}
            />
          </div>

          <div className="profile-form-group">
            <p className="profile-form-label">
              이메일<span className="red-star">*</span>
            </p>
            <div className="email-verification-wrapper">
              <input
                type="email"
                name="memberEmail"
                placeholder="이메일을 입력하세요"
                value={member.memberEmail || ""}
                onChange={handleEmailChange}
                required
              />
              <button
                type="button"
                className="email-verification-button"
                onClick={handleSendVerificationCode}
                disabled={!isEmailChanged || sentCode}
              >
                이메일 인증
              </button>
            </div>
          </div>

          {sentCode && (
              <div className="profile-form-group">
                <div className="email-verification-wrapper">
                <input
                  type="text"
                  placeholder="인증번호를 입력하세요"
                  value={verificationCode}
                  onChange={(e) => setVerificationCode(e.target.value)} // 상태와 연결
                />
                <button
                  type="button"
                  className="email-verification-button"
                  onClick={handleVerifyCode}
                  disabled={isEmailVerified}
                >
                  인증번호 확인
                </button>
                </div>
              </div>
            )}
            

          <div className="profile-interest-group">
            <p className="profile-interest-label">
              관심사<span className="red-star">*</span>
            </p>
            {interestMessage && (
              <p className="interest-message">{interestMessage}</p>
            )}
            {interests.length > 0 ? (
              interests.map((interest) => (
                <div
                  key={interest.categoryNo}
                  className={`profile-interest-item ${
                    selectedInterests.includes(interest.categoryNo)
                      ? "selected"
                      : ""
                  }`}
                  onClick={() => handleInterestClick(interest.categoryNo)}
                >
                  {interest.categoryName}
                </div>
              ))
            ) : (
              <p>관심사를 불러오는 중입니다...</p>
            )}
          </div>

          <div className="profile-button-group">
            <input
              type="button"
              className="profile-modify-button"
              onClick={updateMember}
              value="저장"
              disabled={isEmailChanged && !isEmailVerified} // 조건 추가
            />
            &emsp;
            <input
              type="button"
              className="profile-modify-button"
              onClick={updateMember}
              value="회원 탈퇴"
              disabled={isEmailChanged && !isEmailVerified} // 조건 추가
            />
          </div>
        </form>
    </div>
  );
};

export default MemberProfileFormPage;
