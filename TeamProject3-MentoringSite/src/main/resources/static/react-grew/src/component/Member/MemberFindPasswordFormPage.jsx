import React, { useState } from "react";
import "../../css/memberPage.css" // 별도의 CSS 파일을 연결해 스타일을 적용
import { useNavigate } from "react-router-dom";
import * as memberApi from "../../api/memberApi"
import * as responseStatus from "../../api/responseStatusCode";
import { toast } from "react-toastify";

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
        try {
       
            if(!member.memberId) {
                toast.error("아이디를 입력해주세요.");
                return;
            }

            if(!member.memberName) {
                toast.error("이름을 입력해주세요요.");
                return;
            }

            if(!member.memberEmail) {
                toast.error("이메일을 다시 확인해주세요.");
                return;
            }

            const response = await memberApi.findPassword(member);
            console.log(response.trace);
            
            switch(response.status) {
                case responseStatus.PASSWORD_RESET_SUCCESS:
                    toast.success("이메일로 임시 비밀번호가 전송되었습니다.");
                    navigate('/login');
                    break;
                case responseStatus.NOT_FOUND_MEMBER:
                    toast.error("이메일과 일치하는 회원이 없습니다.");
                    break;
                default:
                    toast.error("오류가 발생하였습니다. 정보를 다시 확인해주세요.");
                    break;
            }
        } catch (error) {
            console.log("에러 : ", error);
        }
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
                name="memberId"
                placeholder="아이디를 입력하세요" 
                onChange={handleChangeFindPasswordForm}
                 />
            </div>
            <div className="form-group">
            <label htmlFor="userName">이름</label>
                <input 
                type="text" 
                id="memberName" 
                name="memberName"
                placeholder="이름을 입력하세요" 
                onChange={handleChangeFindPasswordForm}
                />
            </div>
            <div className="form-group">
            <label htmlFor="email">이메일 주소</label>
            <div className="email-group">
                <input
                type="email"
                id="memberEmail"
                name="memberEmail"
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
