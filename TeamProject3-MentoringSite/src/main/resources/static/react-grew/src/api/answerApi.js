const BACKEND_SERVER = "";
/*
POST /answer
PUT  /answer/update/{answerNo}
PUT  /answer/delete/{answerNo}
PUT  /answer/accept
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
export const upVote = async (answerNo, memberNo) => {
  const response = await fetch(
    `${BACKEND_SERVER}/vote/${answerNo}/upvote?memberNo=${memberNo}`,
    {
      method: "POST",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변 비추천
export const downVote = async (answerNo, memberNo) => {
  const response = await fetch(
    `${BACKEND_SERVER}/vote/${answerNo}/downvote?memberNo=${memberNo}`,
    {
      method: "POST",
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
export const deleteAnswer = async (answerNo) => {
  const response = await fetch(`${BACKEND_SERVER}/answer/delete/${answerNo}`, {
    method: "PUT",
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변수정
export const updateAnswer = async (answerNo) => {
  const response = await fetch(`${BACKEND_SERVER}/answer/update/${answerNo}`, {
    method: "PUT",
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//답변등록
export const writeAnswer = async (sendJsonObject,inquiryNo) => {
  const response = await fetch(`${BACKEND_SERVER}/answer/${inquiryNo}`, {
    method: "POST", // HTTP 메서드
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
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
