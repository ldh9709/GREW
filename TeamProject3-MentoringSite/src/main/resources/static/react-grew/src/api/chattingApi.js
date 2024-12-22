const BACKEND_SERVER = "";
export const listChatRoom = async (token,page,size) => {
  const response = await fetch(`${BACKEND_SERVER}/chatroom/list?page=${page}&size=${size}`, {
     method: "GET",
     headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
    }
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const memberListChatRoom = async (token,page,size) => {
  const response = await fetch(`${BACKEND_SERVER}/chatroom/memberList?page=${page}&size=${size}`, {
     method: "GET",
     headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
    }
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const changeChatRoomName = async (
  chatRoomNo,
  memberNo,
  chatRoomName
) => {
  const response = await fetch(
    `${BACKEND_SERVER}/chatroom/name/${chatRoomNo}, ${memberNo}, ${chatRoomName}`,
    {
      method: "PUT",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const viewChatMessage = async (chatRoomNo) => {
  const response = await fetch(
    `${BACKEND_SERVER}/chatroom/messages/${chatRoomNo}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const leaveChatRoom = async (chatRoomNo, memberNo) => {
  const response = await fetch(
    `${BACKEND_SERVER}/chatroom/${chatRoomNo}/leave/${memberNo}`,
    {
      method: "PUT",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
