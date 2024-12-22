import React, { useEffect, useState } from "react";
import { getAdminReportList, updateReportStatusForAdmin, adminReport } from "../../api/adminApi"; // api.js에서 import

// 신고 처리 페이지
export const AdminReportPage = () => {
  const [reports, setReports] = useState([]); // 신고 목록 상태
  const [loading, setLoading] = useState(true); // 데이터 로딩 상태
  const [error, setError] = useState(null); // 에러 상태
 
  // 신고 목록을 API에서 가져오는 함수
  const fetchReports = async () => {
    try {
      {/*
          "Content-Type": "application/json",
        },
      });
      if (!response.ok) {
        throw new Error('신고 목록을 가져오는 데 실패했습니다.');
      }
      const data = await response.json();
      setReports(data.data); // 신고 목록을 상태에 저장
      console.log("response : ", data);*/}
      // 1. 로컬 스토리지에서 액세스 토큰을 가져옴
      const token = localStorage.getItem("accessToken");  // 실제 로그인 후 받은 토큰을 사용  

      if (!token) {
        setError("액세스 토큰이 없습니다. 로그인 후 다시 시도해주세요.");
        return; // 토큰이 없다면 더 이상 진행하지 않음
      }
       // 2. 페이지, 사이즈 값 설정
      const filter = 1;  // 필터값 (전체)
      const page = 0;    // 페이지 번호
      const size = 10;   // 페이지 당 항목 수
      // 3. adminReport 함수 호출하여 데이터 가져오기
      const data = await getAdminReportList(filter, page, size);
      
      console.log("data : ", data);
      // 4. 데이터가 잘 왔으면 상태에 저장
      if (data && data.data) {
        setReports(data.data); // 신고 목록을 상태에 저장
      } else {
        setError('데이터 형식에 문제가 있습니다.');
      }
    } catch (err) {
      console.log("ERR : ", err);
      setError('신고 목록을 가져오는 데 실패했습니다.'); // 에러 처리
    } finally {
      setLoading(false); // 로딩 종료
    }
  };

  // 신고 상태 업데이트 함수
  const updateReportStatus = async (reportNo, status) => {
    try {
      {/*const response = await fetch(`/admin/report/${reportNo}/status?status=${status}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (!response.ok) {
        throw new Error('신고 상태 변경에 실패했습니다.');
      }

      const data = await response.json();*/}
      await updateReportStatusForAdmin(reportNo, status);
      fetchReports();  // 상태 변경 후 신고 목록을 다시 불러옴
      console.log("status update response: ", fetchReports);
    } catch (err) {
      console.log("ERR : ", err);
      setError('신고 상태 변경에 실패했습니다.');
    }
  };

  // 컴포넌트가 처음 렌더링될 때 신고 목록을 불러옴
  useEffect(() => {
    fetchReports();
  }, []);

  return (
    <div style={styles.page}>
      
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
    backgroundColor: '#002468',
    color: '#ffffff',
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
  },
  buttonContainer: {
    display: 'flex',
    justifyContent: 'flex-end',
    alignItems: 'center',
    marginTop: '10px',
  },
  button: {
    padding: '8px 16px',
    fontSize: '14px',
    backgroundColor: '#002468',
    color: '#fff',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    transition: 'background-color 0.3s',
    marginLeft: '10px',
  },
};

