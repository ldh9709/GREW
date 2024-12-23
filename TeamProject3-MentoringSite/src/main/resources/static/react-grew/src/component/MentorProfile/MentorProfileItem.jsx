import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../css/mentorProfile.css"; // CSS 스타일 임포트
import * as memberApi from "../../api/memberApi";

export default function MentorProfileItem({ mentor }) {
  const navigate = useNavigate();

  const [ member, setMember ] = useState({
    memberNo:0,
    memberName: "",
  });

  useEffect(() => {
    fetchMember();
  }, []);

  const fetchMember = async () => {
    const response = await memberApi.getMemberByMemberNo(mentor.memberNo);
    console.log(response.data);
    setMember(response.data);
  };

  // // 🔥 멘토 프로필 클릭 시 상세 페이지로 이동하는 함수
  // const mentorname = () => {
  //   navigate(`/mentor-profile/${mentor.mentorProfileNo}`);
  // };
  // 🔥 멘토 프로필 클릭 시 상세 페이지로 이동하는 함수
  const viewMentorProfile = () => {
    navigate(`/mentor-profile/${mentor.mentorProfileNo}`);
  };

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
      {/* 멘토 이름 */}
      <div className="card-title">
      {member.memberName}
      </div>
      {/* 멘토 소개 */}
      <div className="card-content">
        {mentor.mentor_introduce || "소개 텍스트 없음"}
      </div>
      {/* 멘토 경력 */}
      <h3 className="mentor-profile-name">
        {mentor.mentorCareer || "경력 정보 없음"}
      </h3>
    </div>
  );
}
