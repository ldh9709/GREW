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
//알림 url
export const urlAlarm = async (alarmNo) => {
  const response = await fetch(`${BACKEND_SERVER}/alarms/${alarmNo}/redirect`, {
    method: "GET",
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
//알림 읽음표시
export const isReadAlarm = async (alarmNo) => {
  await fetch(`${BACKEND_SERVER}/alarms/is-read/${alarmNo}`, {
    method: "PUT",
  });
};
//안읽은 알림 갯수
export const isReadAlarmCount = async(memberNo)=>{
  const response =  await fetch(`${BACKEND_SERVER}/alarms/is-read/count?memberNo=${memberNo}`,{
    method:"PUT",
  });
  const responseJsonObject = response.json();
  return responseJsonObject;
};
export const findAlarm = async(alarmNo)=>{
  const response =  await fetch(`${BACKEND_SERVER}/alarms/find-alarm?alarmNo=${alarmNo}`,{
    method:"GET",
  });
  const responseJsonObject = response.json();
  return responseJsonObject;
};
//멤버 알림 전체 읽음 처리
export const isReadAllAlarm = async(memberNo)=>{
  await fetch(`${BACKEND_SERVER}/alarms/all/isread?memberNo=${memberNo}`,{
    method:"PUT",
  });
}