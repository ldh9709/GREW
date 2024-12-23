import React, { useEffect, useState } from "react";
import * as answerApi from "../../api/answerApi";
import * as memberApi from "../../api/memberApi";
import * as categoryApi from "../../api/categoryApi";
export default function AnswerProfilePopup({ memberNo }) {
  const [mentorProfile, setMentorProFile] = useState([]);
  const [member, setMember] = useState([]);
  const [category, setCategories] = useState([]);
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

  return (
    <div className="popup">
      <div>{member.memberName} 멘토 </div>
      <div>분야 : {category.categoryName}</div>
      <br />
      <div>한줄소개 : {mentorProfile.mentorIntroduce}</div>
      <div>
        <button>팔로우</button>
        <button>프로필 보기</button>
      </div>
    </div>
  );
}
