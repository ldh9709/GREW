import React, { useEffect, useState } from "react";
import * as answerApi from "../../api/answerApi";
import * as memberApi from "../../api/memberApi";
import * as categoryApi from "../../api/categoryApi";
import { useNavigate } from "react-router-dom";
export default function AnswerProfilePopup({ memberNo }) {
  const [mentorProfile, setMentorProFile] = useState([]);
  const [member, setMember] = useState([]);
  const [category, setCategories] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    fetchMentorProfile();
    fetchMember();
  }, [memberNo]); // memberNo가 변경될 때마다 호출

  // mentorProfile이 업데이트된 후에 fetchCategory 호출
  useEffect(() => {
    if (mentorProfile && mentorProfile.categoryNo) {
      fetchCategory();
    }
  }, [mentorProfile]); // mentorProfile이 변경될 때마다 호출

  const fetchMember = async () => {
    const response = await memberApi.getMemberByMemberNo(memberNo);
    setMember(response.data);
  };
  const fetchMentorProfile = async () => {
    const response = await answerApi.getMentorProfileByMemberNo(memberNo);
    console.log(response);
    setMentorProFile(response.data);
  };

  const fetchCategory = async () => {
    const response = await categoryApi.getCategory(mentorProfile.categoryNo);
    console.log(response);
    setCategories(response.data);
  };
  const handleMentorProfile = () => {
    navigate(`/mentor-profile/${mentorProfile.mentorProfileNo}`);
  };
  return (
    <div className="popup">
      <div className="popup-img">
        <img src={mentorProfile?.mentorImage||'/images/mentor-profile/defaultimg.jpeg'} alt="Mentor Profile" />
      </div>
      <div className="mentor-profile-name">{member.memberName} 멘토 </div>
      <div className="mentor-profile-category">{category.categoryName}</div>
      <br />
      <div className="mentor-profile-introduce">한줄소개 : {mentorProfile.mentorIntroduce}</div>
      <div className="answer-mentor-profile-btn">
        <button
          className="handle-mentor-profile-btn"
          onClick={handleMentorProfile}
        >
          프로필 보기
        </button>
      </div>
    </div>
  );
}
