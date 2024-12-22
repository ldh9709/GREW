import React, { useEffect, useState } from 'react'; // React와 필요한 훅들 import
import { useParams } from 'react-router-dom'; // useParams import
import { getMentorBoardDetail } from '../../api/mentorBoardApi'; // API 함수 import
import '../../css/mentorBoard.css'; // 스타일 import

const MentorBoardDetail = () => {
  const { mentorBoardNo } = useParams(); // URL 파라미터에서 mentorBoardNo 가져오기
  const [board, setBoard] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const calculateRelativeDate = (dateString) => {
    const now = new Date();
    const postDate = new Date(dateString);
    const diffInSeconds = Math.floor((now - postDate) / 1000);

    if (diffInSeconds < 60) return "방금 전";
    if (diffInSeconds < 3600) return `${Math.floor(diffInSeconds / 60)}분 전`;
    if (diffInSeconds < 86400) return `${Math.floor(diffInSeconds / 3600)}시간 전`;
    if (diffInSeconds < 604800) return `${Math.floor(diffInSeconds / 86400)}일 전`;
    if (diffInSeconds < 2419200) return `${Math.floor(diffInSeconds / 604800)}주 전`;
    return `${Math.floor(diffInSeconds / 2419200)}개월 전`;
  };

  useEffect(() => {
    const fetchBoardDetail = async () => {
      try {
        setLoading(true);
        const response = await getMentorBoardDetail(mentorBoardNo);
        console.log(response)
        setBoard(response.data); // API 응답 데이터 저장
      } catch (err) {
        setError('멘토 보드 상세 데이터를 가져오는 중 오류가 발생했습니다.');
      } finally {
        setLoading(false);
      }
    };

    fetchBoardDetail();
  }, [mentorBoardNo]);

  if (loading) return <p>로딩 중입니다...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="mentor-board-detail">
      <h1>{board?.mentorBoardTitle || "제목 없음"}</h1>
      <img
        src={board?.mentorBoardImage || '/default-thumbnail.png'}
        alt="보드 이미지"
        className="board-detail-image"
      />
      <p>{board?.mentorBoardContent || "내용 없음"}</p>
      <div className="board-detail-meta">
        <span>조회수: {board?.mentorBoardViews}</span>
        <span>작성일: {calculateRelativeDate(board.mentorBoardDate)}</span>
      </div>
    </div>
  );
};

export default MentorBoardDetail; // 반드시 default export 추가
