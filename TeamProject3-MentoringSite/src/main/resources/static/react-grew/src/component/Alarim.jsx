import React, { useEffect, useState } from "react";
import * as alarmApi from "../api/alarmApi";
import "../css/styles.css";
const Alarim = () => {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    // API 호출 함수
    const fetchNotifications = async () => {
      const response = await alarmApi.findByMemberNo(1); // API 호출
      console.log(response);
      setNotifications(response.data); // 받은 데이터로 notifications 상태 업데이트
    };
    fetchNotifications(); // 컴포넌트 마운트 시 알림 데이터 가져오기
  }, []); // 빈 배열을 넣으면 컴포넌트가 마운트될 때만 실행됨
    const closeNotification = async()=>{
    }
  return (
    <div>
      <div className="notification-header">알림</div>
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
                      closeNotification(notification.alarmNo);
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
