import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../css/mentorProfile.css"; // CSS 스타일 임포트
import * as memberApi from "../../api/memberApi";
import * as mentorProfileApi from "../../api/mentorProfileApi";

export default function MentorProfileItem({ mentor }) {
  const navigate = useNavigate();

  const [ member, setMember ] = useState({
    memberNo:0,
    memberName: "",
  });


  const [additionalData, setAdditionalData] = useState({
    mentorIntroduce: "",
    mentorCareer: "",
    categoryName:"",
    mentorHeadline:""
  });


  useEffect(() => {
    if (mentor.mentorStatus === 3) { // 상태가 3인 경우에만 데이터 요청
      fetchMember();
      fetchAdditionalData();
    }
  }, [mentor]);

  const fetchMember = async () => {
    const response = await memberApi.getMemberByMemberNo(mentor.memberNo);
    setMember(response.data);
  };

  const fetchAdditionalData = async () => {
    const response = await mentorProfileApi.getMentorProfileByNo(mentor.mentorProfileNo);
    setAdditionalData({
      mentorIntroduce: response.data.mentorIntroduce || "소개 텍스트 없음",
      mentorCareer: response.data.mentorCareer || "경력 정보 없음",
      categoryName:response.data.categoryName || "분야 정보 없음",
      mentorHeadline:response.data.mentorHeadline || "한줄 소개 없음"
    });
  };


  // 멘토 프로필 클릭 시 상세 페이지로 이동하는 함수
  // const mentorname = () => {
  //   navigate(`/mentor-profile/${mentor.mentorProfileNo}`);
  // };
  // 멘토 프로필 클릭 시 상세 페이지로 이동하는 함수
  const viewMentorProfile = () => {
    navigate(`/mentor-profile/${mentor.mentorProfileNo}`);
  };
  if (mentor.mentorStatus !== 3) {
    return null; // 상태가 3이 아닌 경우 아무것도 렌더링하지 않음///////////////////////////
  }
  return (
    <div
      className="mentor-profile-card"
      onClick={viewMentorProfile} // 클릭하면 MentorProfileDetail 페이지로 이동
    >
      {/* 멘토 이미지 */}
      <img
        src={mentor.mentorImage || "/default-profile.png"}
        alt={`${mentor.member_name || "멘토"} 이미지`}
        className="mentor-profile-image"
      />
      {/* 멘토 분야 */}
      <div className="card-category">{additionalData.categoryName}</div>

      {/* 멘토 이름 */}
      <div className="card-title">{mentor.memberName} 멘토</div>

      {/* 멘토 한줄 소개 */}
      <div className="card-content">{additionalData.mentorHeadline}</div>
      
    </div>
    );
}
