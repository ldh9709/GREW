import React, { useEffect, useState } from "react";
import "../../css/chatApp.css"; // CSS를 별도 파일로 분리
import ChattingMessage from "./Chatting.jsx";
import ChatRoom from "./ChatRoom.jsx";
import Alarim from "../Alarim.jsx";
import * as AlarmApi from "../../api/alarmApi.js";
import "../../css/chatApp.css"; // CSS를 별도 파일로 분리
import { useMemberAuth } from "../../util/AuthContext.js";

const ChatAlarim = () => {
  const [alarmCount, setAlarmCount] = useState(null);
  const [activePanel, setActivePanel] = useState(null); //패널을 열때 chat 또는 notification인지 구분하는 용도
  const [roomId, setRoomId] = useState(null); // 선택된 roomId 상태
  const [roomName, setRoomName] = useState(null); // 선택된 roomName 상태
  const { token, member } = useMemberAuth();

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
    if (member.memberNo) {
      const response = await AlarmApi.isReadAlarmCount(member.memberNo);
      console.log(response.data);
      setAlarmCount(response.data);
    }
  };
  useEffect(() => {
if(member){

    alarmIsReadCount(); // 데이터를 가져오는 함수
    const intervalId = setInterval(() => {
        alarmIsReadCount(); // 데이터를 가져오는 함수
    }, 3000); // 5000ms = 5초
    return () => clearInterval(intervalId);
}
  }, [member]);
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
            {alarmCount !== 0 && alarmCount != null ? (
              <span className="notification-dot">{alarmCount}</span>
            ) : (
              <span />
            )}
          </div>
        </>
      ) : (
        <></>
      )}
    </div>
  );
};

export default ChatAlarim;
