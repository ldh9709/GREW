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
      setUsername(username); // 이름 설정
      setHasUnreadMessages(false); // 다시 초기화
    }
    chatMessages(username);
  }, [roomId, hasUnreadMessages]);

  // stompClient 초기화 및 WebSocket 연결
  useEffect(() => {
    if (!roomId || !username) return;

    const socket = new SockJS(`http://localhost:8080/chat`);
    stompClient.current = new StompClient({
      webSocketFactory: () => socket,
      onConnect: () => {
        stompClient.current.subscribe(
          `/topic/messages/${roomId}`,
          (response) => {
            const message = JSON.parse(response.body);
            if (!message.chatMessageDate) {
              message.chatMessageDate = new Date().toISOString();
            }

            const messageType =
              message.memberName === username ? "sent" : "received";

            // 이전 메시지 배열을 기반으로 새로운 메시지를 추가하는 방식
            setMessages((prevMessages) => [
              ...prevMessages,
              {
                ...message,
                type: messageType,
                isImageLoaded: !!message.imageData, // 이미지가 있으면 로드된 상태로 설정
              },
            ]);
          }
        );
      },
      onDisconnect: () => console.log("Disconnected"),
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

    // STOMP 연결 확인
    if (!stompClient.current || !stompClient.current.connected) {
      console.error("STOMP 연결이 되지 않았습니다.");
      alert("서버와의 연결이 끊어졌습니다. 다시 시도해주세요.");
      return;
    }

    if (selectedImage) {
      const reader = new FileReader();
      reader.onloadend = () => {
        const img = new Image();
        img.onload = () => {
          // 최대 파일 크기 (5MB 이하로 제한)
          const MAX_FILE_SIZE_KB = 50;
          const imageSizeInMB = selectedImage.size / (128 * 128); // 이미지 크기(MB)

          if (imageSizeInMB > MAX_FILE_SIZE_KB) {
            alert(
              "이미지가 너무 큽니다. 최대 50KB 이하의 이미지만 업로드 가능합니다."
            );
            return;
          }

          // 리사이즈할 크기 설정 (예: 최대 500px)
          const MAX_WIDTH = 500;
          const MAX_HEIGHT = 500;

          const canvas = document.createElement("canvas");
          const ctx = canvas.getContext("2d");

          let width = img.width;
          let height = img.height;

          // 비율 유지하며 크기 조정
          if (width > height) {
            if (width > MAX_WIDTH) {
              height = Math.round((height * MAX_WIDTH) / width);
              width = MAX_WIDTH;
            }
          } else {
            if (height > MAX_HEIGHT) {
              width = Math.round((width * MAX_HEIGHT) / height);
              height = MAX_HEIGHT;
            }
          }

          canvas.width = width;
          canvas.height = height;
          ctx.drawImage(img, 0, 0, width, height);

          const base64Image = canvas.toDataURL("image/png"); // 리사이즈된 이미지
          console.log("Resized Base64 Image:", base64Image);

          const imageData = {
            imageBlob: base64Image.split(",")[1],
            chatRoomNo: roomId,
            memberNo: decodeToken.memberNo,
          };

          // 이미지 전송
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
              imageData: imageData.imageBlob,
              type: "sent",
            },
          ]);
        };
        img.src = reader.result;
      };
      reader.readAsDataURL(selectedImage); // 이미지를 base64로 읽어오기
    }
  };
  // 이미지 데이터 서버에서 가져오기
  const fetchImageData = async (chatMessageNo, retryCount = 0) => {
    try {
      const response = await fetch(
        `http://localhost:8080/image/${chatMessageNo}`
      );
      if (response.ok) {
        const base64Image = await response.text();
        return base64Image;
      } else {
        console.error("이미지 로딩 실패");
        if (retryCount < 3) {
          // 실패 시 1초 후에 재시도
          await new Promise((resolve) => setTimeout(resolve, 500));
          return fetchImageData(chatMessageNo, retryCount + 1); // 재시도
        }
        return null; // 재시도 3번 실패시 null 반환
      }
    } catch (error) {
      console.error("이미지 요청 오류", error);
      if (retryCount < 3) {
        await new Promise((resolve) => setTimeout(resolve, 500));
        return fetchImageData(chatMessageNo, retryCount + 1); // 재시도
      }
      return null;
    }
  };

  // 메시지 업데이트 시 이미지 로딩 처리
  useEffect(() => {
    const loadImages = async () => {
      const updatedMessages = await Promise.all(
        messages.map(async (msg) => {
          if (msg.chatMessageContent === "이미지" && msg.chatMessageNo) {
            if (!msg.imageData) {
              // 이미지를 로딩 중으로 설정
              msg.isLoadingImage = true;
            }
            const base64Image = await fetchImageData(msg.chatMessageNo);
            if (base64Image) {
              return { ...msg, imageData: base64Image, isLoadingImage: false }; // 로딩 완료
            }
          }
          return msg; // 이미지가 없으면 그대로 반환
        })
      );
      setMessages(updatedMessages); // 이미지가 업데이트된 메시지 배열을 상태로 설정
    };

    loadImages(); // 이미지 로딩 실행
  }, [messages.length]); // 메시지 배열의 길이가 바뀔 때마다 실행
  // 이미지 파일 선택 처리
  const handleImageSelect = (event) => {
    const file = event.target.files[0];
    const MAX_FILE_SIZE_KB = 50000;
    if (file && file.size > MAX_FILE_SIZE_KB) {
      console.log(file.size);
      alert("이미지 크기가 너무 큽니다. 50KB 이하의 이미지를 선택해주세요.");
    } else {
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
                  {/* 텍스트 메시지 처리 */}
                  {msg.chatMessageContent !== "이미지" ? (
                    <div className={`chat-message-${msg.type}`}>
                      {msg.type === "sent"
                        ? `${msg.memberName}: ${msg.chatMessageContent}`
                        : `${msg.memberName}: ${msg.chatMessageContent}`}
                    </div>
                  ) : null}

                  {/* 이미지 처리 */}
                  {msg.chatMessageContent === "이미지" && msg.imageData ? (
                    <div className={`chat-image-container-${msg.type}`}>
                      {msg.isLoadingImage ? (
                        <div className="loading-spinner">로딩 중...</div> // 로딩 중일 때 스피너 또는 메시지
                      ) : (
                        <img
                          src={`data:image/png;base64,${msg.imageData}`}
                          alt="chat image"
                          className="chat-image"
                        />
                      )}
                    </div>
                  ) : null}
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
