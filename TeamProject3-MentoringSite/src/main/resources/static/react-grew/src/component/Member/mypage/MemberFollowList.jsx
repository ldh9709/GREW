import { useMemberAuth } from "../../../util/AuthContext";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-solid-svg-icons';
import * as followApi from "../../../api/followApi"
import { useNavigate } from "react-router-dom";

export default function FollowList({ handleUpdate }) {
    /* Context에 저장된 토큰, 멤버정보 */
    const { token } = useMemberAuth();
    const [followList, setFollowList] = useState([])
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
    const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
    const navigate = useNavigate();


    const fetchFollowList = async (page) => {
        try {
            const response = await followApi.followList(token,page);
            const { data } = response;
            setFollowList(data.content);
            setTotalPages(data.totalPages);
            console.log(data.content);
        } catch (error) {
            console.log('팔로우 리스트 조회 실패', error);
        }
    }    

    //팔로우 취소 버튼
    const onClickHeartBtn = async (followNo) => {
        try {
            await followApi.deleteFollow(token,followNo);
            fetchFollowList(currentPage - 1);
            handleUpdate();
        } catch (error) {
            alert('팔로우 취소 실패');
        }
    }

    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    // 페이지 로드 시 데이터 가져오기
    useEffect(() => {
           fetchFollowList(currentPage - 1);
    }, [currentPage])
    

    // 페이지 번호 버튼 표시
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
        pageNumbers.push(i);
    }


    return (
        <>
            <div className="tab-content tab-bottom" id="following">
              {/* 팔로우 리스트가 비어 있는 경우 */}
              {followList.length === 0 ? (
                    <p className="no-follow">팔로우 한 멘토가 없습니다.</p>
                ) : (
                    <>
                        <ul className="follow-list">
                            {followList.map((follow, index) => (
                                <li className="follow-card"
                                key={index}
                                >
                                    <div className="profile">
                                        <img src={follow.mentorImage} alt="프로필 이미지" />
                                    </div>
                                    <div className="info"
                                     onClick={() => navigate(`/mentor-profile/${follow.mentorProfileNo}`)}
                                    >
                                        <p className="name"><strong>{follow.mentorName} 멘토</strong></p>
                                        <p>{follow.primaryCategory}</p>
                                        <p>{follow.subCategory}</p>
                                    </div>
                                    <div
                                        className="heart"
                                        onClick={() => onClickHeartBtn(follow.followNo)}
                                    >
                                        <FontAwesomeIcon icon={faHeart} />
                                    </div>
                                </li>
                            ))}
                        </ul>
                        {/* 페이지네이션 버튼 */}
                        <div className="common-pagination common-pagination-bottom">
                            {/* 이전 버튼 */}
                            <button
                            className="common-pagination-arrow"
                            disabled={currentPage === 1}
                            onClick={() => paginate(currentPage - 1)}
                            >
                            &lt;
                            </button>

                            {/* 페이지 번호 버튼 */}
                            {pageNumbers.map((number) => (
                            <button
                                key={number}
                                className={`common-pagination-number ${
                                currentPage === number ? "active" : ""
                                }`}
                                onClick={() => paginate(number)}
                            >
                                {number}
                            </button>
                            ))}

                            {/* 다음 버튼 */}
                            <button
                            className="common-pagination-arrow"
                            disabled={currentPage === totalPages}
                            onClick={() => paginate(currentPage + 1)}
                            >
                            &gt;
                            </button>
                        </div>
                                        </>
                                    )}
                                </div>
        </>
    );
}
