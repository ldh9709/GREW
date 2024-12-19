// import React from 'react';

// export const ReportTable = () => {
//   // 예시 데이터
//   const reports = [
//     { id: '20241212', content: '홍길동 신고 내용', status: '완료' },
//     { id: '20241213', content: '이창섭 신고 내용', status: '미정' },
//     { id: '20241214', content: '전과자 신고 내용', status: '미정' },
//   ];

//   return (
//     <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
//       <h2>신고 내역</h2>
//       <table style={{ width: '80%', borderCollapse: 'collapse', textAlign: 'center' }}>
//         <thead>
//           <tr style={{ backgroundColor: '#f0f0f0' }}>
//             <th style={styles.th}>신고 ID</th>
//             <th style={styles.th}>신고 내용</th>
//             <th style={styles.th}>상태</th>
//             <th style={styles.th}>처리</th>
//             <th style={styles.th}>삭제</th>
//           </tr>
//         </thead>
//         <tbody>
//           {reports.map((report, index) => (
//             <tr key={index} style={{ borderBottom: '1px solid #ddd' }}>
//               <td style={styles.td}>{report.id}</td>
//               <td style={styles.td}>{report.content}</td>
//               <td style={styles.td}>{report.status}</td>
//               <td style={styles.td}>
//                 <button style={styles.button}>처리</button>
//               </td>
//               <td style={styles.td}>
//                 <button style={{ ...styles.button, backgroundColor: '#ff4d4d' }}>삭제</button>
//               </td>
//             </tr>
//           ))}
//         </tbody>
//       </table>
//     </div>
//   );
// };

// const styles = {
//   th: {
//     border: '1px solid #ddd',
//     padding: '10px',
//     fontWeight: 'bold',
//   },
//   td: {
//     border: '1px solid #ddd',
//     padding: '10px',
//   },
//   button: {
//     padding: '5px 10px',
//     backgroundColor: '#4CAF50',
//     color: 'white',
//     border: 'none',
//     borderRadius: '4px',
//     cursor: 'pointer',
//   },
// };

// export default ReportTable;
