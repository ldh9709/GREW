import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../../api/mentorBoardApi'; // 수정된 경로
import '../../css/mentorBoard.css'; // 수정된 경로


const MentorBoardUpdate = () => {
    const { id } = useParams(); // URL의 파라미터로 멘토보드 ID 가져오기
    const [formData, setFormData] = useState({
        mentorBoardTitle: '',
        mentorBoardContent: '',
        mentorBoardImage: null,
    });
    const navigate = useNavigate();

    // 페이지 로드 시 기존의 멘토 보드 데이터를 불러오기
    useEffect(() => {
        api.getMentorBoardDetail(id)
            .then((data) => setFormData(data))
            .catch((error) => console.error('멘토보드 데이터를 불러오는 중 오류 발생:', error));
    }, [id]);

    // 입력 필드 변경 이벤트 처리
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    // 파일 선택 이벤트 처리
    const handleFileChange = (e) => {
        setFormData({ ...formData, mentorBoardImage: e.target.files[0] });
    };

    // 폼 제출 이벤트 처리
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await api.updateMentorBoard(id, formData);
            alert('멘토보드가 성공적으로 수정되었습니다.');
            navigate(`/mentor-board/${id}`); // 수정 후 상세 페이지로 이동
        } catch (error) {
            alert('수정에 실패했습니다. 다시 시도해주세요.');
            console.error('멘토보드 수정 중 오류 발생:', error);
        }
    };

    return (
        <div className="mentor-board-form">
            <h1>멘토보드 수정</h1>
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
                <button type="submit">수정</button>
            </form>
        </div>
    );
};

export default MentorBoardUpdate;
