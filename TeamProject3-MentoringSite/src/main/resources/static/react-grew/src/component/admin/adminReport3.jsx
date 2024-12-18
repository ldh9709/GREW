import React from 'react';

export const UserPage = () => {
  // 사용자 데이터 예시
  const users = [
    { id: 1, name: '', userId: '' },
    { id: 2, name: '', userId: '' },
    { id: 3, name: '', userId: '' },
  ];

  return (
    <div style={styles.page}>
      {/* 사이드바 */}
      <div style={styles.sidebar}>
        <h2 style={styles.logo}>관리자페이지</h2>
        <ul style={styles.menu}>
          
          <li>게시판관리</li>
          <li>게시글관리</li>
          <li>신고관리</li>
          {/*<li>통계</li>*/}
          
        </ul>
      </div>

      {/* 사용자 정보 리스트 */}
      <div style={styles.content}>
        <h2 style={styles.title}>신고 내역</h2>
        {users.map((user) => (
          <div key={user.id} style={styles.card}>
            <div style={styles.item}>
              <span style={styles.icon}>🔖</span> 번호: {user.id}
            </div>
            <div style={styles.item}>
              <span style={styles.icon}>👤</span> 이름: {user.name}
            </div>
            <div style={styles.item}>
              <span style={styles.icon}>🆔</span> 아이디: {user.userId}
            </div>
            <div style={styles.buttonContainer}>
              <button style={styles.button}>처리</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

// 스타일 정의
const styles = {
  page: {
    display: 'flex',
    height: '100vh',
    backgroundColor: '#f4f4f4',
    fontFamily: 'Arial, sans-serif',
  },
  sidebar: {
    width: '220px',
    backgroundColor: 'ffffff',
    color: '000000',
    padding: '20px',
    boxShadow: '2px 0 5px rgba(0,0,0,0.1)',
  },
  logo: {
    fontSize: '20px',
    fontWeight: 'bold',
    marginBottom: '30px',
    textAlign: 'center',
    
  },
  menu: {
    listStyleType: 'none',
    padding: 0,
    fontSize: '16px',
    textAlign: 'center',
    alignItems:'space-around'
  },
  content: {
    flex: 1,
    padding: '20px',
    overflowY: 'auto',
  },
  title: {
    fontSize: '24px',
    marginBottom: '20px',
  },
  card: {
    backgroundColor: '#ffffff',
    border: '1px solid #ddd',
    borderRadius: '8px',
    boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
    padding: '15px',
    marginBottom: '15px',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'space-between',
  },
  item: {
    display: 'flex',
    alignItems: 'center',
    marginBottom: '5px',
    color: '#333',
  },
  icon: {
    marginRight: '10px',
    filter: 'grayscale(100%)', // 흑백 이모티콘
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

export default UserPage;
