import React from 'react';

export const UserCard = () => {
  // 사용자 데이터 예시
  const userData = {
    id: 1,
    name: '홍길동',
    userId: 'hong123',
    email: 'hong123@example.com',
    joinDate: '2024-06-01',
    status: '활동중',
    role: '관리자',
    reports: 5,
  };

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        {/* 아이콘 및 정보 */}
        <div style={styles.item}>
          <span style={styles.icon}>🔢</span> 번호: {userData.id}
        </div>
        <div style={styles.item}>
          <span style={styles.icon}>👤</span> 이름: {userData.name}
        </div>
        <div style={styles.item}>
          <span style={styles.icon}>🆔</span> 아이디: {userData.userId}
        </div>
        <div style={styles.item}>
          <span style={styles.icon}>📧</span> 이메일: {userData.email}
        </div>
        <div style={styles.item}>
          <span style={styles.icon}>📅</span> 가입일자: {userData.joinDate}
        </div>
        <div style={styles.item}>
          <span style={styles.icon}>🔵</span> 회원 상태: {userData.status}
        </div>
        <div style={styles.item}>
          <span style={styles.icon}>⚙️</span> 역할 상태: {userData.role}
        </div>
        <div style={styles.item}>
          <span style={styles.icon}>🚨</span> 신고 횟수: {userData.reports}
        </div>
      </div>
    </div>
  );
};

const styles = {
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
    backgroundColor: '#f9f9f9',
  },
  card: {
    width: '400px',
    backgroundColor: '#fff',
    border: '1px solid #ddd',
    borderRadius: '10px',
    padding: '20px',
    boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
    display: 'flex',
    flexDirection: 'column',
    gap: '10px',
  },
  item: {
    display: 'flex',
    alignItems: 'center',
    fontSize: '16px',
    color: '#333',
  },
  icon: {
    marginRight: '10px',
    fontSize: '18px',
  },
};

export default UserCard;