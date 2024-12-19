import React from "react";
import MentorProfileDetail from "./MentorProfileDetail"; // 멘토 프로필 상세
import MentorBoard from "./MENTOR_BOARD"; // 멘토 게시판
import MentorReview from "./MENTOR_REVIEW"; // 멘토 리뷰

const MentorProfile = () => {
  // 임시로 memberNo를 1번으로 설정
  const memberNo = 1; 

  return (
    <div>
      {/* Main Content */}
      <main style={{ padding: "20px" }}>
        {/* 1. Mentor Profile Detail Section */}
        <section style={{ display: "flex", marginBottom: "40px" }}>
          {/* 왼쪽 UI: MentorProfileDetail에서 렌더링된 프로필 사진 및 데이터 */}
          <div style={{ flex: 1 }}>
            <MentorProfileDetail memberNo={memberNo} />
          </div>
        </section>

        {/* 2. Mentor Board Section */}
        <section style={{ marginBottom: "40px" }}>
          <h2>멘토 콘텐츠</h2>
          <MentorBoard />
        </section>

        {/* 3. Mentor Review Section */}
        <section>
          <h2>멘토 리뷰</h2>
          <MentorReview />
        </section>
      </main>
    </div>
  );
};

export default MentorProfile;
