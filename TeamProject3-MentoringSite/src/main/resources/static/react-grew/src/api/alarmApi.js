const BACKEND_SERVER = " ";
//멤버의 알림찾기
export const findByMemberNo = async (memberNo) => {
  const response = await fetch(
    `${BACKEND_SERVER}/alarms/alarms?memberNo=${memberNo}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//알림하나삭제
export const deleteAlarm = async (alarmNo) => {
  await fetch(`${BACKEND_SERVER}/alarms/delete?alarmNo=${alarmNo}`, {
    method: "DELETE",
  });
};
//멤버의 알림전체삭제
export const deleteAlarmByMember = async (memberNo) => {
  await fetch(`${BACKEND_SERVER}/alarms/delete/all?memberNo=${memberNo}`, {
    method: "DELETE",
  });
};
