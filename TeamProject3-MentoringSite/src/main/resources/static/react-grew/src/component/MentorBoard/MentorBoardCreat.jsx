import React, { useState } from 'react';
import api from '../../api/mentorBoardApi'; // 수정된 경로
import '../../css/mentorBoard.css'; // 수정된 경로


const MentorBoardCreate = () => {
    const [formData, setFormData] = useState({
        mentorBoardTitle: '',
        mentorBoardContent: '',
        mentorBoardImage: null,
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleFileChange = (e) => {
        setFormData({ ...formData, mentorBoardImage: e.target.files[0] });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await api.createMentorBoard(formData);
            alert('멘토보드가 성공적으로 등록되었습니다.');
        } catch (error) {
            alert('등록에 실패했습니다. 다시 시도해주세요.');
            console.error(error);
        }
    };

    return (
        <div className="mentor-board-form">
            <h1>멘토보드 등록</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>제목</label>
                    <input
                        type="text"
                        name="mentorBoardTitle"
                        value={formData.mentorBoardTitle}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>내용</label>
                    <textarea
                        name="mentorBoardContent"
                        value={formData.mentorBoardContent}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>이미지</label>
                    <input
                        type="file"
                        name="mentorBoardImage"
                        onChange={handleFileChange}
                    />
                </div>
                <button type="submit">등록</button>
            </form>
        </div>
    );
};

export default MentorBoardCreate;
