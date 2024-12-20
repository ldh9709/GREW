import { getCookie } from "../../../util/cookieUtil"
import React, { useEffect, useState } from 'react'
import * as inquiryApi from "../../../api/inquiryApi"
import * as answerApi from "../../../api/answerApi"
import { useNavigate } from 'react-router-dom';

export default function MemberInquiryList() {
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;
    const role = memberCookie.memberRole;
    
    const [datayList, setdataList] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);
    const navigate = useNavigate();

    const fetchInquiryList = async (page) => {
        try {
            if (role === 'ROLE_MENTEE') {
                const response = await inquiryApi.listInquiryByMemberNo(token, page);
                const { data } = response;
                setdataList(data.content);
                setTotalPages(data.totalPages);
            } else if (role === 'ROLE_MENTOR') {
                const response = await answerApi.listAnswerByMemberNo(token,page);
                const { data } = response;
                setdataList(data.content);
                setTotalPages(data.totalPages);
            }


        } catch (error) {
            console.log('내가 쓴 질문 리스트 조회 실패',error);
        }
    }

    useEffect(() => {
        fetchInquiryList(currentPage - 1);
    },[currentPage])
    
    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    // 페이지 번호 버튼 표시
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
        pageNumbers.push(i);
    }

  return (
    <div className="tab-content tab-inquiry" id="inquiry">
       <table className="list-table">
       <thead>
            <tr>
            <th className="col-no">번호</th>
            <th className="col-category">카테고리</th>
            <th className="col-title">제목</th>
            <th className="col-date">작성일자</th>
            <th className="col-views">조회수</th>
            </tr>
        </thead>
        <tbody>
            {/* 질문 리스트 map으로 반복 */}          
            {datayList.map((inquiry,index) => (
                <tr key={index} onClick={() => {
                    navigate(`/inquiry/${inquiry.inquiryNo}`)
                }}>
                    <td className="col-no">{index+1}</td>
                    <td className="col-category">{inquiry.parentsCategoryName}</td>
                    <td className="col-title">{inquiry.inquiryTitle}</td>
                    <td className="col-date">{inquiry.inquiryDate.substring(0,10)}</td>
                    <td className="col-views">{inquiry.inquiryViews}</td>
                </tr>
            ))} 
        </tbody>
        </table>
        {/* 페이지네이션 버튼 */}
        <div className="pagenation pagenation-bottom">
            {pageNumbers.map((number) => (
                <button key={number} onClick={() => paginate(number)}>
                    {number}
                </button>
            ))}
        </div>   
    </div>
  )
}
