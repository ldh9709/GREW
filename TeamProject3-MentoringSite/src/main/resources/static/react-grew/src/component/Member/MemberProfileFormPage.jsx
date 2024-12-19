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
    /* 관심사 선택 시 배열 업데이트 */
    const handleInterestClick = (value) => {
        /* 상태를 업데이트, 함수형 업데이트 방식을 사용하여 이전 상태(prevState)를 기반으로 새로운 상태를 계산 */
        setMember((prevState) => {
        /* prevState.interests: 기존에 선택된 관심사의 배열, includes(value) : value가 interests 배열에 포함되어 있는지 확인, 있으면 true, 없으면 false 반환 */
        const alreadySelected = prevState.interests.includes(value);

        const updatedInterests = alreadySelected
            /* alreadySelected가 true일 경우(이미 선택된 경우에 또 눌렀을 때), filter를 사용하여 value를 제외한 나머지 항목들로 새로운 배열을 생성(이미 생성됐을 시 제거)  */
            ? prevState.interests.filter((interest) => interest !== value)
            /* 스프레드 연산자(...)를 사용하여 기존 배열의 모든 항목을 복사한 뒤, value를 추가, 선택되지 않은 경우에 추가(기존에 없을 시 추가) */
            : [...prevState.interests, value]; 
            return { ...prevState, interests: updatedInterests }; // 새 배열로 업데이트
            });
        };
        
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

            <div className="member-form-group">
  <label>관심사</label>
  <div className="member-form-interest-group">
            <div
                className={`interest-item ${
                member.interests.includes("2") ? "selected" : ""
                }`}
            >
                인사/총무/노무
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("3") ? "selected" : ""
                }`}
            >
                영업/영업관리
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("4") ? "selected" : ""
                }`}
            >
                IT개발/데이터
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("6") ? "selected" : ""
                }`}
            >
                중학생
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("7") ? "selected" : ""
                }`}
            >
                고등학생
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("8") ? "selected" : ""
                }`}
            >
                대학입시상담
            </div>

            <div
                className={`interest-item ${
                member.interests.includes("10") ? "selected" : ""
                }`}
            >
                음악
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("11") ? "selected" : ""
                }`}
            >
                글쓰기
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("12") ? "selected" : ""
                }`}
            >
                미술
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("13") ? "selected" : ""
                }`}
            >
                사진/영상 제작
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("14") ? "selected" : ""
                }`}
            >
                연기/연극
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("16") ? "selected" : ""
                }`}
            >
                스타트업 아이디어
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("17") ? "selected" : ""
                }`}
            >
                마케팅 전략
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("18") ? "selected" : ""
                }`}
            >
                법률 특허 상담
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("22") ? "selected" : ""
                }`}
            >
                피트니스
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("23") ? "selected" : ""
                }`}
            >
                요가/필라테스
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("24") ? "selected" : ""
                }`}
            >
                재활 운동
            </div>
            <div
                className={`interest-item ${
                member.interests.includes("25") ? "selected" : ""
                }`}
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
