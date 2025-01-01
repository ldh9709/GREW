import "../../css/memberPage.css";
import React, { useEffect, useState } from "react";
import { getCookie } from "../../util/cookieUtil";
import { memberProfile, updateAction } from "../../api/memberApi";
import * as categoryApi from "../../api/categoryApi";
import * as responseStatus from "../../api/responseStatusCode";
import { useNavigate } from "react-router-dom";


const MemberProfileFormPage = () => {
  const navigate = useNavigate();
  const memberCookie = getCookie("member");
  const token = memberCookie.accessToken;

  /* 왼쪽 사이드 바 CSS */
  const [activeTab, setActiveTab] = useState("profile");

  /* 관심사 start */
  const [interests, setInterests] = useState([]); // 관심사 데이터
  const [selectedInterests, setSelectedInterests] = useState([]); // 선택된 관심사
  /* 관심사 end */

  const [member, setMember] = useState({
    memberNo: "",
    memberName: "",
    memberId: "",
    memberEmail: "",
    memberPassword: "",
    memberPassword2: "",
    interests: [],
  });

  /***** 사용자 정보 가져오기 *****/
  const fetchProfileData = async () => {
    const response = await memberProfile(token);
    console.log("프로필response : ", response);

    const { data } = response;

    // 기본 관심사 필터링
    const filteredInterests = data.interests.filter(
      (interest) => ![19, 20, 21].includes(interest.categoryNo) // categoryNo가 19, 20, 21이 아닌 항목만 필터링
    );

    setMember({
      memberNo: data.memberNo,
      memberName: data.memberName,
      memberId: data.memberId,
      memberEmail: data.memberEmail,
      memberPassword: data.memberPassword,
      memberPassword2: data.memberPassword,
      interests: filteredInterests || [],
    });

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
    console.log(e.target.value);
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

  console.log(member.interests);

  return (
    <div className="profile-layout">
      {/* 오른쪽 프로필 수정 폼 */}
      <div className="profile-content">
        <h2 className="profile-title">회원정보 수정</h2>
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
            <input
              type="email"
              name="memberEmail"
              placeholder="이메일을 입력하세요"
              value={member.memberEmail || ""}
              onChange={onChangeMemberModifyForm}
              required
            />
          </div>

          <div className="profile-interest-group">
            <p className="profile-interest-label">
              관심사<span className="red-star">*</span>
            </p>
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
              className="profile-button"
              onClick={updateMember}
              value="수정완료"
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default MemberProfileFormPage;
