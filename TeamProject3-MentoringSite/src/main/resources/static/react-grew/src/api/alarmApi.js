const BACKEND_SERVER = " ";

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