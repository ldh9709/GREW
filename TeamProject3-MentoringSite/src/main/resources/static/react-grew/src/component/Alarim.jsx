import React, { useEffect, useState } from "react";
import * as alarmApi from "../api/alarmApi";
import "../css/styles.css";
const Alarim = () => {
  const [notifications, setNotifications] = useState([]);
  useEffect(() => {
    fetchNotifications();
  }, []); // 빈 배열을 넣으면 컴포넌트가 마운트될 때만 실행됨
  const fetchNotifications = async () => {
    const response = await alarmApi.findByMemberNo(1); // API 호출
    console.log(response);
    setNotifications(response.data); // 받은 데이터로 notifications 상태 업데이트
  };

  const deleteNotification = async (alarmNo) => {
    await alarmApi.deleteAlarm(alarmNo);
    
    fetchNotifications();
  };
  const deleteNotificationByMember = async (memberNo) => {
    await alarmApi.deleteAlarmByMember(memberNo);
    fetchNotifications();
  };
  return (
    <div>
      <div className="notification-header">알림</div>
      <button
        className="notification-all-delete-btn"
        onClick={() => deleteNotificationByMember(1)}
      >
        전체 삭제
      </button>
      <div>
        {notifications.length > 0 ? (
          notifications.map((notification) => (
            <div key={notification.alarmNo} className="notification">
              <button className="notification-main-btn">
                <div className="notification">
                  <div className="notification-icon">🔔</div>
                  <div>{notification.alarmContent}</div>
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
