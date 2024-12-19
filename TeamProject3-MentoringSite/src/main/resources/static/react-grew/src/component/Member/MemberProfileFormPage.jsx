import React, { useEffect, useState } from "react";
import { memberProfile } from "../../api/memberApi";
import "../../css/memberPage.css"
const MemberProfileFormPage = () => {
    
    

    const [member, setMember] = useState({
        memberName: "",
        memberId: "",
        memberEmail: "",
        interests: []
    });

    const fetchProfileData = async () => {

        const response = await memberProfile();
        console.log("프로필response : ", response)
        console.log("프로필response.data : ", response.data)

        const { data } = response;

        setMember({
            memberName: data.memberName,
            memberId: data.memberId,
            memberEmail: data.memberEmail,
            interests: data.interests || []  
        });
        console.log("setMember : ", setMember);

        
    }    

    /* 컴포넌트 마운트 시 프로필 데이터 호출 */
    /* 최초 화면 로딩 시 한 번만 실행 */
    useEffect(() => {
        fetchProfileData();
    }, []);

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
                <input type="password" placeholder="비밀번호 입력" className="member-form-password" required />
            </div>

            <div className="member-form-group">
                <label>비밀번호 확인</label>
                <input type="password" placeholder="비밀번호 확인" className="member-form-password" required />
            </div>

            <div className="member-form-group">
                <label>이메일</label>
                <div className="email-group">
                    <input type="text" placeholder="이메일" className="member-form-email" defaultValue={member.memberEmail} />
                </div>
            </div>

             {/* 관심사 영역 */}
             <div className="member-form-group">
                        <label>관심사</label>
                        <div className="member-form-interest-group">
                            <fieldset>
                                <legend>직무상담</legend>
                                <p>
                                    <input type="checkbox" value="2" />
                                    인사/총무/노무
                                </p>
                                <p>
                                    <input type="checkbox" value="3" />
                                    영업/영업관리
                                </p>
                                <p>
                                    <input type="checkbox" value="4" />
                                    IT개발/데이터
                                </p>
                            </fieldset>

                            <fieldset>
                                <legend>학습/교육</legend>
                                <p>
                                    <input type="checkbox" value="6" />
                                    중학생
                                </p>
                                <p>
                                    <input type="checkbox" value="7" />
                                    고등학생
                                </p>
                                <p>
                                    <input type="checkbox" value="8" />
                                    대학입시상담
                                </p>
                            </fieldset>

                            <fieldset>
                                <legend>예술/창작</legend>
                                <p>
                                    <input type="checkbox" value="10" />
                                    음악
                                </p>
                                <p>
                                    <input type="checkbox" value="11" />
                                    글쓰기
                                </p>
                                <p>
                                    <input type="checkbox" value="12" />
                                    미술
                                </p>
                            </fieldset>

                            <fieldset>
                                <legend>창업/비즈니스</legend>
                                <p>
                                    <input type="checkbox" value="16" />
                                    스타트업 아이디어
                                </p>
                                <p>
                                    <input type="checkbox" value="17" />
                                    마케팅 전략
                                </p>
                                <p>
                                    <input type="checkbox" value="18" />
                                    법률 특허 상담
                                </p>
                            </fieldset>
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
