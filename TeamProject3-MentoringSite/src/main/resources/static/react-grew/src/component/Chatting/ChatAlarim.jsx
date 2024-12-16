import React, { useState } from 'react';
import '../../css/ChatApp.css'; // CSS를 별도 파일로 분리
import ChattingMessage from './Chatting.jsx';
import ChatRoom from './ChatRoom.jsx';
import Alarim from '../Alarim.jsx';
import { Routes, Route, useNavigate } from 'react-router-dom';

const ChatAlarim = () => {
    const [activePanel, setActivePanel] = useState(null); //패널을 열때 chat 또는 notification인지 구분하는 용도

    const togglePanel = (panel) => {    //chat 또는 notification를 확인하여 슬라이드 패널을 열고 닫음
        navigate('/chatting/');
        setActivePanel((prevPanel) => (prevPanel === panel ? null : panel));
    };
    const navigate = useNavigate();
    const openChatting = (roomId) => {
        navigate(`/chatting/${roomId}`);
    };

    return (
        <div>
            {/* Panel */}
            <div className={`chat-panel ${activePanel ? 'open' : ''}`}>
                {activePanel === 'chat' && (
                    <Routes>
                        <Route path="/chatting/" element={<ChatRoom onRoomClick={openChatting} />} />
                        <Route path="/chatting/:roomId" element={<ChattingMessage />} />
                    </Routes>
                )}
                {activePanel === 'notification' && (
                    <Alarim/>
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