import React, { useEffect, useState } from "react";
import * as alarmApi from "../api/alarmApi";
import "../css/styles.css";
import { getCookie } from "../util/cookieUtil";
import { useNavigate } from "react-router-dom";
const Alarim = () => {
  const memberCookie = getCookie("member");
  const [notifications, setNotifications] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    // 폴링을 위해 setInterval 사용 (5초마다 알림을 가져옴)
    setInterval(fetchNotifications, 5000);  // 5초마다 폴링
    fetchNotifications();
  }, []); // 빈 배열을 넣으면 컴포넌트가 마운트될 때만 실행됨
  const fetchNotifications = async () => {
    if(memberCookie){

      const response = await alarmApi.findByMemberNo(memberCookie.memberNo); // API 호출
      setNotifications(response.data); // 받은 데이터로 notifications 상태 업데이트
      console.log(response);
    }else{
      setNotifications(null);
    }
  };

  const deleteNotification = async (alarmNo) => {
    await alarmApi.deleteAlarm(alarmNo);

    fetchNotifications();
  };
  const deleteNotificationByMember = async (memberNo) => {
    await alarmApi.deleteAlarmByMember(memberNo);
    fetchNotifications();
  };
  const handleAlarmButton = async (alarmNo) => {
    await alarmApi.isReadAlarm(alarmNo);
    const responsejsonObject = await alarmApi.urlAlarm(alarmNo);
    console.log(responsejsonObject.data);
    navigate(responsejsonObject.data);
    fetchNotifications();
  };
  return (
    <div>
      <div className="notification-header">알림</div>
      <button
        className="notification-all-delete-btn"
        onClick={() => deleteNotificationByMember(memberCookie.memberNo)}
      >
        전체 삭제
      </button>
      <div>
        {memberCookie&&notifications.length > 0 ? (
          notifications.map((notification) => (
            <div key={notification.alarmNo} className="notification">
              {notification.isRead == 1 ? (
                <button
                  className="notification-main-btn"
                  onClick={() => {
                    handleAlarmButton(notification.alarmNo);
                  }}
                >
                  <div className="notification">
                    <div className="notification-icon">🔔</div>
                    <div>
                      {notification.alarmContent}
                    </div>
                  </div>

                  <div
                    className="notification-close-btn"
                    onClick={(e) => {
                      e.stopPropagation();
                      deleteNotification(notification.alarmNo);
                    }}
                  >
                    ×
                  </div>
                </button>
              ) : (
                <button
                  className="notification-main-btn-isread"
                  onClick={() => {
                    handleAlarmButton(notification.alarmNo);
                  }}
                >
                  <div className="notification">
                    <div className="notification-icon">🔔</div>
                    <div>
                      {notification.alarmContent}
                    </div>
                  </div>

                  <div
                    className="notification-close-btn"
                    onClick={(e) => {
                      e.stopPropagation();
                      deleteNotification(notification.alarmNo);
                    }}
                  >
                    ×
                  </div>
                </button>
              )}
            </div>
          ))
        ) : (
          <div>알림이 없습니다.</div>
        )}
      </div>
    </div>
  );
};
export default Alarim;
