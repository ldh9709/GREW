import React, { useState, useEffect, useRef } from "react";
import SockJS from "sockjs-client";
import { Client as StompClient } from "@stomp/stompjs";
import { getCookie } from "../../util/cookieUtil.js";
import * as ChattingApi from "../../api/chattingApi.js";

const ChattingMessage = ({ roomId, roomName }) => {
  const memberCookie = getCookie("member");
  const [username, setUsername] = useState("");
  const [messageContent, setMessageContent] = useState("");
  const [messages, setMessages] = useState([]);
  const [selectedImage, setSelectedImage] = useState(null);
  const [isStompConnected, setIsStompConnected] = useState(false);
  const chatContainerRef = useRef(null);
  const stompClient = useRef(null);

  // 채팅 메시지 로드
  const chatMessages = async (username) => {
    const responseJsonObject = await ChattingApi.viewChatMessage(roomId);
    const backMessage = responseJsonObject.data.map((msg) => ({
      memberName: msg.memberName,
      chatMessageContent: msg.chatMessageContent,
      type: msg.memberName === username ? "sent" : "received",
      imageData: msg.imageData || null,
      chatMessageNo: msg.chatMessageNo,
    }));
    setMessages(backMessage);
  };

  useEffect(() => {
    const username = memberCookie.memberName;
    if (username) {
      setUsername(username);
    }
    chatMessages(username);
  }, [roomId]); // roomId가 변경될 때만 호출

  // stompClient 초기화 및 WebSocket 연결
  useEffect(() => {
    if (!roomId || !username) return;

    const socket = new SockJS("http://localhost:8080/chat");
    stompClient.current = new StompClient({
      webSocketFactory: () => socket,
      onConnect: () => {
        console.log("WebSocket connected");
        setIsStompConnected(true);

        stompClient.current.subscribe(
          `/topic/messages/${roomId}`,
          (response) => {
            const message = JSON.parse(response.body);
            const messageType =
              message.memberName === username ? "sent" : "received";
            setMessages((prevMessages) => [
              ...prevMessages,
              {
                memberName: message.memberName,
                chatMessageContent: message.chatMessageContent,
                type: messageType,
                imageData: message.imageData || null,
                chatMessageNo: message.chatMessageNo,
              },
            ]);
          }
        );
      },
      onDisconnect: () => {
        console.log("WebSocket disconnected");
        setIsStompConnected(false);
      },
    });

    stompClient.current.activate();

    return () => {
      if (stompClient.current) {
        stompClient.current.deactivate();
      }
    };
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
    if (messageContent.trim() && stompClient.current && isStompConnected) {
      const message = {
        chatMessageNo: 0,
        chatMessageContent: messageContent,
        chatMessageDate: "",
        chatMessagerCheck: 0,
        memberNo: memberCookie.memberNo,
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
    if (selectedImage && stompClient.current && isStompConnected) {
      const reader = new FileReader();
      reader.onloadend = () => {
        const base64Image = reader.result.split(",")[1];

        const imageData = {
          imageBlob: base64Image,
          chatMessageNo: Date.now(),
          chatRoomNo: roomId,
          memberNo: memberCookie.memberNo,
        };

        stompClient.current.publish({
          destination: `/app/sendImage/${roomId}`,
          body: JSON.stringify(imageData),
        });
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
          messages.map((msg, index) => (
            <div key={index} className={`chat-message ${msg.type}`}>
              {msg.type === "sent"
                ? `${msg.memberName}: ${msg.chatMessageContent}`
                : `${msg.memberName}: ${msg.chatMessageContent}`}

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
            </div>
          ))
        )}
      </div>

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
};

export default ChattingMessage;
