import React, { useEffect, useState } from "react";
import * as mentorProfileApi from "../../api/mentorProfileApi";
import * as categoryApi from "../../api/categoryApi"; // 카테고리 데이터를 조회하는 API
import "../../css/MentorBoardCreate.css"; // 공통 CSS 파일
import { useParams } from "react-router-dom";

function MentorBoardCreate({
  onSubmit, // 등록 함수
  onUpdate, // 수정 함수
  onDelete, // 삭제 함수
}) {
  const [category, setCategory] = useState(""); // 카테고리명 선언
  const [mentorBoardTitle, setMentorBoardTitle] = useState(""); // 제목
  const [mentorBoardContent, setMentorBoardContent] = useState(""); // 본문
  const [mentorBoardImage, setMentorBoardImage] = useState(null); // 썸네일 이미지
  const [mentorBoardStatus, setMentorBoardStatus] = useState(1); // 게시글 상태 (1: 활성)
  const [isEditing, setIsEditing] = useState(false); // 수정 여부

  // URL에서 mentorProfileNo 가져오기
  const { mentorProfileNo } = useParams();

  useEffect(() => {
    const fetchCategory = async () => {
      try {
        const response = await mentorProfileApi.getMentorProfile(mentorProfileNo);
        if (response.status === 2355 && response.data) {
          const categoryNo = response.data.categoryNo || null;
          if (categoryNo) {
            // categoryNo에 해당하는 문자열 가져오기
            const categoryResponse = await categoryApi.childCategory(categoryNo);
            if (categoryResponse.status === 2420 && categoryResponse.data) {
              setCategory(categoryResponse.data.categoryName || "카테고리 없음");
            } else {
              setCategory("카테고리를 불러오지 못했습니다.");
            }
          } else {
            setCategory("카테고리 없음");
          }
        } else {
          setCategory("카테고리를 불러오지 못했습니다.");
        }
      } catch (err) {
        console.error("카테고리 데이터 로드 중 오류 발생:", err);
        setCategory("오류 발생");
      }
    };

    if (mentorProfileNo) {
      fetchCategory();
    }
  }, [mentorProfileNo]);

  const handleSubmit = () => {
    if (mentorBoardTitle.trim() === "" || mentorBoardContent.trim() === "") {
      alert("제목과 본문을 모두 입력해주세요.");
      return;
    }

    const formData = new FormData();
    formData.append("mentorBoardTitle", mentorBoardTitle);
    formData.append("mentorBoardContent", mentorBoardContent);
    formData.append("mentorBoardImage", mentorBoardImage);
    formData.append("mentorBoardStatus", mentorBoardStatus);

    onSubmit(formData);

    setMentorBoardTitle("");
    setMentorBoardContent("");
    setMentorBoardImage(null);
  };

  const handleUpdate = () => {
    if (mentorBoardTitle.trim() === "" || mentorBoardContent.trim() === "") {
      alert("수정할 제목과 본문을 입력해주세요.");
      return;
    }

    const formData = new FormData();
    formData.append("mentorBoardTitle", mentorBoardTitle);
    formData.append("mentorBoardContent", mentorBoardContent);
    formData.append("mentorBoardImage", mentorBoardImage);
    formData.append("mentorBoardStatus", mentorBoardStatus);

    onUpdate(formData);
  };

  const handleDelete = () => {
    onDelete();
  };

  return (
    <div className="mentor-board-form-container">
      <div className="field">
        <label htmlFor="category">분야</label>
        <span className="category">{category}</span>
      </div>

      <div className="field">
        <label htmlFor="mentorBoardTitle">제목</label>
        <input
          type="text"
          id="mentorBoardTitle"
          placeholder="제목을 입력해주세요."
          value={mentorBoardTitle}
          onChange={(e) => setMentorBoardTitle(e.target.value)}
        />
      </div>

      <div className="field">
        <label htmlFor="mentorBoardImage">썸네일 이미지</label>
        <input
          type="file"
          id="mentorBoardImage"
          onChange={(e) => setMentorBoardImage(e.target.files[0])}
        />
      </div>

      <div className="field">
        <label htmlFor="mentorBoardContent">본문</label>
        <textarea
          id="mentorBoardContent"
          placeholder="내용을 입력해주세요."
          value={mentorBoardContent}
          onChange={(e) => setMentorBoardContent(e.target.value)}
        />
      </div>

      <div className="button-group">
        {!isEditing ? (
          <button className="submit-button" onClick={handleSubmit}>
            등록
          </button>
        ) : (
          <>
            <button className="update-button" onClick={handleUpdate}>
              수정
            </button>
            <button className="delete-button" onClick={handleDelete}>
              삭제
            </button>
          </>
        )}
      </div>
    </div>
  );
}

export default MentorBoardCreate;
