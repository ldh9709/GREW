const BACKEND_SERVER = "";
/*
POST /answer
PUT  /answer/update/{answerNo}
PUT  /answer/delete/{answerNo}
PUT  /answer/accept
GET  /answer
GET  /answer/{answerNo}/answerDetail
GET  /answer/answerList/{inquiryNo}/inquiryVote
GET  /answer/answerList/{inquiryNo}/inquiryDate
GET  /answer/answerList/{categoryNo}/categoryVote
GET  /answer/answerList/{categoryNo}/categoryDate
GET  /answer/answerList/recently-vote
*/
//답변 최신순
export const AnswerByDate = async (inquiryNo, page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/answer/${inquiryNo}/answer-date?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변 추천순
export const AnswerByVote = async (inquiryNo, page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/answer/${inquiryNo}/answer-vote?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//추천수 세기
export const countVote = async (answerNo) => {
  const response = await fetch(`${BACKEND_SERVER}/vote/${answerNo}/votes`, {
    method: "GET",
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변 추천
export const upVote = async (answerNo,token) => {
  const response = await fetch(
    `${BACKEND_SERVER}/vote/${answerNo}/upvote`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
        'Authorization': `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가
      },
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변 비추천
export const downVote = async (answerNo,token) => {
  const response = await fetch(
    `${BACKEND_SERVER}/vote/${answerNo}/downvote`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
        'Authorization': `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가
      },
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변채택
export const acceptAnswer = async (answerNo) => {
  const response = await fetch(`${BACKEND_SERVER}/answer/accept/${answerNo}`, {
    method: "PUT",
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변삭제
export const deleteAnswer = async (answerNo,token) => {
  const response = await fetch(`${BACKEND_SERVER}/answer/delete/${answerNo}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
      'Authorization': `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가
    },
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변수정
export const updateAnswer = async (sendJsonObject,token) => {
  const response = await fetch(`${BACKEND_SERVER}/answer/update/${sendJsonObject.answerNo}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      'Authorization': `Bearer ${token}`// 요청 헤더 설정
    },
    body: JSON.stringify(sendJsonObject), // 요청 본문에 JSON 객체 전달
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변등록
export const writeAnswer = async (sendJsonObject,inquiryNo,token) => {
  const response = await fetch(`${BACKEND_SERVER}/answer/create/${inquiryNo}`, {
    method: "POST", // HTTP 메서드
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
      'Authorization': `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가
    },
    body: JSON.stringify(sendJsonObject), // 요청 본문에 JSON 객체 전달
  });
  const responseJsonObject = response.json();
  return responseJsonObject;
};
//번호로 질문 가져오기
export const findInquiry = async (inquiryNo) => {
    const response = await fetch(`${BACKEND_SERVER}/inquiry/find/${inquiryNo}`, {
      method: "GET",
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
  };
//답변 하나보기
export const viewAnswer = async (answerNo) => {
    const response = await fetch(`${BACKEND_SERVER}/answer/view/${answerNo}`, {
      method: "GET",
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
  };

//내가 작성한 답변 내역
export const listAnswerByMemberNo = async (token, page) => {
  const response = await fetch(`${BACKEND_SERVER}/answer?page=${page}&size=10`, {
    method: 'GET',
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
      'Authorization': `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가     
    }
  })
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

//질문에 본인 답변 유무
export const isAnswerByInquiryNo = async (inquiryNo,memberNo) => {
  const response = await fetch(`${BACKEND_SERVER}/answer/isAnswer?inquiryNo=${inquiryNo}&memberNo=${memberNo}`, {
    method: 'GET',
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
    }
  })
  const responseJsonObject = await response.json();
  return responseJsonObject;
};