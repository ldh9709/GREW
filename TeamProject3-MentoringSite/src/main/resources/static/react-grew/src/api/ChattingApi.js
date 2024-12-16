const BACKEND_SERVER='';
export const viewChatMessage=async (roomId)=>{
    const response=await fetch(`${BACKEND_SERVER}/messages/${roomId}`,{
       method:'GET'
    });
    const responseJsonObject= await response.json();
    return responseJsonObject;
 }