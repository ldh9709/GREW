import React, { useEffect, useState } from "react";
import { memberProfile } from "../../api/memberApi";
import "../../css/memberPage.css";

const MemberProfileFormPage = () => {
  const [member, setMember] = useState({
    memberName: "",
    memberId: "",
    memberEmail: "",
    interests: [],
  });

  const fetchProfileData = async () => {
    const response = await memberProfile();
    console.log("프로필response : ", response);
    console.log("프로필response.data : ", response.data);

    const { data } = response;

    setMember({
      memberName: data.memberName,
      memberId: data.memberId,
      memberEmail: data.memberEmail,
      interests: data.interests || [],
    });
    
  };

  // 관심사를 클릭하여 선택하거나 해제
  const handleInterestClick = (interestId) => {
    setMember((prevState) => {
      const alreadySelected = prevState.interests.some(
        (interest) => interest.categoryNo === parseInt(interestId)
      );

      const updatedInterests = alreadySelected
        ? prevState.interests.filter(
            (interest) => interest.categoryNo !== parseInt(interestId)
          )
        : [
            ...prevState.interests,
            { interestNo: 0, memberNo: prevState.memberId, categoryNo: parseInt(interestId) },
          ];

      return { ...prevState, interests: updatedInterests };
    });
  };

  const isInterestSelected = (interestId) => {
    return member.interests.some((interest) => interest.categoryNo === parseInt(interestId));
  };

  const onChangeMemberModifyForm = (e) => {
    setMember({
      ...member,
      [e.target.name]:e.target.value
    })
    console.log(e.target.value);
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
              className="member-form-password"
              onChange={onChangeMemberModifyForm}
            />
          </div>

          <div className="member-form-group">
            <label>비밀번호 확인</label>
            <input
              type="password"
              placeholder="비밀번호 확인"
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
                className="member-form-email"
                defaultValue={member.memberEmail}
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
            <button type="submit" className="member-submit-button">
              수정완료
            </button>
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
