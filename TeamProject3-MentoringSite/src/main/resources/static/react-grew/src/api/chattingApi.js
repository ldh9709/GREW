const BACKEND_SERVER = "";
export const createChatting = async (menteeNo, mentorNo) => {
    const response = await fetch(`${BACKEND_SERVER}/create/${menteeNo}, ${mentorNo}`, {
        method: 'POST'
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
};

export const listChatRoom = async (token, page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/chatroom/list?page=${page}&size=${size}`,
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가
      },
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const activeListChatRoom = async (token,page,size) => {
  const response = await fetch(`${BACKEND_SERVER}/chatroom/list/active?page=${page}&size=${size}`, {
     method: "GET",
     headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
    }
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const waitListChatRoom = async (token,page,size) => {
  const response = await fetch(`${BACKEND_SERVER}/chatroom/list/wait?page=${page}&size=${size}`, {
     method: "GET",
     headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
    }
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const changeChatRoomName = async (chatRoomNo, token, chatRoomName) => {
  const response = await fetch(
    `${BACKEND_SERVER}/chatroom/name/${chatRoomNo}, ${chatRoomName}`,
    {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가
      },
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const readChatMessage = async (chatRoomNo) => {
   const response = await fetch(
     `${BACKEND_SERVER}/chatmessage/update/${chatRoomNo}`,
     {
       method: "PUT"
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

export const sendImage = async (token, chatRoomNo, imageBlob) => {
  const response = await fetch(
    `${BACKEND_SERVER}/chatroom/${chatRoomNo}/sendImage`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가
      },
      body: JSON.stringify({
        imageBlob: imageBlob, // Base64로 인코딩된 이미지 데이터
      }),
    }
  );

  const responseJsonObject = await response.json();
  return responseJsonObject; // 서버로부터 반환된 이미지 처리 결과
};
