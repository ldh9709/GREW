import React, { useEffect, useRef, useState } from "react";
import "../../css/chatApp.css"; // CSS를 별도 파일로 분리
import SockJS from "sockjs-client";
import { Client as StompClient } from "@stomp/stompjs";
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
  const [roomStatus, setRoomStatus] = useState(null); // 선택된 roomStatus 상태
  const [mentorNo, setMentorNo] = useState(null); // 선택된 mentorNo 상태
  const { token, member } = useMemberAuth();
  let stompClient = useRef(null);
  // stompClient 초기화 및 WebSocket 연결
  
  useEffect(() => {
    if (roomId && member.memberName) {
      let socket;

      try {
        socket = new SockJS('http://localhost:8080/chat');
      } catch (error) {
        console.error('Failed to connect to localhost, trying ngrok...');
        socket = new SockJS('https://f8eb-175-123-27-55.ngrok-free.app/chat');
      }
      
      stompClient.current = new StompClient({
        webSocketFactory: () => socket,
        onConnect: () => {
          stompClient.current.subscribe(
            `/topic/messages/member/${member.memberNo}`,
            (response) => {
              // 서버에서 소켓내용을 받아옴
              const message = JSON.parse(response.body);
              if (!message.chatMessageDate) {
                message.chatMessageDate = new Date().toISOString(); // ISO 8601 형식으로 현재 시간 설정
              }
              const messageType =
                message.memberName === member.memberName ? "sent" : "received";
            }
          );
        },
        onDisconnect: () => console.log("Disconnected"),
      });

      stompClient.current.activate();

      return () => {
        if (stompClient.current) {
          stompClient.current.deactivate(); // 소켓이 연결되었다면 일을 끝낸 뒤 다시 연결 종료
        }
      };
    }
  }, [roomId]);

  const togglePanel = (panel) => {
    //chat 또는 notification를 확인하여 슬라이드 패널을 열고 닫음
    setActivePanel((prevPanel) => (prevPanel === panel ? null : panel));
  };
  const openChatting = (roomId, roomName, roomStatus, mentorNo) => {
    setRoomId(roomId); // 선택된 roomId 설정
    setRoomName(roomName); // 선택된 roomName 설정
    setRoomStatus(roomStatus);
    setMentorNo(mentorNo);
    setActivePanel("ChattingMessage");
  };
  const alarmIsReadCount = async () => {
    if (member.memberNo) {
      const response = await AlarmApi.isReadAlarmCount(member.memberNo);
      setAlarmCount(response.data);
    }
  };
  useEffect(() => {
    if (member) {
      alarmIsReadCount(); // 데이터를 가져오는 함수
    }
  }, []);

  return (
    <div>
      {member && member.memberNo != null ? (
        <>
          {/* Panel */}
          <div className={`chat-panel ${activePanel ? "open" : ""}`}>
            {activePanel === "chat" && <ChatRoom onRoomClick={openChatting} />}
            {activePanel === "notification" && <Alarim />}
            {activePanel === "ChattingMessage" && (
              <ChattingMessage roomId={roomId} roomName={roomName} roomStatus={roomStatus} mentorNo={mentorNo}/>
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
