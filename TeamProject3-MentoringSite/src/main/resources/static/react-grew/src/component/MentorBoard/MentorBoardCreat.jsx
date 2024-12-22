import React, { useState } from 'react';
import { createMentorBoard } from '../../api/mentorBoardApi';

const MentorBoardCreat = () => {
  const [formData, setFormData] = useState({
    title: '',
    content: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await createMentorBoard(formData);
      alert('멘토 보드가 성공적으로 생성되었습니다.');
      console.log(response);
    } catch (error) {
      alert('멘토 보드 생성에 실패했습니다.');
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
      <button type="submit">생성</button>
    </form>
  );
};

export default MentorBoardCreat;
