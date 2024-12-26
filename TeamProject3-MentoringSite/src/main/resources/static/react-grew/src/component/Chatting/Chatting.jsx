import React, { useState, useEffect, useRef } from "react";
import SockJS from "sockjs-client";
import { Client as StompClient } from "@stomp/stompjs";
import { getCookie } from "../../util/cookieUtil.js";
import * as ChattingApi from "../../api/chattingApi.js";
import { jwtDecode } from "jwt-decode";

const ChattingMessage = ({ roomId, roomName }) => {
  const memberCookie = getCookie("member");
  const token = memberCookie ? memberCookie.accessToken : null;
  const decodeToken = token ? jwtDecode(token) : null;

  const [username, setUsername] = useState("");
  const [messageContent, setMessageContent] = useState("");
  const [messages, setMessages] = useState([]);
  const [selectedImage, setSelectedImage] = useState(null);
  const chatContainerRef = useRef(null);

  const [hasUnreadMessages, setHasUnreadMessages] = useState(false);
  let stompClient = useRef(null);

  const formatTime = (dateString) => {
    const date = new Date(dateString);
    const hours = date.getHours();
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const period = hours >= 12 ? "오후" : "오전";
    const formattedHours = hours % 12 || 12; // 0시는 12시로 표시
    return `${period} ${formattedHours}:${minutes}`;
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}년 ${month}월 ${day}일`;
  };

  // 채팅 메시지 로드
  const chatMessages = async (username) => {
    const responseJsonObject = await ChattingApi.viewChatMessage(roomId);
    console.log(responseJsonObject);

    const backMessage = responseJsonObject.data.map((msg) => ({
      memberName: msg.memberName,
      chatMessageDate: msg.chatMessageDate,
      chatMessageContent: msg.chatMessageContent,
      chatMessageCheck: msg.chatMessageCheck,
      type: msg.memberName === username ? "sent" : "received", // 메시지 유형 설정
      imageData: msg.imageData || null,
      chatMessageNo: msg.chatMessageNo,
    }));
    setMessages(backMessage);

    // 읽지 않은 메시지 중 자신의 메시지가 아닌 것만 필터링
    const unreadMessages = responseJsonObject.data.filter(
      (msg) => msg.chatMessageCheck === 1 && msg.memberName !== username
    );
    console.log(unreadMessages);

    // 읽음 상태 업데이트 요청
    if (unreadMessages.length > 0) {
      //안읽은 채팅이 있는지 확인
      Promise.all(
        //Promise.all은 배열이 완료될 때까지 기다린 뒤 .then 블록을 실행
        unreadMessages.map((msg) =>
          ChattingApi.readChatMessage(msg.chatMessageNo)
        )
      ).then(() => {
        // 읽음 상태를 업데이트한 메시지를 반영
        setHasUnreadMessages(true); // 상태 업데이트
        setMessages(
          (
            prevMessages //react객체안에 업데이트 된 정보를 담음
          ) =>
            prevMessages.map((msg) =>
              unreadMessages.some(
                (unreadMsg) => unreadMsg.chatMessageNo === msg.chatMessageNo
              )
                ? { ...msg, chatMessageCheck: 0 } // 읽음 상태로 변경
                : msg
            )
        );
      });
    }
  };

  useEffect(() => {
    const username = decodeToken.memberName;
    if (username) {
      console.log("username : " + username);
      setUsername(username); // 이름 설정
      setHasUnreadMessages(false); // 다시 초기화
    }
    chatMessages(username);
  }, [roomId, hasUnreadMessages]);

  // stompClient 초기화 및 WebSocket 연결
  useEffect(() => {
    if (!roomId || !username) {
      // roomId 또는 username이 null일 경우, 아무 작업도 하지 않음
      return;
    }
    console.log("입력받았었고 등록된 roomId : " + roomId);
    console.log("등록된 username : " + username);
    console.log("소켓검사 실시");
    if (roomId && username) {
      const socket = new SockJS(`http://localhost:8080/chat`);
      console.log("소켓 만들어짐");
      console.log("소켓에 stomp만들기");
      stompClient.current = new StompClient({
        webSocketFactory: () => socket,
        onConnect: () => {
          console.log("Connected");

          stompClient.current.subscribe(
            `/topic/messages/${roomId}`,
            (response) => {
              // 서버에서 소켓내용을 받아옴
              const message = JSON.parse(response.body);
              if (!message.chatMessageDate) {
                message.chatMessageDate = new Date().toISOString(); // ISO 8601 형식으로 현재 시간 설정
              }
              console.log("message : ");
              console.log(message);
              const messageType =
                message.memberName === username ? "sent" : "received";
              setMessages((prevMessages) => [
                ...prevMessages,
                {
                  chatMessageNo: message.chatMessageNo,
                  chatMessageContent: message.chatMessageContent,
                  chatMessageDate: message.chatMessageDate,
                  chatMessageCheck: message.chatMessageCheck,
                  memberName: message.memberName,
                  imageData: message.imageData || null,
                  type: messageType,
                },
              ]);
              // 읽음 상태 업데이트 요청
              if (message.memberName !== username) {
                stompClient.current.publish({
                  destination: `/app/chat/read/${roomId}`,
                  body: JSON.stringify(message),
                });
              }
            }
          );
          stompClient.current.subscribe(
            `/topic/read-status/${roomId}`,
            (response) => {
              const responseJsonObject = JSON.parse(response.body);
              console.log(responseJsonObject);
              chatMessages(username);
            }
          );
        },
        onDisconnect: () => console.log("Disconnected"),
      });
      console.log("stomp만들기 종료");

      stompClient.current.activate();
      console.log("클라이언트를 활성화하여 STOMP 연결을 시작합니다.");

      return () => {
        if (stompClient.current) {
          stompClient.current.deactivate(); // 소켓이 연결되었다면 일을 끝낸 뒤 다시 연결 종료
          console.log("WebSocket 연결을 종료");
        }
      };
    }
    console.log("소켓검사 종료");
  }, [roomId, username]);

  // 메시지 전송 후 스크롤 제어
  useEffect(() => {
    if (chatContainerRef.current) {
      chatContainerRef.current.scrollTop =
        chatContainerRef.current.scrollHeight;
    }
  }, [messages]); // 메시지가 추가될 때만 스크롤 이동

  // 이미지 데이터를 서버에서 가져오는 함수
  const fetchImageData = async (chatMessageNo) => {
    try {
      const response = await fetch(
        `http://localhost:8080/image/${chatMessageNo}`
      );
      if (response.ok) {
        const base64Image = await response.text();
        return base64Image;
      } else {
        console.error("이미지 로딩 실패");
        return null;
      }
    } catch (error) {
      console.error("이미지 요청 오류", error);
      return null;
    }
  };

  // 이미지 로딩 처리
  useEffect(() => {
    const loadImages = async () => {
      const updatedMessages = await Promise.all(
        messages.map(async (msg) => {
          if (msg.chatMessageContent === "이미지" && msg.chatMessageNo) {
            const base64Image = await fetchImageData(msg.chatMessageNo);
            return { ...msg, imageData: base64Image };
          }
          return msg;
        })
      );
      setMessages(updatedMessages);
    };

    loadImages();
  }, [messages.length]); // 메시지 배열의 길이가 바뀔 때만 실행하도록 변경

  // 메시지 전송 처리
  const handleSendMessage = (event) => {
    event.preventDefault();
    if (messageContent.trim() && stompClient.current) {
      const message = {
        chatMessageNo: 0,
        chatMessageContent: messageContent,
        chatMessageDate: "",
        chatMessageCheck: 1,
        memberNo: decodeToken.memberNo,
        memberName: username,
        chatRoomNo: roomId,
      };
      stompClient.current.publish({
        destination: `/app/chat/${roomId}`,
        body: JSON.stringify(message),
      });
      setMessageContent(""); // 전송 후 입력 필드 초기화
    }
  };

  // 이미지 전송 처리
  const handleSendImage = (event) => {
    event.preventDefault();
    if (selectedImage && stompClient.current) {
      const reader = new FileReader();
      reader.onloadend = () => {
        const base64Image = reader.result.split(",")[1];
        console.log("Base64 Image:", base64Image);

        const imageData = {
          imageBlob: base64Image,
          chatMessageNo: Date.now(),
          chatRoomNo: roomId,
          memberNo: decodeToken.memberNo,
        };

        stompClient.current.publish({
          destination: `/app/sendImage/${roomId}`,
          body: JSON.stringify(imageData),
        });
        // 채팅 메시지로 추가
        setMessages((prevMessages) => [
          ...prevMessages,
          {
            chatMessageNo: imageData.chatMessageNo,
            chatMessageContent: "이미지",
            chatMessageDate: new Date().toISOString(),
            chatMessageCheck: 1,
            memberName: username,
            imageData: base64Image,
            type: "sent",
          },
        ]);
      };
      reader.readAsDataURL(selectedImage);
    }
  };

  // 이미지 파일 선택 처리
  const handleImageSelect = (event) => {
    const file = event.target.files[0];
    if (file) {
      setSelectedImage(file);
    }
  };

  let lastDate = null; // 마지막 메시지의 날짜

  if (username && roomId) {
    return (
      <div className="chat-app">
        <div className="chat-header">{roomName}</div>

        <div
          id="chat-container"
          ref={chatContainerRef}
          className="chat-container"
        >
          {messages.length === 0 ? (
            <div className="no-messages">채팅을 시작해보세요</div>
          ) : (
            messages.map((msg, index) => {
              const currentDate = formatDate(msg.chatMessageDate);
              const isNewDate = lastDate !== currentDate;
              lastDate = currentDate;

              return (
                <div key={index} className="chat-message-container">
                  {/* 날짜 변경 시 새로운 날짜 표시 */}
                  {isNewDate && (
                    <div className="chat-message-date">{currentDate}</div>
                  )}
                  {/* 읽음/안 읽음 상태 표시 */}
                  <span className={`chat-message-status-${msg.type}`}>
                    {msg.chatMessageCheck === 1 ? "1" : null}
                  </span>
                  <div className={`chat-message-${msg.type}`}>
                    {msg.type === "sent"
                      ? `${msg.memberName}: ${msg.chatMessageContent}`
                      : `${msg.memberName}: ${msg.chatMessageContent}`}
                  </div>
                  {/* 이미지가 포함된 메시지 처리 */}
                  {msg.chatMessageContent === "이미지" && msg.imageData && (
                    <div>
                      <img
                        src={`data:image/png;base64,${msg.imageData}`}
                        alt="chat image"
                        className="chat-image"
                      />
                    </div>
                  )}
                  <div className={`chat-message-time-${msg.type}`}>
                    {formatTime(msg.chatMessageDate)}
                  </div>
                </div>
              );
            })
          )}
        </div>
        {/* 전송을 누르면 handleSendMessage를 호출 */}
        <form
          id="message-form"
          className="message-form"
          onSubmit={handleSendMessage}
        >
          <label htmlFor="image-upload" className="image-upload-label">
            <img
              src="https://cdn.icon-icons.com/icons2/2348/PNG/512/image_picture_icon_143003.png"
              className="image-icon"
              alt="image icon"
            />
          </label>
          <input
            type="file"
            id="image-upload"
            className="image-upload"
            onChange={handleImageSelect}
            accept="image/*"
          />
          <input
            type="text"
            id="message"
            className="message-input"
            placeholder="메시지를 입력하세요..."
            value={messageContent}
            onChange={(e) => setMessageContent(e.target.value)}
          />
          <button type="submit" id="send" className="send-button">
            전송
          </button>
          <button
            type="button"
            className="send-image-button"
            onClick={handleSendImage}
          >
            이미지 전송
          </button>
        </form>
      </div>
    );
  }
};

export default ChattingMessage;
