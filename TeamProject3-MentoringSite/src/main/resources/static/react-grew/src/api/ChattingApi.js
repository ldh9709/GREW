const BACKEND_SERVER='';
export const listChatRoom=async ()=>{
   const response=await fetch(`${BACKEND_SERVER}/chatroom`,{
      method:'GET'
   });
   const responseJsonObject= await response.json();
   return responseJsonObject;
}

export const getChatRoomName=async (chatRoomNo, memberNo)=>{
   const response=await fetch(`${BACKEND_SERVER}/chatroom/name/${chatRoomNo}, ${memberNo}`,{
      method:'GET'
   });
   const responseJsonObject= await response.json();
   return responseJsonObject;
}

export const changeChatRoomName=async (chatRoomNo, sendJsonObject)=>{
   const response=await fetch(`${BACKEND_SERVER}/chatroom/name/${chatRoomNo}`,{
      method:'PUT',
      headers:{
         'Content-Type':'application/json'
      },
      body:JSON.stringify(sendJsonObject)
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