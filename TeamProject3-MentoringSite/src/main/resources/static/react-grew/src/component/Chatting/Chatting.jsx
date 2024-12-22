import React, { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Client as StompClient } from '@stomp/stompjs';
import { getCookie } from "../../util/cookieUtil.js";
import * as ChattingApi from '../../api/chattingApi.js';

const ChattingMessage = ({ roomId, roomName }) => {
  const memberCookie = getCookie("member");
  const [username, setUsername] = useState('');
  const [messageContent, setMessageContent] = useState('');
  const [messages, setMessages] = useState([]);
  const chatContainerRef = useRef(null);
  const [imagePreview, setImagePreview] = useState(null);
  let stompClient = useRef(null);
  
  const chatMessages = async (username) => {
    const responseJsonObject = await ChattingApi.viewChatMessage(roomId);
    console.log(responseJsonObject);
    console.log('username : '+username);
      const backMessage = responseJsonObject.data.map((msg) => ({
        memberName: msg.memberName,
        chatMessageContent: msg.chatMessageContent,
        type: msg.memberName === username ? 'sent' : 'received', // 메시지 유형 설정
      }));
      setMessages(backMessage);
  }
  
  useEffect(() => {
    const username = memberCookie.memberName;
    if (username) {
      console.log('username : '+username);
      setUsername(username); // 이름 설정
    } 
    chatMessages(username);
  }, [roomId]);

  useEffect(() => {
    if (!roomId || !username) {
        // roomId 또는 username이 null일 경우, 아무 작업도 하지 않음
        return;
    }
    console.log('입력받았었고 등록된 roomId : '+roomId);
    console.log('등록된 username : '+username);
    console.log('소켓검사 실시');
    if (roomId && username) {
        const socket = new SockJS(`http://localhost:8080/chat`);
        console.log('소켓 만들어짐');
        console.log('소켓에 stomp만들기');
        stompClient.current = new StompClient({
            webSocketFactory: () => socket,
            onConnect: () => {
              console.log('Connected');
              
              stompClient.current.subscribe(`/topic/messages/${roomId}`, (response) => { // 서버에서 소켓내용을 받아옴
                const message = JSON.parse(response.body);
                console.log(message);
                const messageType = message.memberName === username ? 'sent' : 'received';
                setMessages((prevMessages) => [
                  ...prevMessages,
                  { memberName: message.memberName, chatMessageContent: message.chatMessageContent, type: messageType },
                ]);
              });
            },
            onDisconnect: () => console.log('Disconnected'),
        });
        console.log('stomp만들기 종료');
        
        stompClient.current.activate();
        console.log('클라이언트를 활성화하여 STOMP 연결을 시작합니다.');
        
        return () => {
          if (stompClient.current) {
            stompClient.current.deactivate();  // 소켓이 연결되었다면 일을 끝낸 뒤 다시 연결 종료
            console.log('WebSocket 연결을 종료');
          }
        };
    }
    console.log('소켓검사 종료');
  }, [roomId, username]);

  useEffect(() => {
    // 채팅창 스크롤 자동 이동
    if (chatContainerRef.current) {
      chatContainerRef.current.scrollTop = chatContainerRef.current.scrollHeight;
    }
  }, [messages]);

  const handleImageUpload = (e) => {
    const file = e.target.files[0]; // 파일이 여러 개일 수 있지만, 여기서는 첫 번째 파일만 처리

    if (file) {
      const reader = new FileReader();
      
      reader.onloadend = () => {
          // 여기서는 이미지를 미리보기 하기 위한 방법
          const imagePreviewUrl = reader.result;
          console.log("Selected image: ", imagePreviewUrl);
          
          // 이미지 미리보기 출력 (선택적으로 화면에 미리보기)
          setImagePreview(imagePreviewUrl);
      };

      reader.readAsDataURL(file); // 파일을 데이터 URL로 읽기
    }
  };

  const handleSendMessage = (event) => {
    event.preventDefault();
    if (messageContent.trim() && stompClient.current) {
      const message = {
        chatMessageNo: 0,
        chatMessageContent: messageContent,
        chatMessageDate: '',
        chatMessagerCheck: 0,
        memberNo: memberCookie.memberNo,
        memberName: username,
        chatRoomNo : roomId
      };

      stompClient.current.publish({ destination: `/app/chat/${roomId}`, body: JSON.stringify(message) }); //서버로 채팅내용을 소켓으로 보냄
      setMessageContent('');
    }
  };

  if (username && roomId){
    return (
        <div className="chat-app">
          <div className="chat-header">{roomName}</div>

          <div id="chat-container" ref={chatContainerRef} className="chat-container">
            {messages.length === 0 ? (
            <div className="no-messages">채팅을 시작해보세요</div>
          ) : (
            messages.map((msg, index) => (
                <div key={index} className={`chat-message ${msg.type}`}>
                    {msg.type === 'sent' ? `${msg.memberName}: ${msg.chatMessageContent}` : `${msg.memberName}: ${msg.chatMessageContent}`}
                </div>
            ))
          )}
          </div>
          {/* 전송을 누르면 handleSendMessage를 호출 */}
          <form id="message-form" className="message-form" onSubmit={handleSendMessage}> 
            <label htmlFor="image-upload" className="image-upload-label">
              <img src="https://cdn.icon-icons.com/icons2/2348/PNG/512/image_picture_icon_143003.png" className="image-icon" />
            </label>
            <input
              type="file"
              id="image-upload"
              className="image-upload"
              onChange={handleImageUpload}
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
          </form>
        </div>
    );
  };
};

export default ChattingMessage;
