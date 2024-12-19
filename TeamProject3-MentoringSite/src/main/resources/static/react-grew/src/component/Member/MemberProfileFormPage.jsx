import React, { useEffect, useState } from "react";
import { getCookie } from "../../util/cookieUtil";
import { memberProfile, updateAction } from "../../api/memberApi";
import * as responseStatus from "../../api/responseStatusCode";
import "../../css/memberPage.css";
import { useNavigate } from "react-router-dom";

const MemberProfileFormPage = () => {
  const navigate = useNavigate();
  const memberCookie = getCookie("member");
  const token = memberCookie.accessToken;

  const [member, setMember] = useState({
    memberNo: "",
    memberName: "",
    memberId: "",
    memberEmail: "",
    interests: [],
  });

  const fetchProfileData = async () => {
    const response = await memberProfile(token);
    console.log("프로필response : ", response);
    console.log("프로필response.data : ", response.data);

    const { data } = response;

    setMember({
      memberNo: data.memberNo,
      memberName: data.memberName,
      memberId: data.memberId,
      memberEmail: data.memberEmail,
      interests: data.interests || [],
    });
  };

  const handleInterestClick = (categoryNo) => {
    setMember((prevState) => {
      const updatedInterests = prevState.interests.map((interest) => {
        if (interest.categoryNo === parseInt(categoryNo)) {
          // 이미 선택된 경우: 선택 해제
          return null;
        }
        return interest;
      }).filter(Boolean); // `null` 값을 필터링하여 제외
  
      // 선택되지 않은 경우 새로운 관심사 추가
      const alreadySelected = prevState.interests.some(
        (interest) => interest.categoryNo === parseInt(categoryNo)
      );
  
      if (!alreadySelected) {
        // 새로운 categoryNo 추가, interestNo는 기존과 동일하게 유지
        const newInterestNo =
          prevState.interests.length > 0
            ? Math.max(...prevState.interests.map((i) => i.interestNo)) + 1
            : 1;
  
        updatedInterests.push({
          interestNo: newInterestNo, // 새 interestNo 생성
          memberNo: prevState.memberNo,
          categoryNo: parseInt(categoryNo),
        });
      }
  
      return { ...prevState, interests: updatedInterests };
    });
  };
  

  const isInterestSelected = (categoryNo) => {
    return member.interests.some(
      (interest) => interest.categoryNo === parseInt(categoryNo)
    );
  };

  const onChangeMemberModifyForm = (e) => {
    setMember({
      ...member,
      [e.target.name]: e.target.value,
    });
    console.log(e.target.value);
  };

  const updateMember = () => {
    console.log("업데이트 시 멤버 : ", member);
    
    updateAction(member, token).then((responseJsonObject) => {
      console.log("Server response:", responseJsonObject);
      switch (responseJsonObject.status) {
        case responseStatus.UPDATE_MEMBER_SUCCESS:
          navigate("/member/profile/edit");
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
    fetchProfileData();
  }, []);

  console.log(member.interests);

  return (
    <div className="member-profile-container">
      <div className="member-profile-form">
        <form>
          <h3 className="member-profile-title">회원정보</h3>

          <div className="member-form-group">
            <label>이름</label>
            <span>{member.memberName}</span>
          </div>

          <div className="member-form-group">
            <label>아이디</label>
            <span>{member.memberId}</span>
          </div>

          <div className="member-form-group">
            <label>비밀번호</label>
            <input
              type="password"
              placeholder="비밀번호 입력"
              name="memberPassword"
              className="member-form-password"
              onChange={onChangeMemberModifyForm}
            />
          </div>

          <div className="member-form-group">
            <label>비밀번호 확인</label>
            <input
              type="password"
              placeholder="비밀번호 확인"
              name="memberPassword"
              className="member-form-password"
              onChange={onChangeMemberModifyForm}
            />
          </div>

          <div className="member-form-group">
            <label>이메일</label>
            <div className="email-group">
              <input
                type="text"
                placeholder="이메일"
                name="memberEmail"
                value={member.memberEmail}
                onChange={onChangeMemberModifyForm}
                className="member-form-email"
              />
            </div>
          </div>

          <div className="member-form-group">
            <label>관심사</label>
            <div className="member-form-interest-group">
              <div
                className={`interest-item ${isInterestSelected("2") ? "selected" : ""}`}
                onClick={() => handleInterestClick("2")}
              >
                인사/총무/노무
              </div>
              <div
                className={`interest-item ${isInterestSelected("3") ? "selected" : ""}`}
                onClick={() => handleInterestClick("3")}
              >
                영업/영업관리
              </div>
              <div
                className={`interest-item ${isInterestSelected("4") ? "selected" : ""}`}
                onClick={() => handleInterestClick("4")}
              >
                IT개발/데이터
              </div>
              <div
                className={`interest-item ${isInterestSelected("6") ? "selected" : ""}`}
                onClick={() => handleInterestClick("6")}
              >
                중학생
              </div>
              <div
                className={`interest-item ${isInterestSelected("7") ? "selected" : ""}`}
                onClick={() => handleInterestClick("7")}
              >
                고등학생
              </div>
              <div
                className={`interest-item ${isInterestSelected("8") ? "selected" : ""}`}
                onClick={() => handleInterestClick("8")}
              >
                대학입시상담
              </div>
              <div
                className={`interest-item ${isInterestSelected("10") ? "selected" : ""}`}
                onClick={() => handleInterestClick("10")}
              >
                음악
              </div>
              <div
                className={`interest-item ${isInterestSelected("11") ? "selected" : ""}`}
                onClick={() => handleInterestClick("11")}
              >
                글쓰기
              </div>
              <div
                className={`interest-item ${isInterestSelected("12") ? "selected" : ""}`}
                onClick={() => handleInterestClick("12")}
              >
                미술
              </div>
              <div
                className={`interest-item ${isInterestSelected("13") ? "selected" : ""}`}
                onClick={() => handleInterestClick("13")}
              >
                사진/영상 제작
              </div>
              <div
                className={`interest-item ${isInterestSelected("14") ? "selected" : ""}`}
                onClick={() => handleInterestClick("14")}
              >
                연기/연극
              </div>
              <div
                className={`interest-item ${isInterestSelected("16") ? "selected" : ""}`}
                onClick={() => handleInterestClick("16")}
              >
                스타트업 아이디어
              </div>
              <div
                className={`interest-item ${isInterestSelected("17") ? "selected" : ""}`}
                onClick={() => handleInterestClick("17")}
              >
                마케팅 전략
              </div>
              <div
                className={`interest-item ${isInterestSelected("18") ? "selected" : ""}`}
                onClick={() => handleInterestClick("18")}
              >
                법률 특허 상담
              </div>
              <div
                className={`interest-item ${isInterestSelected("22") ? "selected" : ""}`}
                onClick={() => handleInterestClick("22")}
              >
                피트니스
              </div>
              <div
                className={`interest-item ${isInterestSelected("23") ? "selected" : ""}`}
                onClick={() => handleInterestClick("23")}
              >
                요가/필라테스
              </div>
              <div
                className={`interest-item ${isInterestSelected("24") ? "selected" : ""}`}
                onClick={() => handleInterestClick("24")}
              >
                재활 운동
              </div>
              <div
                className={`interest-item ${isInterestSelected("25") ? "selected" : ""}`}
                onClick={() => handleInterestClick("25")}
              >
                식단/영양 상담
              </div>
            </div>
          </div>

          <div className="member-button-group">
            <input
              type="button"
              className="member-submit-button"
              onClick={updateMember}
              value="수정완료"
            />
            <button type="button" className="member-delete-button">
              계정탈퇴
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default MemberProfileFormPage;
