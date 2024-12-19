const BACKEND_SERVER='';
export const listChatRoom = async (token) => {
   const response = await fetch(`${BACKEND_SERVER}/chatroom/list`, {
      method: 'GET',
      headers: {
         'Authorization': `Bearer ${token}`, // 전달받은 JWT 토큰 사용
         'Content-Type': 'application/json'
      }
   });
   const responseJsonObject= await response.json();
   return responseJsonObject;
}

export const changeChatRoomName=async (chatRoomNo, memberNo, chatRoomName)=>{
   const response=await fetch(`${BACKEND_SERVER}/chatroom/name/${chatRoomNo}, ${memberNo}, ${chatRoomName}`,{
      method:'PUT'
   });
   const responseJsonObject= await response.json();
   return responseJsonObject;
}

export const viewChatMessage=async (chatRoomNo)=>{
    const response=await fetch(`${BACKEND_SERVER}/chatroom/messages/${chatRoomNo}`,{
       method:'GET'
    });
    const responseJsonObject= await response.json();
    return responseJsonObject;
 }

 export const leaveChatRoom=async (chatRoomNo, memberNo)=>{
   const response=await fetch(`${BACKEND_SERVER}/chatroom/${chatRoomNo}/leave/${memberNo}`,{
      method:'PUT'
   });
   const responseJsonObject= await response.json();
   return responseJsonObject;
}