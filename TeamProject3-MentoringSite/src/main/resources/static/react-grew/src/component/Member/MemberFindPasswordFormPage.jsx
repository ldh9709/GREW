import React, { useState } from "react";
import "../../css/memberPage.css" // 별도의 CSS 파일을 연결해 스타일을 적용
import { useNavigate } from "react-router-dom";
import * as memberApi from "../../api/memberApi"

const MemberFindPasswordForm = () => {
    
    const navigate = useNavigate();

    const [member, setMember] = useState({
        memberId: "",
        memberName: "",
        memberEmail: "",
    });

    const handleChangeFindPasswordForm = (e) => {
        setMember({...member, [e.target.name]: e.target.value});
    };

    const findPassword = async () => {
        const response = await memberApi.findPassword(member);
        console.log(response);
    }


    return (

        <div className="password-reset-container">
        <h1>개인회원 비밀번호 찾기</h1>
        <p className="description">
            회원정보에 등록된 정보로 비밀번호를 찾을 수 있습니다.
        </p>
        <form className="reset-form">
            <div className="form-group">
            <label htmlFor="userId">아이디</label>
                <input 
                type="text" 
                id="memberId" 
                placeholder="아이디를 입력하세요" 
                onChange={handleChangeFindPasswordForm}
                 />
            </div>
            <div className="form-group">
            <label htmlFor="userName">이름</label>
                <input 
                type="text" 
                id="memberName" 
                placeholder="이름을 입력하세요" 
                onChange={handleChangeFindPasswordForm}
                />
            </div>
            <div className="form-group">
            <label htmlFor="birthDate">생년월일</label>
                <input 
                type="text" 
                id="memberBirthDay" 
                placeholder="YYYY/MM/DD" 
                onChange={handleChangeFindPasswordForm}
                />
            </div>
            <div className="form-group">
            <label htmlFor="email">이메일 주소</label>
            <div className="email-group">
                <input
                type="email"
                id="memberEmail"
                placeholder="이메일 주소를 입력하세요"
                onChange={handleChangeFindPasswordForm}
                />
                
            </div>
            <button type="button" className="send-code-button" onClick={findPassword}>
            비밀번호 찾기
            </button>
            </div>
        </form>
        </div>
    );
    };

export default MemberFindPasswordForm;
