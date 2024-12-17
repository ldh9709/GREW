import React, { useState } from 'react';

const ChatRoom = ({ onRoomClick }) => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [rooms, setRooms] = useState([
        { id: 1, name: '채팅방 1' },
        { id: 2, name: '채팅방 2' },
        { id: 3, name: '채팅방 3' },
    ]);
    
    const [currentRoom, setCurrentRoom] = useState(null);
    const [newRoomName, setNewRoomName] = useState('');
    const openEditModal = (room) => {
        setCurrentRoom(room);
        setNewRoomName(room.name);
        setIsModalOpen(true);
    };

    const saveRoomName = () => {
        if (currentRoom && newRoomName.trim()) {
            setRooms((prevRooms) =>
                prevRooms.map((room) =>
                    room.id === currentRoom.id
                        ? { ...room, name: newRoomName.trim() }
                        : room
                )
            );
            setIsModalOpen(false);
        }
    };
    

    return (
        <div>
            {/* Panel */}
            <div>
                <div className="chat-header">채팅방 목록</div>
                <ul className="chat-room-list">
                    {rooms.map((room) => (
                        <li
                            key={room.id}
                            className="chat-room-item"
                            onClick={() => onRoomClick(room.id)}
                        >
                            <span className="room-name">{room.name}</span>
                            <button
                                className="edit-button"
                                onClick={(e) => {
                                    e.stopPropagation();
                                    openEditModal(room);
                                }}
                            >
                                수정
                            </button>
                        </li>
                    ))}
                </ul>
            </div>

            {/* Modal for Editing Room Name */}
            {isModalOpen && (
                <div className={`modal ${isModalOpen ? 'active' : ''}`}>
                    <div
                        className="modal-content"
                        onClick={(e) => e.stopPropagation()}
                    >
                        <div className="modal-header">채팅방 이름 변경</div>
                        <input
                            type="text"
                            value={newRoomName}
                            onChange={(e) => setNewRoomName(e.target.value)}
                            placeholder="새 채팅방 이름"
                        />
                        <button onClick={saveRoomName}>저장</button>
                        <button
                            className="close-button"
                            onClick={() => setIsModalOpen(false)} // 닫기 버튼 추가
                        >
                            닫기
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ChatRoom;
