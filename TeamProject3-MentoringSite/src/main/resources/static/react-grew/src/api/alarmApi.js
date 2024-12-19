const BACKEND_SERVER = " ";
//멤버의 알림찾기
export const findByMemberNo = async (memberNo) => {
  const response = await fetch(
    `${BACKEND_SERVER}/alarms?memberNo=${memberNo}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//알림하나삭제제