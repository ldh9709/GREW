import React, { useEffect, useState } from "react";
import image from '../../../image/images.jpeg'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-solid-svg-icons';
import * as followApi from "../../../api/followApi"

export default function FollowList({ memberNo }) {
    const [followList, setFollowList] = useState([{
            name: '',
            primaryCategory: '',
            subCategory:''
    }])
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
    const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
    const [itemsPerPage] = useState(6); // 페이지당 항목 수 (예: 한 페이지에 6개 항목)



    const fetchFollowList = async (page, size) => {
        try {
            const response = await followApi.followList(memberNo, page, size);
            const data = response.data;
            setFollowList(data.content);
            setTotalPages(data.totalPages);
        } catch (error) {
            console.log('팔로우 리스트 조회 실패', error);
        }
    }    

    const fetchFollowDelete = async () => {
        try {
            
        } catch (error) {
            console.log('팔로우 취소 실패', error);
        }
    }
    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    // 페이지 로드 시 데이터 가져오기
    useEffect(() => {
           fetchFollowList(currentPage - 1, itemsPerPage);
    }, [currentPage])
    

    // 페이지 번호 버튼 표시
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
        pageNumbers.push(i);
    }


    return (
    <>
        <div className="tab-content" id="following">
            <ul className="follow-list">

                {/* 팔로우 리스트 map으로 반복*/}
                {followList.map((follow)=>(
                    <li className="follow-card">
                        <div className="profile">
                            <img src={image} alt="프로필 이미지" />
                        </div>
                        <div className="info">
                            <p className="name"><strong>{follow.mentorName} 멘토</strong></p>
                            <p>{follow.primaryCategory}</p>
                            <p>{follow.subCategory}</p>
                        </div>
                        <div className="heart">
                            <FontAwesomeIcon icon={faHeart} />
                        </div>
                    </li>
                ))}
            </ul>
            {/* 페이지네이션 버튼 */}
            <div className="pagenation">
            {pageNumbers.map((number) => (
                <button key={number} onClick={() => paginate(number)}>
                    {number}
                </button>
            ))}
            </div>    
        </div>
    </>
  )
}
