import React from "react";
import Header from "../../layout/Header";
import Footer from "../../layout/Footer";
import Navigate from "../../layout/Navigate";

export default function MentorProfileDetail() {
  return (
    <>
      <Header />
      <Navigate />
      <div className="container">
        {/* 멘토 프로필 정보 영역 */}
        <section className="mentor-profile-info">
          <h2>멘토 프로필 정보</h2>
          <div className="mentor-profile">
            <img src="/placeholder-profile.png" alt="프로필 이미지" className="profile-image" />
            <div className="profile-details">
              <h3>이름: 홍길동</h3>
              <p>주요 경력: ...</p>
              <p>멘토 소개: ...</p>
              <p>평점: ...</p>
              {/* 팔로우 버튼 영역 */}
              {/* TODO: 팔로우 버튼 */}
            </div>
          </div>
        </section>

        {/* 멘토보드 리스트 영역 */}
        <section className="mentor-board-list">
          <h2>멘토 보드 리스트</h2>
          {/* TODO: 멘토보드 리스트 컴포넌트 */}
        </section>

        {/* 멘토 리뷰 리스트 영역 */}
        <section className="mentor-review-list">
          <h2>멘토 리뷰 리스트</h2>
          {/* TODO: 멘토 리뷰 컴포넌트 */}
        </section>
      </div>
      <Footer />
    </>
  );
}
