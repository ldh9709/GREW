const BACKEND_SERVER = "";
/*
GET  /member/check-memberId                 :아이디 중복 체크
POST /member                                :회원 가입
PUT  /member/{memberNo}                     :회원 정보 수정
PUT  /member/{memberNo}/status/{statusNo}   :회원 상태 변경
GET  /member                                :회원 전체 조회
GET  /member/{memberNo}                     :특정 회원 조회
GET  /member/mentee-summary/{memberNo}      :멘티 회원 활동정보 요약 조회
GET  /member/mentor-summary/{memberNo}      :멘토 회원 활동정보 요약 조회

*/


//멘티 회원 활동정보 요약
export const memberCountSummary = async(memberNo) => {
    const response = await fetch(`${BACKEND_SERVER}/member/mentee-summary/${memberNo}`,{
        method: 'GET',
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}

//멘토 회원 활동정보 요약
export const memberInfoSummary = async(memberNo) => {
    const response = await fetch(`${BACKEND_SERVER}/member/${memberNo}`,{
        method: 'GET'
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}
