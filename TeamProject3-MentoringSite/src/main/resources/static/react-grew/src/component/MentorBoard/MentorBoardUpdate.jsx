import React, { useState, useEffect } from 'react';
import { getMentorBoardDetail, updateMentorBoard } from '../../api/mentorBoardApi';

const MentorBoardUpdate = ({ mentorBoardNo }) => {
  const [formData, setFormData] = useState({
    title: '',
    content: '',
  });

  useEffect(() => {
    const fetchBoardDetail = async () => {
      try {
        const data = await getMentorBoardDetail(mentorBoardNo);
        setFormData(data);
      } catch (error) {
        console.error('멘토 보드 상세 정보를 가져오는 데 실패했습니다.', error);
      }
    };

    fetchBoardDetail();
  }, [mentorBoardNo]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await updateMentorBoard(mentorBoardNo, formData);
      alert('멘토 보드가 성공적으로 수정되었습니다.');
      console.log(response);
    } catch (error) {
      alert('멘토 보드 수정에 실패했습니다.');
      console.error(error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>제목:</label>
        <input type="text" name="title" value={formData.title} onChange={handleChange} required />
      </div>
      <div>
        <label>내용:</label>
        <textarea name="content" value={formData.content} onChange={handleChange} required />
      </div>
      <button type="submit">수정</button>
    </form>
  );
};

export default MentorBoardUpdate;
