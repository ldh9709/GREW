import React, { useEffect, useState } from "react";
import * as mentorApi from "../../api/mentorApi"; // 실제 프로젝트 구조에 맞게 경로를 설정하세요.
import "../../css/MentorProfileDetail.css"; // CSS 파일 추가 필요

function MentorProfileDetail({ mentorProfileNo }) {
  const [mentorData, setMentorData] = useState(null); // 초기 상태를 null로 설정
  const [loading, setLoading] = useState(true); // 로딩 상태
  const [error, setError] = useState(null); // 에러 상태

  useEffect(() => {
    const fetchMentorData = async () => {
      try {
        const response = await mentorApi.getMentorProfile(mentorProfileNo);
        if (response.status === 2355 && response.data) {
          setMentorData(response.data);
        } else {
          setError("데이터를 불러오는 데 실패했습니다.");
        }
      } catch (err) {
        console.error("API 호출 중 오류:", err);
        setError("서버 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };

    if (mentorProfileNo) {
      fetchMentorData();
    } else {
      setError("회원 번호가 제공되지 않았습니다.");
      setLoading(false);
    }
  }, [mentorProfileNo]);

  if (loading) return <div className="loading">로딩 중...</div>;
  if (error) return <div className="error">{error}</div>;
  if (!mentorData) return <div className="no-data">멘토 데이터를 찾을 수 없습니다.</div>;

  return (
    <div className="mentor-profile-container">
      {/* 왼쪽: 멘토 프로필 사진 및 기본 정보 */}
      <div className="mentor-left">
        <img
          src={mentorData.mentorImage || "https://via.placeholder.com/150"}
          alt="멘토 프로필 사진"
          className="mentor-image"
        />
        <button className="follow-button">+ 팔로우</button>
        <button className="mentoring-request-button">멘토에게 질문하기</button>
      </div>

      {/* 오른쪽: 상세 정보 */}
      <div className="mentor-right">
        <h2>{mentorData.mentorIntroduce || "멘토 이름"}</h2>
        <div className="mentor-rating">
          평점: {mentorData.mentorRating?.toFixed(2) || "0.00"} / 5.0
        </div>
        <div className="mentor-career">
          <h3>주요 경력</h3>
          <p>{mentorData.mentorCareer || "경력 정보가 없습니다."}</p>
        </div>
        <div className="mentor-notes">
          <h3>기타 사항</h3>
          <p>{mentorData.notes || "기타 정보 없음"}</p>
        </div>
      </div>
    </div>
  );
}

export default MentorProfileDetail;
