import React, { useEffect, useState } from "react";
import { useMemberAuth } from "../../util/AuthContext";
import * as ChattingApi from '../../api/chattingApi.js';
import * as followApi from "../../api/followApi";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faHeartCircleCheck, faHeartCirclePlus } from '@fortawesome/free-solid-svg-icons';

export default function MentorProfileInfo({ mentorProfile }) {
    const { token, member } = useMemberAuth();
    const [isFollow, setIsfollow] = useState(false);
    const [follow, setFollow] = useState({});
    const [loading, setLoding] = useState(false);

  //팔로우 여부 체크
  const checkFollow = async () => {
    if (member.memberRole === 'ROLE_MENTEE') {
      const response = await followApi.isExistFollow(token, mentorProfile.memberNo);
      setIsfollow(response.data);
    }
  }
  //팔로우 목록 조회
  const fetchGetFollow = async () => {
    if (member.memberRole === 'ROLE_MENTEE' && isFollow===true) {
      const response = await followApi.getfollow(token, mentorProfile.memberNo);
      setFollow(response.data)
    }
  }

  //팔로우 등록, 취소
  const toggleFollow = async() => {
    if (!isFollow) {
      const followItem = {
        menteeMemberNo: member.memberNo,
        mentorMemberNo: mentorProfile.memberNo
      };
      await followApi.addfollow(token, followItem);
      mentorProfile.mentorFollowCount += 1;
      setIsfollow(true);
    } else {
      await followApi.deleteFollow(token, follow.followNo);
      mentorProfile.mentorFollowCount -= 1;
      setIsfollow(false);
    } 
  }

  //팔로우 버튼 클릭
  const handleFollowClick = async () => {
    //로딩이 실행 중이면 클릭이벤트 무시
    if (loading) return;
    setLoding(true)
    try {
      if (!token) {
        alert("로그인이 필요한 서비스 입니다.")
        return;
      }

      if (member.memberRole !== 'ROLE_MENTEE') {
        alert("멘티회원만 가능한 서비스 입니다.");
        return;
      }

      await toggleFollow();
    } catch (error) {
      console.error('팔로우 등록 및 취소 실패', error);
    } finally {
      setLoding(false);
    }
  };


  //상담 신청하기 버튼 클릭
  const handleQuestionButtonClick = async () => {
      if(member.memberRole === "ROLE_MENTEE"){
      await ChattingApi.createChatting(member.memberNo, mentorProfile.memberNo);
      
      }else {
      alert("멘티 회원만 가능한 서비스입니다.");
      }
        
  };
  
  useEffect(() => {
    checkFollow();
    if (isFollow) {
      fetchGetFollow();
    }
  },[isFollow])

  return (
      <>
        {/* 좌측: 이미지와 기본 정보 */}
        <div className="mentor-image-section">
          <img
            src={mentorProfile?.mentorImage || "/default-profile.png"}
            alt="프로필 이미지"
            className="mentor-profile-image-large"
          />
          <div className="mentor-basic-info">
            <h2>{mentorProfile.memberName} 멘토</h2> {/* 멤버 이름 표시 */}
            <div className="mentor-stats">
              <span className="stats-label">
                멘토링 신청 </span>
              <span>
                {mentorProfile?.mentorMentoringCount || 0}건 {" "}
              </span>
              <span className="stats-label">매칭률 </span>
              <span>
                {mentorProfile?.mentorActivityCount
                ? Math.round(
                    (mentorProfile.mentorMentoringCount /
                      mentorProfile.mentorActivityCount) *
                      100
                  )
                  : 0}  
                    %{" "}
              </span>
              <span className="stats-label">
                만족도
              </span>
              <span>
                {mentorProfile?.mentorRating.toFixed(1) || 0}/5.0
              </span>
            </div>
            
            {/* 버튼 */}
            <div className="mentor-actions">
            <button
                className={`follow-button ${isFollow ? 'follow-isexist' : ''}`}
                onClick={handleFollowClick}
              >
                {isFollow ? (
                  <FontAwesomeIcon icon={faHeart}/>
                ): (
                  <FontAwesomeIcon icon={faHeartCirclePlus} />
                )}
                팔로우
              </button>
              <button
                className="question-button"
                onClick={handleQuestionButtonClick}
              >
                멘토링 신청하기
              </button>
            </div>
              <div className="mentor-follow-count">{mentorProfile?.mentorFollowCount || 0}명이 팔로우 하는 중</div>
            </div>
        </div>
    </>
  )
}
