import React, { useEffect, useState } from "react";
import * as mentorApi from "../../api/mentorApi"; // API 호출 경로는 실제 프로젝트 구조에 맞게 설정하세요.
import "./MentorProfileDetail.css"; // 스타일은 필요 시 별도로 설정.

function MentorProfileDetail({ memberNo }) {
  const [mentorData, setMentorData] = useState(null); // 초기 상태를 null로 설정
  const [loading, setLoading] = useState(true); // 로딩 상태 표시
  const [error, setError] = useState(null); // 에러 상태 표시

  // memberNo가 변경될 때마다 멘토 데이터를 새로 가져옵니다.
  useEffect(() => {
    const fetchMentorData = async () => {
      try {
        // API 호출
        const response = await mentorApi.getMentorProfile(memberNo);
        if (response.status === 2355 && response.data) {
          setMentorData(response.data);
        } else {
          setError("데이터를 불러오는 데 실패했습니다.");
        }
      } catch (err) {
        console.error("API 호출 중 오류 발생:", err);
        setError("서버 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };

    if (memberNo) {
      fetchMentorData();
    } else {
      setError("회원 번호가 제공되지 않았습니다.");
      setLoading(false);
    }
  }, [memberNo]); // memberNo가 변경될 때마다 API 호출

  if (loading) return <div className="loading">로딩 중...</div>;
  if (error) return <div className="error">{error}</div>;
  if (!mentorData) return <div className="no-data">멘토 데이터가 없습니다.</div>;

  return (
    <div className="mentor-profile-container">
      {/* 왼쪽: 프로필 사진 및 기본 정보 */}
      <div className="mentor-left">
        <div className="mentor-image">
          <img
            src={mentorData.mentorImage || "https://via.placeholder.com/150"}
            alt="Mentor Profile"
          />
        </div>
        <div className="mentor-introduction">
          <h2>{mentorData.mentorIntroduce || "멘토 소개가 없습니다."}</h2>
        </div>
      </div>

      {/* 오른쪽: 멘토 상세 정보 */}
      <div className="mentor-right">
        <h3>멘토 상세 정보</h3>
        <div className="mentor-stats">
          <div>
            <strong>활동 수:</strong> {mentorData.mentorActivityCount || 0}
          </div>
          <div>
            <strong>팔로워 수:</strong> {mentorData.mentorFollowCount || 0}
          </div>
          <div>
            <strong>멘토링 수:</strong> {mentorData.mentorMentoringCount || 0}
          </div>
          <div>
            <strong>평점:</strong> {mentorData.mentorRating?.toFixed(2) || "0.00"} / 5.0
          </div>
          <div>
            <strong>상태:</strong> {mentorData.mentorStatus === 1 ? "활성" : "비활성"}
          </div>
          <div>
            <strong>카테고리 번호:</strong> {mentorData.categoryNo || "정보 없음"}
          </div>
          <div>
            <strong>회원 번호:</strong> {mentorData.memberNo}
          </div>
        </div>

        {/* 경력 정보 */}
        <div className="mentor-career">
          <strong>경력:</strong> {mentorData.mentorCareer || "경력 정보가 없습니다."}
        </div>
      </div>
    </div>
  );
}

export default MentorProfileDetail;
