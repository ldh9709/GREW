import React, { useState } from "react";
import "../../css/memberPage.css"; // 별도의 CSS 파일 연결
import { useNavigate } from "react-router-dom";
import * as memberApi from "../../api/memberApi";
import * as responseStatus from "../../api/responseStatusCode";
import { toast } from "react-toastify";

const MemberFindIdForm = () => {
  const navigate = useNavigate();

  const [member, setMember] = useState({
    memberId: "",
    memberName: "",
    memberEmail: "",
    inputCode: ""
  });

  const handleChangeFindIdForm = (e) => {
    setMember({...member, [e.target.name]: e.target.value});
  };

  const sendMailFindId = async () => {

    if(!member.memberName) {
      toast.error("이름을 입력해주세요.");
      return;
    }

    if(!member.memberEmail) {
      toast.error("이메일을 다시 확인해주세요");
      return;
    }
    const response = await memberApi.sendMailFindId(member);

    switch (response.status) {
      case responseStatus.EMAIL_SEND_SUCCESS:
        toast.success("이메일이 발송되었습니다.");
        break;
      default:
        toast.error("이메일을 다시 확인해주세요.")
        break;
    }
  }

  const certificationCodeFindId = async () => {
    const response = await memberApi.certificationCodeFindId(member.memberEmail, member.inputCode);
    console.log("certificationCodeFindId : ",response)
    
    const memberId = response.data;
    
    switch (response.status) {
      case responseStatus.INPUTCODE_CONFIRM_SUCCESS:
        toast.success("인증번호가 확인되었습니다.");
        navigate('/member/find-id-check', { state: { memberId } });
        break;
      case responseStatus.NOT_FOUND_MEMBER:
        toast.error("아이디가 존재하지 않습니다.")
      default:
        toast.error("인증번호를 다시 확인해주세요.")
        break;
    }
  }

  return (
    <div className="password-reset-container">
      <h1>개인회원 아이디 찾기</h1>
      <p className="description">
        회원정보에 등록된 정보로 아이디를 찾을 수 있습니다.
      </p>
      <form className="reset-form">
        {/* 이름 입력 */}
        <div className="form-group">
          <label htmlFor="memberName">이름</label>
          <input
            type="text"
            id="memberName"
            name="memberName"
            onChange={handleChangeFindIdForm}
            placeholder="이름을 입력하세요"
          />
        </div>

        {/* 이메일 입력 */}
        <div className="form-group">
          <label htmlFor="memberEmail">이메일 주소</label>
          <input
            type="email"
            id="memberEmail"
            name="memberEmail"
            onChange={handleChangeFindIdForm}
            placeholder="이메일 주소를 입력하세요"
          />
        </div>
        {/* 버튼 */}
        <button
          type="button"
          className="send-code-button"
          onClick={sendMailFindId}
        >
          인증번호 받기
        </button>
        
        {/* 인증번호 */}
        <div className="form-group">
          <label htmlFor="memberEmail">인증 번호</label>
          <input
            type="text"
            id="inputCode"
            name="inputCode"
            onChange={handleChangeFindIdForm}
            placeholder="인증번호 입력"
          />
        </div>

        {/* 버튼 */}
        <button
          type="button"
          className="send-code-button"
          onClick={certificationCodeFindId}
        >
          다음
        </button>
      </form>
    </div>
  );
};

export default MemberFindIdForm;
