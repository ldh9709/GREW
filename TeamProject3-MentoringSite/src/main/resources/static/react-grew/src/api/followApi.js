const BACKEND_SERVER = "";
/*
POST    /follow                                :팔로우 신청
GET     /follow/mentor/{mentorNo}/follower-count:멘토 팔로워 수 출력
GET     /follow/mentee/{menteeNo}              :멘티가 팔로잉한 멘토 리스트 출력
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
export const followerCount = async(mentorNo)=>{
    const response = await fetch(`${BACKEND_SERVER}/follow/mentor/${mentorNo}/follower-count`,{
        method:'GET'
    })
    const responseJsonObject = await response.json();
    return responseJsonObject;
}
//팔로잉 리스트 조회
export const followList = async(menteeNo, page, size)=>{
    const response = await fetch(`${BACKEND_SERVER}/follow/mentee/${menteeNo}?page=${page}&size=${size}`,{
        method:'GET'
    })
    const responseJsonObject = await response.json();
    return responseJsonObject;
}
//팔로잉 취소
export const deleteFollow = async(followNo)=>{
    const response = await fetch(`${BACKEND_SERVER}/follow/${followNo}`,{
        method:'DELETE'
    })
    const responseJsonObject = await response.json();
    return responseJsonObject;
}