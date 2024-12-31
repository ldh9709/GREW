import React, { useEffect, useState } from "react";
import * as alarmApi from "../api/alarmApi";
import "../css/styles.css";
import { useNavigate } from "react-router-dom";
import { useMemberAuth } from "../util/AuthContext";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
const Alarim = () => {
  const { token, member } = useMemberAuth();
  const [notifications, setNotifications] = useState([]);
  const navigate = useNavigate();

  const fetchNotifications = async () => {
    if (member) {
      const response = await alarmApi.findByMemberNo(member.memberNo); // API 호출
      setNotifications(response.data); // 받은 데이터로 notifications 상태 업데이트
    } else {
      setNotifications(null);
    }
  };
  useEffect(() => {
    setInterval(fetchNotifications, 10000); // 5초마다 폴링
    fetchNotifications();
  }, []);
  const allIsReadNotificationByMember = async (memberNo) => {
    await alarmApi.isReadAllAlarm(memberNo);

    fetchNotifications();
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
    const response = await alarmApi.findAlarm(alarmNo);
    if(response.data.referenceType!=null){
      const responsejsonObject = await alarmApi.urlAlarm(alarmNo);
      navigate(responsejsonObject.data);
    }
    fetchNotifications();

  };
  return (
    <div>
      <div className="notification-header">알림</div>
      <div className="all-delete-btn-div">
        <button
          className="notification-all-delete-btn"
          onClick={() => allIsReadNotificationByMember(member.memberNo)}
        >
          전체 읽음
        </button>
        <button
          className="notification-all-delete-btn"
          onClick={() => deleteNotificationByMember(member.memberNo)}
        >
          전체 삭제
        </button>
      </div>
      <div className="alarm-main">
        {member && notifications.length > 0 ? (
          notifications.map((notification) => (
            <div key={notification.alarmNo} className="notification">
              {notification.isRead === 1 ? (
                <button
                  className="notification-main-btn"
                  onClick={() => {
                    handleAlarmButton(notification.alarmNo);
                  }}
                >
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
              ) : (
                <button
                  className="notification-main-btn-isread"
                  onClick={() => {
                    handleAlarmButton(notification.alarmNo);
                  }}
                >
                  <div className="notification">
                    <div className="notification-icon">🔔</div>
                    <div className="notification-content">
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
                    <FontAwesomeIcon icon={faXmark} />
                  </div>
                </button>
              )}
            </div>
          ))
        ) : (
          <div>
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            알림이 없습니다.
          </div>
        )}
      </div>
    </div>
  );
};
export default Alarim;
