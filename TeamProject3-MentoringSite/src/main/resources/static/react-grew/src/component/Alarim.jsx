import React, { useEffect, useState } from "react";
import * as alarmApi from "../api/alarmApi";
const Alarim = () => {
  const [notifications, setNotifications] = useState([
    { id: 1, message: "새로운 알림 1" },
    { id: 2, message: "새로운 알림 2" },
    { id: 3, message: "새로운 알림 3" },
  ]);

  useEffect(() => {
    // API 호출 함수
    const fetchNotifications = async () => {
        const response = await alarmApi.findByMemberNo(1); // API 호출
        console.log(response);
        setNotifications(response); // 받은 데이터로 notifications 상태 업데이트
      }
    fetchNotifications(); // 컴포넌트 마운트 시 알림 데이터 가져오기
  }, []); // 빈 배열을 넣으면 컴포넌트가 마운트될 때만 실행됨

  return (
    <div>
            <div className="chat-header">알림</div>
            <div>
                {notifications.length > 0 ? (
                    <ul>
                        {/* {notifications.map((notification) => (
                            <li key={notification.alarmNo}>.......</li>
                        ))} */}
                    </ul>
                ) : (
                    <div>알림이 없습니다.</div>
                )}
            </div>
        </div>
  );
};
export default Alarim;
