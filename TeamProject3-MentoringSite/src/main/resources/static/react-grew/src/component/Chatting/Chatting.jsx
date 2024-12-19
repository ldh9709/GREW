import React, { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Client as StompClient } from '@stomp/stompjs';
import { useNavigate } from 'react-router-dom';
import { getCookie } from "../../util/cookieUtil.js";

const ChattingMessage = ({ roomId }) => {
  const navigate = useNavigate(); // 리다이렉트를 위해 사용
  const [username, setUsername] = useState('');
  const [messageContent, setMessageContent] = useState('');
  const [messages, setMessages] = useState([]);
  const chatContainerRef = useRef(null);
  const memberCookie = getCookie("member");
  const token = memberCookie.accessToken;
  let stompClient = useRef(null);

  useEffect(() => {
    if (!roomId) {
        alert('방 번호가 존재하지 않습니다.');
        navigate('/'); // 유효하지 않은 경우 즉시 리다이렉트
        return;
    }
    console.log('주소에서 받은 roomIdParam : '+roomId);
  }, []); // 빈 배열 -> 한 번만 실행

  useEffect(() => {
    if (!roomId) {
        // roomId 또는 username이 null일 경우, 아무 작업도 하지 않음
        return;
    }
    console.log('등록된 roomId : '+roomId);
    if (roomId) {
        const enteredUsername = prompt('이름을 입력해주세요:', '');
        if (enteredUsername) {
            console.log('입력한 enteredUsername : '+enteredUsername);
            setUsername(enteredUsername); // 이름 설정
        } else {
            alert('이름을 입력해야 합니다.');
            navigate(`/`); // 이름을 입력하지 않으면 리다이렉트
        }
    }
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
        const socket = new SockJS('http://localhost:8080/chat');
        console.log('소켓 만들어짐');
        console.log('소켓에 stomp만들기');
        stompClient.current = new StompClient({
            webSocketFactory: () => socket,
            onConnect: () => {
                console.log('Connected');
                
                stompClient.current.subscribe(`/topic/messages/${roomId}`, (response) => {
                    const message = JSON.parse(response.body);
                    const messageType = message.memberName === username ? 'sent' : 'received';
                    setMessages((prevMessages) => [
                        ...prevMessages,
                        { memberName: message.memberName, chatContent: message.chatContent, type: messageType },
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

  const handleSendMessage = (event) => {
    event.preventDefault();
    if (messageContent.trim() && stompClient.current) {
      const message = {
        chatMessageNo: 0,
        chatContent: messageContent,
        chatMessageDate: '',
        chatMessagerCheck: 0,
        memberNo: 1,
        memberName: username,
        chatRoomNo : roomId
      };

      stompClient.current.publish({ destination: `/app/chat/${roomId}`, body: JSON.stringify(message) });
      setMessageContent('');
    }
  };

  if (roomId || username) {
    return (
        <div className="chat-app">
        <h2>1:1 Chat Application</h2>

        <div id="chat-container" ref={chatContainerRef} className="chat-container">
            {messages.map((msg, index) => (
            <div key={index} className={`chat-message ${msg.type}`}>
                {msg.type === 'sent' ? `${msg.memberName}: ${msg.chatContent}` : `${msg.memberName}: ${msg.chatContent}`}
            </div>
            ))}
        </div>

        <form id="message-form" className="message-form" onSubmit={handleSendMessage}>
            <input
            type="text"
            id="message"
            className="message-input"
            placeholder="Type a message..."
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
