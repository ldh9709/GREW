import React, { useState } from 'react';
import ChatRoomList from './SlideList/ChatRoomList.jsx';
import ChatRequests from './SlideList/ChatRequests.jsx';

const ChatRoom = ({ onRoomClick }) => {
    const [activeTab, setActiveTab] = useState('rooms'); // 'rooms' 또는 'requests'

    return (
        <div>
            {/* Panel */}
            <div className="chat-header">
                <div
                className={`chat-tab ${activeTab === 'rooms' ? 'active' : ''}`}
                onClick={() => setActiveTab('rooms')}
                >
                채팅방 목록
                </div>
                <div
                className={`chat-tab ${activeTab === 'requests' ? 'active' : ''}`}
                onClick={() => setActiveTab('requests')}
                >
                채팅방 신청 목록
                </div>
            </div>
            {activeTab === 'rooms' ? (
                <ChatRoomList onRoomClick={onRoomClick}/> // 채팅방 목록 컴포넌트
            ) : (
                <ChatRequests /> // 채팅방 신청 목록 컴포넌트
            )}
        </div>
    );
};

export default ChatRoom;