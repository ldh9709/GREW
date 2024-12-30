const BACKEND_SERVER = ""; // 실제 백엔드 서버 주소로 변경

/*
POST /review                          : 리뷰 등록
PUT /review/update/{reviewNo}         : 리뷰 수정
PUT /review/delete/{reviewNo}         : 리뷰 삭제(상태변경)
GET /review/{reviewNo}                : 리뷰 하나 보기
GET /review/ChatRoom/{chatRoomNo}     : 특정 요청 리뷰 목록 출력
GET /review/member/{memberNo}         : 특정 멤버 리뷰 목록 출력
GET /review/reviewList                : 전체 리뷰 목록 출력
*/

// 리뷰 등록
export const writeReview = async (reviewDto, token) => {
  const response = await fetch(`${BACKEND_SERVER}/review`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
      Authorization: `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가
    },
    body: JSON.stringify(reviewDto),
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

// 리뷰 수정
export const updateReview = async (reviewNo, reviewDto) => {
  const response = await fetch(`${BACKEND_SERVER}/review/update/${reviewNo}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    },
    body: JSON.stringify(reviewDto),
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

// 리뷰 삭제
export const deleteReview = async (reviewNo) => {
  const response = await fetch(`${BACKEND_SERVER}/review/delete/${reviewNo}`, {
    method: "PUT",
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

// 리뷰 상세보기
export const viewReview = async (reviewNo) => {
  const response = await fetch(`${BACKEND_SERVER}/review/${reviewNo}`, {
    method: "GET",
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

// 특정 채팅방 리뷰 목록 출력
export const listReviewByChatRoom = async (chatRoomNo, page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/review/ChatRoom/${chatRoomNo}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const listReviewByMember = async (
  mentorProfileNo,
  page,
  size,
  token
) => {
  const response = await fetch(
    `${BACKEND_SERVER}/review/reviewList/${mentorProfileNo}?page=${page}&size=${size}`,
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`, // JWT 토큰 추가
      },
    }
  );
  if (!response.ok) {
    console.error("Error fetching reviews:", response.statusText);
  }
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const listReviewAll = async (page, size) => {
  try {
    const response = await fetch(`${BACKEND_SERVER}/review/reviewList`, {
      method: "GET",
    });

    // 응답 상태 코드가 200일 때만 JSON 처리
    if (response.ok) {
      const responseJsonObject = await response.json();
      return responseJsonObject;
    } else {
      throw new Error(`Error: ${response.statusText}`);
    }
  } catch (error) {
    console.error("리뷰 목록 불러오기 실패:", error);
    return { data: [] }; // 실패 시 빈 배열 반환
  }
};
