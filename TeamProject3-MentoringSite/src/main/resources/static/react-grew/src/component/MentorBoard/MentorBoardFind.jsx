import React, { useState, useEffect } from 'react';
import { getMentorBoardDetail } from '../../api/mentorBoardApi';

const MentorBoardFind = ({ mentorBoardNo }) => {
  const [boardDetail, setBoardDetail] = useState(null);

  useEffect(() => {
    const fetchBoardDetail = async () => {
      try {
        const data = await getMentorBoardDetail(mentorBoardNo);
        setBoardDetail(data);
      } catch (error) {
        console.error('멘토 보드 상세 정보를 가져오는 데 실패했습니다.', error);
      }
    };

    fetchBoardDetail();
  }, [mentorBoardNo]);

  if (!boardDetail) return <p>로딩 중...</p>;

  return (
    <div>
      <h1>{boardDetail.title}</h1>
      <p>{boardDetail.content}</p>
    </div>
  );
};

export default MentorBoardFind;
