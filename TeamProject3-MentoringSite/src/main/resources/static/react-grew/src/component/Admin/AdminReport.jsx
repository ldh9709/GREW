import { getCookie } from "../../util/cookieUtil";
import React, { useEffect, useState } from 'react';
import * as adminApi from '../../api/adminApi.js';

export const UserCard = () => {
  const memberCookie = getCookie("member");
  const token = memberCookie.accessToken;
  const filter = null;
  /*
      1. 초기설정
      2. fetct 목록 조회 함수 생성(token,filter)
      3. useEffect 실행함수 생성
      4. 목록 조회
  */
  const [reports,setReport] = useState([]);
  
  const fetchReports = async (token,filter) => {
    try{
      const response = await adminApi.getAdminReportList(token,filter);
      setReport(response.data);
      console.log(response.data);
      console.log(response);
    } catch (err){
      console.log('신고목록조회실패', err);
    }
  }  
  
  useEffect(()=>{
    fetchReports(token,filter);
  }, [])
  /*
    신고 접수 흐름
    1. 신고타켓번호를 통해 신고 내용을 확인하러감
    2. '접수' 버튼 클릭 1) 신고처리완료
                          2)무고처리

  */
  return (
    <div className="admin-member-container">
  
    <table className="member-table">
        <thead>
            <tr>
                <th>신고번호</th>
                <th>타켓타입</th>
                <th>신고타켓번호</th>
                <th>신고타입</th>
                <th>신고내용</th>
                <th>신고일자</th>
                <th>처리일자</th>
                <th>신고상태</th>
                <th>신고자</th>
                <th>처리상황</th>
            </tr>
        </thead>
        <tbody>
          {reports.map((report,index)=>(
             <>
             <tr key={index}>    
                 <td>{index+1}reportNo</td>
                 <td>type</td>
                 <td>target</td>
                 <td>reason</td>
                 <td>content</td>
                 <td>date</td>
                 <td>resolvedDate</td>
                 <td>status</td>
                 <td>memberNo</td>
                 <td>
                     <button
                     className="check"
                    >
                     접수
                     </button>
                     
                 </td>
             </tr>
             </>
          ))}
               
        </tbody>
    </table>
</div>
  );
};

export default UserCard;