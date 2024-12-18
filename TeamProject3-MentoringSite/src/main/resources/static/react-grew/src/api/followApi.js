import { getCookie } from "../util/cookieUtil";

const BACKEND_SERVER = "";
/*
POST    /follow                                :팔로우 신청
GET     /follow/mentor/{mentorNo}/follower-count:멘토 팔로워 수 출력
GET     /followList            :멘티가 팔로잉한 멘토 리스트 출력
DELETE  /follow/{followNo}                     :팔로윙 취소
*/


//팔로우 신청
export const addfollow = async(sendJsonObject)=>{
    const response = await fetch(`${BACKEND_SERVER}/follow`,{
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
        },
        body: JSON.stringify(sendJsonObject)
    })
    const responseJsonObject = await response.json();
    return responseJsonObject;
}
//멘토 팔로워 수 출력
// export const followerCount = async()=>{
//     const response = await fetch(`${BACKEND_SERVER}/follow/mentor/follower-count`,{
//         method:'GET'
//     })
//     const responseJsonObject = await response.json();
//     return responseJsonObject;
// }
//팔로잉 리스트 조회
export const followList = async (page) => {
    // 저장된 토큰 가져오기
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;

    const response = await fetch(`${BACKEND_SERVER}/follow/followList?page=${page}&size=6`,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
        }
    })
    const responseJsonObject = await response.json();
    return responseJsonObject;
}
//팔로잉 취소
export const deleteFollow = async (followNo) => {
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;

    const response = await fetch(`${BACKEND_SERVER}/follow/${followNo}`,{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
        }
    })
    const responseJsonObject = await response.json();
    return responseJsonObject;
}