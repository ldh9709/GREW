import React from 'react';

const UserCard = () => {
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

    id: 2,
    name: '고길동',
    userId: 'hong123',
    email: 'hong123@example.com',
    joinDate: '2024-06-01',
    status: '활동중',
    role: '관리자',
    reports: 5,

    id: 1,
    name: '이창섭',
    userId: 'hong123',
    email: 'hong123@example.com',
    joinDate: '2024-06-01',
    status: '활동중',
    role: '관리자',
    reports: 5,

    id: 1,
    name: '전과자',
    userId: 'hong123',
    email: 'hong123@example.com',
    joinDate: '2024-06-01',
    status: '활동중',
    role: '관리자',
    reports: 5,

    id: 1,
    name: '스낵이들',
    userId: 'hong123',
    email: 'hong123@example.com',
    joinDate: '2024-06-01',
    status: '활동중',
    role: '관리자',
    reports: 5,
  };

  return (
    
    <div style={styles.container}>
      <h2>신고 내역</h2>
      <div style={styles.cardContainer}>
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
          {/*}
          <div style={styles.buttonContainer}>
              <button style={styles.button}>처리</button>
          </div>*/}
        </div>
      </div>
    </div>
  );
};

const styles = {
    container: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'flex-start', // 위쪽에 정렬
        flexDirection: 'column',
        height: '100vh',
        backgroundColor: '#f9f9f9',
        overflowY: 'auto', // 세로로 스크롤이 가능하게
        padding: '20px',
    },
    cardContainer: {
        display: 'flex',
        flexWrap: 'wrap', // 카드들이 가로로 배치되고 넘어갈 경우 새 줄로
        gap: '20px', // 카드 간 간격
        justifyContent: 'flex-start', // 왼쪽에 정렬
    },
    card: {
        width: '300px', // 각 카드의 너비
        backgroundColor: '#fff',
        border: '1px solid #ddd',
        borderRadius: '10px',
        padding: '20px',
        boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
        display: 'flex',
        flexDirection: 'column',
        gap: '10px',
        flexBasis: 'calc(33% - 20px)', // 각 카드가 3개씩 가로로 배치될 수 있도록
        boxSizing: 'border-box', // 여백을 포함한 크기 조정
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
    //filter: 'grayscale(100%)', // 흑백 이모티콘
    },
    buttonContainer: {
    display: 'flex',
    justifyContent: 'flex-end', // 버튼을 우측 정렬
    alignItems: 'center',
    marginTop: '10px',
    },
    button: {
    padding: '8px 16px',
    fontSize: '14px',
    backgroundColor: '#002468', // 버튼 배경 색상
    color: '#fff', // 버튼 텍스트 색상
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background-color 0.3s',
    },
};

export default UserCard;
