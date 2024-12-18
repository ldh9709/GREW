import React, { useEffect, useState } from 'react'
import * as inquiryApi from "../../../api/inquiryApi"

export default function MemberInquiryList() {
    const [inquiryList, setInquiryList] = useState([{
        no: 1,
        category: '',
        title: '',
        date: '',
        views: '',
        likse: ''
    }]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    const fetchInquiryList = async(page) => {
        try {
            const response = await inquiryApi.listInquiryBymemberNo(page);
            const { data } = response;
            console.log(response);
            setInquiryList(data.content);
            setTotalPages(data.totalPages);
        } catch (error) {
            console.log('내가 쓴 질문 리스트 조회 실패');
        }
    }

    useEffect(() => {
        fetchInquiryList(currentPage -1)
    },[currentPage])


  return (
    <div className="" id="inquiry">
       <table className="list-table">
       <thead>
            <tr>
            <th className="col-no">번호</th>
            <th className="col-category">카테고리</th>
            <th className="col-title">제목</th>
            <th className="col-date">작성일자</th>
            <th className="col-views">조회수</th>
            <th className="col-likes">좋아요</th>
            </tr>
        </thead>
        <tbody>
            <tr>
            <td className="col-no">1</td>
            <td className="col-category"><a href="#">카테고리</a></td>
            <td className="col-title">10월 25일 경제경영 신간 리스트</td>
            <td className="col-date">yyyy-mm-dd</td>
            <td className="col-views">100</td>
            <td className="col-likes">10</td>
            </tr>
        </tbody>
          </table>
          
    </div>
  )
}
