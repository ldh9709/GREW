import React, { useState } from 'react';

const Alarim = () => {
    const [notifications, setNotifications] = useState([
        { id: 1, message: '새로운 알림 1' },
        { id: 2, message: '새로운 알림 2' },
        { id: 3, message: '새로운 알림 3' },
    ]);
    return(
        <div>
            <div className="chat-header">알림</div>
            <ul className="notification-list">
                {notifications.map((notification) => (
                    <li key={notification.id} className="notification-item">
                        {notification.message}
                    </li>
                ))}
            </ul>
        </div>
    );
};
export default Alarim;
