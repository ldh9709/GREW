const BACKEND_SERVER='';
export const listChatRoom = async (token,page,size) => {
   const response = await fetch(`${BACKEND_SERVER}/chatroom/list?page=${page}&size=${size}`, {
      method: 'GET',
      headers: {
         'Authorization': `Bearer ${token}`, // 전달받은 JWT 토큰 사용
         'Content-Type': 'application/json'
      }
   });
   const responseJsonObject= await response.json();
   return responseJsonObject;
}
export const changeChatRoomName=async (chatRoomNo, token, chatRoomName)=>{
   const response=await fetch(`${BACKEND_SERVER}/chatroom/name/${chatRoomNo}, ${chatRoomName}`,{
      method:'PUT',
      headers: {
         'Authorization': `Bearer ${token}`, // 전달받은 JWT 토큰 사용
         'Content-Type': 'application/json'
      }
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
 export const leaveChatRoom=async (chatRoomNo, token)=>{
   const response=await fetch(`${BACKEND_SERVER}/chatroom/${chatRoomNo}/leave`,{
      method:'PUT',
      headers: {
         'Authorization': `Bearer ${token}`, // 전달받은 JWT 토큰 사용
         'Content-Type': 'application/json'
      }
   });
   const responseJsonObject= await response.json();
   return responseJsonObject;
}