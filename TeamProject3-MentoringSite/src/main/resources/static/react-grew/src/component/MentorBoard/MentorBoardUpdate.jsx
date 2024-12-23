import React, { useEffect, useState, useRef } from "react";
import * as categoryApi from "../../api/categoryApi"; // 카테고리 데이터를 조회하는 API
import * as mentorProfileApi from "../../api/mentorProfileApi";
import * as mentorBoardApi from "../../api/mentorBoardApi"; // API 호출 부분
import "../../css/MentorBoardForm.css";
import { useParams, useNavigate } from "react-router-dom";  // useNavigate 추가

function MentorBoardUpdate() {
  const [category, setCategory] = useState(""); // 카테고리명 선언
  const [mentorBoardTitle, setMentorBoardTitle] = useState("");
  const [mentorBoardContent, setMentorBoardContent] = useState("");
  const [mentorBoardImage, setMentorBoardImage] = useState(null); // 이미지 파일
  const [imagePreview, setImagePreview] = useState(""); // 이미지 미리보기 URL
  const { mentorBoardNo } = useParams();  // URL에서 mentorBoardNo 가져오기
  const fileInputRef = useRef(null); // file input을 참조하기 위한 useRef 추가

  const navigate = useNavigate();  // 페이지 이동을 위한 navigate 훅

  // 수정할 게시글을 가져오는 함수
  useEffect(() => {
    const fetchBoardData = async () => {
      try {
        const response = await mentorBoardApi.getMentorBoard(mentorBoardNo);
        if (response.status === 2310 && response.data) {
          const board = response.data;
          setMentorBoardTitle(board.mentorBoardTitle);
          setMentorBoardContent(board.mentorBoardContent);
          setMentorBoardImage(board.mentorBoardImage); // 기존 이미지 URL 저장
          setImagePreview(board.mentorBoardImage); // 기존 이미지 URL로 미리보기 설정
          
          const categoryResponse = await mentorProfileApi.getMentorProfile(board.memberNo);
          if (categoryResponse.status === 2355 && categoryResponse.data) {
            const categoryNo = categoryResponse.data.categoryNo;
            if (categoryNo) {
              const childCategoryResponse = await categoryApi.childCategory(categoryNo);
              if (childCategoryResponse.status === 2420 && childCategoryResponse.data) {
                setCategory(childCategoryResponse.data.categoryName || "카테고리 없음");
              }
            } else {
              setCategory("카테고리 없음");
            }
          }
        }
      } catch (err) {
        console.error("게시글 데이터 로드 중 오류 발생:", err);
        alert("게시글 정보를 가져오는 데 실패했습니다.");
      }
    };
  
    if (mentorBoardNo) {
      fetchBoardData();
    }
  }, [mentorBoardNo]);

  const handleSubmit = async () => {
    if (mentorBoardTitle.trim() === "" || mentorBoardContent.trim() === "") {
      alert("제목과 본문을 모두 입력해주세요.");
      return;
    }

    // 수정된 게시글 정보를 포함하는 객체
    const formData = {
      mentorBoardTitle,
      mentorBoardContent,
      mentorBoardImage: imagePreview, // 기존 이미지 URL 사용(image upload 동작시 자동 수정됨)
    };

    try {
      // 게시글 수정 API 호출
      const response = await mentorBoardApi.updateMentorBoard(mentorBoardNo, formData);
      alert("게시글이 성공적으로 수정되었습니다!");

      // 이미지 업로드 처리 (이미지 변경이 있을 경우에만)
    if (mentorBoardImage && imagePreview && mentorBoardImage !== imagePreview) {
        await handleImageUpload(response.data.mentorBoardNo);
    }

      // 초기화
      setMentorBoardTitle("");
      setMentorBoardContent("");
      setMentorBoardImage(null); // 이미지 상태 초기화
      setImagePreview(""); // 미리보기 초기화

      navigate(`/mentorboard/detail/${mentorBoardNo}`); // 수정된 게시글 페이지로 이동
    } catch (err) {
      console.error("게시글 수정 실패:", err);
      alert("게시글 수정에 실패했습니다.");
    }
  };

  const handleImageUpload = async (mentorBoardNo) => {
     
    const formData = new FormData();
    formData.append("file", mentorBoardImage);

    try {
    // 이미지 업로드 API 호출
    const response = await mentorBoardApi.uploadMentorBoardImage(mentorBoardNo, formData);
    console.log("업로드 응답:", response);  // 서버 응답 확인
    alert("이미지 업로드가 완료되었습니다.");
    } catch (err) {
    console.error("이미지 업로드 실패:", err);  // 에러 메시지 출력
    alert("이미지 업로드 실패!");
    }

  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setMentorBoardImage(file);
      const previewUrl = URL.createObjectURL(file);  // 파일 URL 생성
      setImagePreview(previewUrl);  // 미리보기 URL 업데이트
    }
  };

  // 취소 버튼 클릭 시 이전 페이지로 이동
  const handleCancel = () => {
    const shouldCancel = window.confirm("게시글 수정을 취소할까요?\n(작성된 내용은 사라집니다.)");
    if (shouldCancel) {
      navigate(-1); // 이전 페이지로 이동
    }
  };

  return (
    <div className="mentor-board-form-container">
      {/* 상단 제목 추가 */}
      <h1 className="form-title">멘토 콘텐츠 수정하기</h1>

      <div className="field">
        <label htmlFor="category">전문 분야</label>
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
        <label htmlFor="mentorBoardImage">썸네일 이미지(미선택시 기존 이미지 유지)</label>
        <input
          type="file"
          id="mentorBoardImage"
          ref={fileInputRef}  // file input을 ref로 연결
          onChange={handleImageChange} // 이미지 선택 시 미리보기 함수 호출
        />
        {imagePreview && (
          <div className="image-preview">
            <img src={imagePreview} alt="Preview" />
          </div>
        )}
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
        <button className="submit-button" onClick={handleSubmit}>
          수정
        </button>
        <button className="cancel-button" onClick={handleCancel}>
          취소
        </button>
      </div>
    </div>
  );
}

export default MentorBoardUpdate;
