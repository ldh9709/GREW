import React, { useState } from 'react';
import '../../css/ChatApp.css'; // CSS를 별도 파일로 분리
import ChattingMessage from './Chatting.jsx';
import ChatRoom from './ChatRoom.jsx';
import Alarim from '../Alarim.jsx';

const ChatAlarim = () => {
    const [activePanel, setActivePanel] = useState(null); //패널을 열때 chat 또는 notification인지 구분하는 용도
    const [roomId, setRoomId] = useState(null); // 선택된 roomId 상태

    const togglePanel = (panel) => {    //chat 또는 notification를 확인하여 슬라이드 패널을 열고 닫음
        setActivePanel((prevPanel) => (prevPanel === panel ? null : panel));
    };
    const openChatting = (roomId) => {
        setRoomId(roomId); // 선택된 roomId 설정
        setActivePanel('ChattingMessage');
    };
 
    return (
        <div>
            {/* Panel */}
            <div className={`chat-panel ${activePanel ? 'open' : ''}`}>
                {activePanel === 'chat' && (
                    <ChatRoom onRoomClick={openChatting}/>
                )}
                {activePanel === 'notification' && (
                    <Alarim/>
                )}
                {activePanel === 'ChattingMessage' && (
                    <ChattingMessage roomId={roomId}/>
                )}
            </div>

            {/* Chat Button */}
            <div
                className={`chat-button ${activePanel === 'chat' ? 'panel-open' : ''}`}
                onClick={() => togglePanel('chat')}
            >
                챗
            </div>

            {/* Notification Button */}
            <div
                className={`notification-button ${activePanel === 'notification' ? 'panel-open' : ''}`}
                onClick={() => togglePanel('notification')}
            >
                알림
            </div>

        </div>
    );
};

export default ChatAlarim;