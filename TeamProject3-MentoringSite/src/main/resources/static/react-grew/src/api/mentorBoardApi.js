const BACKEND_SERVER = "";


//특정 멘토 컨텐츠 리스트 조회
export const listBoardContentsByMemberNo = async(token,page,size) => {
    
    const response = await fetch(`${BACKEND_SERVER}/mentor-board/list/member?page=${page}&size=${size}`,{
        method:'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })
    const responseJsonObject = await response.json();
    return responseJsonObject;
}