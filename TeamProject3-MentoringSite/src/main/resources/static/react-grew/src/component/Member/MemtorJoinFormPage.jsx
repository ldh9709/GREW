import React from "react";
import "./MentorJoinForm.css";

const MentorJoinForm = () => {
  return (
    <div className="mentor-join-container">
      <h1 className="form-title">회원가입</h1>
      <h3 className="form-subtitle">멘토 가입을 환영합니다!</h3>
      <form className="mentor-join-form">
        {/* 분야 */}
        <div className="form-group">
          <label htmlFor="field">분야</label>
          <select id="field" name="field" required>
            <option value="">-- 선택하세요 --</option>
            <option value="IT">IT</option>
            <option value="교육">교육</option>
            <option value="마케팅">마케팅</option>
            <option value="디자인">디자인</option>
          </select>
        </div>

        {/* 소개글 */}
        <div className="form-group">
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
        <div className="form-group">
          <label htmlFor="qualification">자격</label>
          <textarea
            id="qualification"
            name="qualification"
            placeholder="자격증 또는 관련 자격을 입력하세요."
            rows="3"
            required
          ></textarea>
        </div>

        {/* 경력 */}
        <div className="form-group">
          <label htmlFor="experience">경력</label>
          <textarea
            id="experience"
            name="experience"
            placeholder="경력을 입력하세요."
            rows="3"
            required
          ></textarea>
        </div>

        {/* 프로필 사진 첨부 */}
        <div className="form-group">
          <label htmlFor="profileImage">프로필 사진 첨부</label>
          <input
            type="file"
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
