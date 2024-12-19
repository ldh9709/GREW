import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import * as memberApi from "../../api/memberApi";
import * as responseStatus from "../../api/responseStatusCode";
import "../../css/memberPage.css"

export const MemberJoinFormPage = () => {
  const navigate = useNavigate();

  const [member, setMember] = useState({
    memberId: "",
    memberPassword: "",
    memberEmail: "",
    memberName: "",
    interests: [],
  });

  const [tempCode, setTempCode] = useState("");

  // 입력 필드 업데이트 핸들러
  const handleChangeJoinForm = (e) => {
    setMember({ ...member, [e.target.name]: e.target.value });
  };

  // 체크박스 변경 핸들러
  const handleChangeCheckBox = (e) => {
    const { value, checked } = e.target;
    setMember((prevState) => {
      const updatedInterests = checked
        ? [...prevState.interests, { categoryNo: parseInt(value, 10) }]
        : prevState.interests.filter(
            (interest) => interest.categoryNo !== parseInt(value, 10)
          );
      return { ...prevState, interests: updatedInterests };
    });
  };

  // 인증번호 발송
  const sendJoinCode = async () => {
    const response = await memberApi.sendJoinCode(member.memberEmail);
    setTempCode(response.data);
  };

  // 회원가입 액션
  const MemberJoinAction = async () => {
    if (!member.memberId) {
      alert("아이디를 입력하세요.");
      return;
    }

    const responseJsonObject = await memberApi.joinAction(member, tempCode);

    switch (responseJsonObject.status) {
      case responseStatus.CREATED_MEMBER_SUCCESS:
        navigate("/main");
        break;
      case responseStatus.CREATED_MEMBER_FAIL:
        alert("가입 실패");
        break;
      default:
        alert("에러 발생");
        break;
    }
  };

  return (
    <div className="member-signup-container">
    <h2 className="member-signup-title">회원가입</h2>
    <h3 className="member-signup-subtitle">멘티 가입을 환영합니다!</h3>
    <form className="member-signup-form">
        <div className="member-form-join-group">
        <p className="member-form-join-p">
        아이디<span className="red-star">*</span>
        </p>
            <input
                type="text"
                name="memberId"
                placeholder="아이디를 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
        </div>
        <div className="member-form-join-group">
        <p className="member-form-join-p">
        비밀번호<span className="red-star">*</span>
        </p>
            <input
                type="password"
                name="memberPassword"
                placeholder="비밀번호를 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
        </div>
        <div className="member-form-join-group">
        <p className="member-form-join-p">
        이름<span className="red-star">*</span>
        </p>
            <input
                type="text"
                name="memberName"
                placeholder="이름을 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
        </div>
        <div className="member-form-join-group">
        <p className="member-form-join-p">
        이메일<span className="red-star">*</span>
        </p>
            <input
                type="email"
                name="memberEmail"
                placeholder="이메일을 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
            <input
                type="button"
                value="인증번호 발송"
                onClick={sendJoinCode}
                className="member-join-button"
            />
        </div>
        <div className="member-form-join-group">
            <input
                type="text"
                placeholder="인증번호를 입력하세요"
                onChange={(e) => setTempCode(e.target.value)}
                required
            />
        </div>
        <div className="member-form-interest-group">
            <p>관심사</p>
        <div className="interest-item">인사/총무/노무</div>
        <div className="interest-item">영업/영업관리</div>
        <div className="interest-item">IT개발/데이터</div>

        <div className="interest-item">중학생</div>
        <div className="interest-item">고등학생</div>
        <div className="interest-item">대학입시상담</div>

        <div className="interest-item">음악</div>
        <div className="interest-item">글쓰기</div>
        <div className="interest-item">미술</div>
        
        <div className="interest-item">스타트업 아이디어</div>
        <div className="interest-item">마케팅 전략</div>
        <div className="interest-item">법률 특허 상담</div>

        </div>
        <input
            type="button"
            value="회원가입"
            onClick={MemberJoinAction}
            className="member-signup-button"
        />
    </form>
</div>


  );
};

export default MemberJoinFormPage;
