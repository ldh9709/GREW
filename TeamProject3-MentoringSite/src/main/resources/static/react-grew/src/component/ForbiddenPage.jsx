import React from 'react';
import { useNavigate } from 'react-router-dom';

const ForbiddenPage = () => {
  const navigate = useNavigate();

  const handleGoHome = () => {
    navigate('/');
  };

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>403 - Forbidden</h1>
      <p style={styles.message}>잘못된 접근입니다.</p>
      <button style={styles.button} onClick={handleGoHome}>
        메인화면
      </button>
    </div>
  );
};

// 스타일 정의
const styles = {
  container: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
    backgroundColor: '#f4f4f4',
    textAlign: 'center',
  },
  title: {
    fontSize: '3rem',
    color: '#ff4747',
  },
  message: {
    fontSize: '1.2rem',
    color: '#555',
    marginBottom: '20px',
  },
  button: {
    padding: '10px 20px',
    fontSize: '1rem',
    cursor: 'pointer',
    backgroundColor: '#007bff',
    color: '#fff',
    border: 'none',
    borderRadius: '5px',
  },
};

export default ForbiddenPage;
