import React, { useEffect, useState } from "react";
import "../../css/chatApp.css"; // CSS를 별도 파일로 분리
import ChattingMessage from "./Chatting.jsx";
import ChatRoom from "./ChatRoom.jsx";
import Alarim from "../Alarim.jsx";
import * as AlarmApi from "../../api/alarmApi.js";
import { getCookie } from "../../util/cookieUtil.js";

const ChatAlarim = () => {
  const [activePanel, setActivePanel] = useState(null); //패널을 열때 chat 또는 notification인지 구분하는 용도
  const [roomId, setRoomId] = useState(null); // 선택된 roomId 상태
  const [roomName, setRoomName] = useState(null); // 선택된 roomName 상태
  const [alarmCount, setAlarmCount] = useState(null);
  const memberCookie = getCookie("member");
  const token = memberCookie ? memberCookie.accessToken : null;

  const togglePanel = (panel) => {
    //chat 또는 notification를 확인하여 슬라이드 패널을 열고 닫음
    setActivePanel((prevPanel) => (prevPanel === panel ? null : panel));
  };
  const openChatting = (roomId, roomName) => {
    setRoomId(roomId); // 선택된 roomId 설정
    setRoomName(roomName); // 선택된 roomName 설정
    setActivePanel("ChattingMessage");
  };
  const alarmIsReadCount = async () => {
    const response = await AlarmApi.isReadAlarmCount(6);
    console.log(response.data);
    setAlarmCount(response.data);
  };
  useEffect(() => {
    const intervalId = setInterval(() => {
      alarmIsReadCount(); // 데이터를 가져오는 함수
    }, 5000); // 5000ms = 5초

    // 컴포넌트가 언마운트될 때 setInterval을 정리
    return () => clearInterval(intervalId);
  }, []);
  return (
    <div>
      {token != null ? (
        <>
          {/* Panel */}
          <div className={`chat-panel ${activePanel ? "open" : ""}`}>
            {activePanel === "chat" && <ChatRoom onRoomClick={openChatting} />}
            {activePanel === "notification" && <Alarim />}
            {activePanel === "ChattingMessage" && (
              <ChattingMessage roomId={roomId} roomName={roomName} />
            )}
          </div>
          {/* Chat Button */}
          <div
            className={`chat-button ${
              activePanel === "chat" ? "panel-open" : ""
            }`}
            onClick={() => togglePanel("chat")}
          >
            챗
          </div>

          {/* Notification Button */}
          <div
            className={`notification-button ${
              activePanel === "notification" ? "panel-open" : ""
            }`}
            onClick={() => togglePanel("notification")}
          >
            알림
            <span className="notification-dot">{alarmCount}</span>
          </div>
        </>
      ) : (
        <></>
      )}
    </div>
  );
};

export default ChatAlarim;
