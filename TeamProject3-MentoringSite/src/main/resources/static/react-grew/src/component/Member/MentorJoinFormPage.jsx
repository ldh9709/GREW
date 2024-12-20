import React, { useState } from "react";
import "../../css/mentor.css"
import { useNavigate } from "react-router-dom";



const MentorJoinForm = () => {

  const navigate = useNavigate();

  const [mentor, setMentor] = useState({
    mentorCategory: "",
    mentorIntroduct: "",
    mentorCareer: "",

  })














  return (
    <div className="mentor-join-container">
      <h1 className="form-title">회원가입</h1>
      <h3 className="form-subtitle">멘토 가입을 환영합니다!</h3>
      <form className="mentor-join-form">
        {/* 분야 */}
        <div className="form-group-profile horizontal-field">
        <label htmlFor="introduction">전문 분야</label>

          <select id="field" name="field" required>
            <option value="">-- 선택하세요 --</option>
            <option value="2">인사/총무/노무</option>
            <option value="3">영업/영업관리</option>
            <option value="4">IT개발/데이터</option>
            <option value="6">중학생 교육</option>
            <option value="7">고등학생 교육</option>
            <option value="8">대학입시 상담</option>
            <option value="10">음악</option>
            <option value="11">글쓰기</option>
            <option value="12">미술</option>
            <option value="13">사진/영상 제작</option>
            <option value="14">연기/연극</option>
            <option value="16">스타트업 아이디어</option>
            <option value="17">마케팅 전략</option>
            <option value="18">법률 특허 상담</option>
            <option value="22">피트니스</option>
            <option value="23">요가/필라테스</option>
            <option value="24">재활 운동</option>
            <option value="25">식단/영양 상담</option>
          </select>

        </div>

        {/* 소개글 */}
        <div className="form-group-profile horizontal">
          <label htmlFor="introduction">소개글</label>
            <textarea
              id="introduction"
              name="introduction"
              placeholder="자신을 소개해주세요."
              rows="3"
              required
            ></textarea>
        </div>


        {/* 자격 */}
        <div className="form-group-profile horizontal">
          <label htmlFor="introduction">자격</label>
          <textarea
            id="qualification"
            name="qualification"
            placeholder="자격증 또는 관련 자격을 입력하세요."
            rows="3"
            required
          ></textarea>
        </div>

        {/* 경력 */}
        <div className="form-group-profile horizontal">
        <label htmlFor="introduction">경력</label>
          <textarea
            id="experience"
            name="experience"
            placeholder="경력을 입력하세요."
            rows="3"
            required
          ></textarea>
        </div>

        {/* 프로필 사진 첨부 */}
        <div className="form-group-profile horizontal">
        <label htmlFor="introduction">프로필 사진</label>
          <input
            type="file"
            className="form-group-profileImage"
            id="profileImage"
            name="profileImage"
            accept="image/*"
            required
          />
        </div>

        {/* 제출 버튼 */}
        <button type="button" className="submit-button">
          회원가입
        </button>
      </form>
    </div>
  );
};

export default MentorJoinForm;
