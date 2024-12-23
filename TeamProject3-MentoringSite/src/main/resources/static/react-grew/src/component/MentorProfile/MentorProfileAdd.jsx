import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header from "../../layout/Header";
import Footer from "../../layout/Footer";
import Navigate from "../../layout/Navigate";
import axios from "axios";

export default function MentorProfileAdd() {
  const navigate = useNavigate();

  const [mentorProfile, setMentorProfile] = useState({
    memberNo: "", // 멤버 번호
    categoryNo: "", // 카테고리 번호
    mentorCareer: "", // 멘토 경력
    mentorIntroduce: "", // 멘토 소개
    mentorImage: null, // 프로필 이미지
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setMentorProfile((prevProfile) => ({
      ...prevProfile,
      [name]: value,
    }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setMentorProfile((prevProfile) => ({
      ...prevProfile,
      mentorImage: file,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    const formData = new FormData();
    formData.append("categoryNo", mentorProfile.categoryNo);
    formData.append("mentorCareer", mentorProfile.mentorCareer);
    formData.append("mentorIntroduce", mentorProfile.mentorIntroduce);
    if (mentorProfile.mentorImage) {
      formData.append("mentorImage", mentorProfile.mentorImage);
    }
    
    try {
      const response = await axios.post(
        `/mentor-profile/${mentorProfile.memberNo}/create-profile`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      alert("멘토 프로필이 성공적으로 생성되었습니다.");
      navigate("/member/profile");
    } catch (error) {
      console.error("프로필 생성 중 오류 발생", error);
      alert("프로필 생성에 실패했습니다.");
    }
  };

  return (
    <>
    
      <div className="container">
        <h2>멘토 프로필 생성</h2>
        <form onSubmit={handleSubmit} encType="multipart/form-data">
          <div className="form-group">
            <label htmlFor="memberNo">멤버 번호</label>
            <input
              type="text"
              id="memberNo"
              name="memberNo"
              value={mentorProfile.memberNo}
              onChange={handleInputChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="categoryNo">카테고리 번호</label>
            <input
              type="text"
              id="categoryNo"
              name="categoryNo"
              value={mentorProfile.categoryNo}
              onChange={handleInputChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="mentorCareer">멘토 경력</label>
            <textarea
              id="mentorCareer"
              name="mentorCareer"
              value={mentorProfile.mentorCareer}
              onChange={handleInputChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="mentorIntroduce">멘토 소개</label>
            <textarea
              id="mentorIntroduce"
              name="mentorIntroduce"
              value={mentorProfile.mentorIntroduce}
              onChange={handleInputChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="mentorImage">멘토 프로필 이미지</label>
            <input
              type="file"
              id="mentorImage"
              name="mentorImage"
              onChange={handleFileChange}
            />
          </div>

          <button type="submit" className="btn-submit">프로필 생성</button>
        </form>
      </div>
      
    </>
  );
}
