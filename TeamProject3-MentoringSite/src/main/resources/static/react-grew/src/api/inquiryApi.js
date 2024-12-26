import { getCookie } from "../util/cookieUtil";

const BACKEND_SERVER = "";

/*
POST /inquiry                         :질문쓰기
PUT  /inquiry/update/{inquiryNo}      :질문수정
PUT  /inquiry/increase/{inquiryNo}    :조회수증가
PUT  /inquiry/delete/{inquiryNo}      :질문삭제(상태변경)
GET  /inquiry/{inquiryNo}             :질문한개보기
GET  /inquiry/viewCount               :조회수 순으로 전체 질문출력
GET  /inquiry/viewCount/{categoryNo}  :조회수 순으로 카테고리별 질문출력
GET  /inquiry/answerCount             :답변수 순으로 전체 질문출력
GET  /inquiry/answerCount/{categoryNo}:답변수 순으로 카테고리별 질문출력
GET  /inquiry/search/{search}         :질문 검색
GET  /inquiry                         :내가 작성한 질문 전체 출력

*/
//질문수정
export const updateInquiry = async (sendJsonObject) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/update/${sendJsonObject.inquiryNo}`,
    {
      method: "PUT", // HTTP 메서드
      headers: {
        "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
      },
      body: JSON.stringify(sendJsonObject), // 요청 본문에 JSON 객체 전달
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//질문삭제(상태변경)
export const deleteInquiry = async (inquiryNo) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/delete/${inquiryNo}`,
    {
      method: "PUT",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//조회수증가
export const increaseView = async (inquiryNo) => {
  await fetch(`${BACKEND_SERVER}/inquiry/increase/${inquiryNo}`, {
    method: "PUT",
  });
};
//질문전체조회수정렬
export const listInquiryView = async (page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/view-count?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//질문전체답변수정렬
export const listInquiryAnswer = async (page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/answer-count?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//질문전체최신순정렬
export const listInquiryDate = async (page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/date?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//질문하나보기
export const viewInquiry = async (inquiryNo) => {
  const response = await fetch(`${BACKEND_SERVER}/inquiry/view/${inquiryNo}`, {
    method: "GET",
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//질문등록
export const writeInquiry = async (sendJsonObject,token) => {
  const response = await fetch(`${BACKEND_SERVER}/inquiry`, {
    method: "POST", // HTTP 메서드
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더 설정
      'Authorization': `Bearer ${token}`, // Authorization 헤더에 JWT 토큰 추가
    },
    body: JSON.stringify(sendJsonObject), // 요청 본문에 JSON 객체 전달
   });
   console.log(sendJsonObject);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//전체질문카테고리별최신순
export const listInquiryByCategoryDate = async (categoryNo, page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/${categoryNo}/date?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//전체질문카테고리별답변많은순
export const listInquiryByCategoryAnswer = async (categoryNo, page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/${categoryNo}/answer-count?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//전체질문카테고리별조회순
export const listInquiryByCategoryView = async (categoryNo, page, size) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/${categoryNo}/view-count?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//전체질문카테고리별(대분류)최신순
export const listInquiryByParentCategoryDate = async (
  categoryNo,
  page,
  size
) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/${categoryNo}/parent/date?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//전체질문카테고리별(대분류)답변많은순
export const listInquiryByParentCategoryAnswer = async (
  categoryNo,
  page,
  size
) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/${categoryNo}/parent/answer-count?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//전체질문카테고리별(대분류)조회순
export const listInquiryByParentCategoryView = async (
  categoryNo,
  page,
  size
) => {
  const response = await fetch(
    `${BACKEND_SERVER}/inquiry/${categoryNo}/parent/view-count?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//질문 검색
export const searchInquiry = async(search,page,size)=>{
   const response = await fetch(`${BACKEND_SERVER}/inquiry/search/${search}?page=${page}&size=${size}`,{
      method:'GET'
   });
   const responseJsonObject = await response.json();
   return responseJsonObject;
}

//내가 쓴 질문 
export const listInquiryByMemberNo = async (token,page) => {
   
   const response = await fetch(`${BACKEND_SERVER}/inquiry/list/member?page=${page}&size=10`, {
      method: 'GET',
      headers: {
         'Content-Type': 'application/json',
         'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
      }
   });
   const responseJsonObject = await response.json();
   return responseJsonObject;
}
//질문의 답변수 
export const countAnswerByinquiryNo = async (inquiryNo) => {
   
   const response = await fetch(`${BACKEND_SERVER}/answer/count-answer/${inquiryNo}`, {
      method: 'GET',
      headers: {
         'Content-Type': 'application/json',
      }
   });
   const responseJsonObject = await response.json();
   return responseJsonObject;
}
//베스트 답변변
export const bestAnswerList = async () => {
   
   const response = await fetch(`${BACKEND_SERVER}/answer/recently-vote?page=0&size=10`, {
      method: 'GET',
      headers: {
         'Content-Type': 'application/json',
      }
   });
   const responseJsonObject = await response.json();
   return responseJsonObject;
}
