import React, { useEffect, useState } from "react";
import { useMemberAuth } from "../../util/AuthContext";
import * as ChattingApi from '../../api/chattingApi.js';
import * as followApi from "../../api/followApi";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faHeartCircleCheck, faHeartCirclePlus } from '@fortawesome/free-solid-svg-icons';

export default function MentorProfileInfo({ mentorProfile }) {
    const { token, member } = useMemberAuth();
    const [isFollow, setIsfollow] = useState(false);
    const [follow,setFollow] = useState({})

  const handleFollowToggle = async () => {

  }
  //팔로우 여부 체크
  const checkFollow = async(mentorNo) => {
    const response = await followApi.isExistFollow(token, mentorNo);
    console.log(response)
    setIsfollow(response.data);
  }

  //팔로우 등록
  const handleFollow = async() => {
    try {
        if (!isFollow) {
          handleFollow();
        }
        const response = await followApi.addfollow(token,follow);
        console.log(response)
      }catch (error) {
      console.log('팔로우가 실패하였습니다')
    }
  }

    const handleQuestionButtonClick = async () => {
        if(member.memberRole === "ROLE_MENTEE"){
        console.log("member.memberNo : "+member.memberNo);
        console.log("mentorProfile.memberNo : "+mentorProfile.memberNo);

        await ChattingApi.createChatting(member.memberNo, mentorProfile.memberNo);
        
        }else {
        alert("멘티만 가능한 서비스입니다.");
        }
        
    };
    
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
                만족도 </span>
              <span>
                {mentorProfile?.mentorRating || 0}
              </span>
            </div>
            
            {/* 버튼 */}
            <div className="mentor-actions">
              <button className="follow-button"
                onClick={{ handleFollowToggle }}
              >
                {isFollow ? (
                  <FontAwesomeIcon icon={faHeart} style={{ color: "red"}} />
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
