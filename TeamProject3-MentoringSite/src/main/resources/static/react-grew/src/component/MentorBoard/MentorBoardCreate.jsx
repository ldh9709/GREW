import React, { useEffect, useState, useRef } from "react";
import * as categoryApi from "../../api/categoryApi"; // 카테고리 데이터를 조회하는 API
import * as mentorProfileApi from "../../api/mentorProfileApi";
import * as mentorBoardApi from "../../api/mentorBoardApi"; // API 호출 부분
import "../../css/mentorBoardForm.css";
import { useParams, useNavigate } from "react-router-dom";  // useNavigate 추가

function MentorBoardCreate() {
  const [category, setCategory] = useState(""); // 카테고리명 선언
  const [mentorBoardTitle, setMentorBoardTitle] = useState("");
  const [mentorBoardContent, setMentorBoardContent] = useState("");
  const [mentorBoardImage, setMentorBoardImage] = useState(null); // 이미지 파일
  const [imagePreview, setImagePreview] = useState(""); // 이미지 미리보기 URL
  const { mentorProfileNo } = useParams();  // URL에서 mentorProfileNo 가져오기
  const fileInputRef = useRef(null); // file input을 참조하기 위한 useRef 추가

  // 기본이미지 URL 설정 (컴포넌트 로드 시 한번만 호출)
  const defaultImageUrl = "/images/mentor-board/defaultImage.png";

  const navigate = useNavigate();  // 페이지 이동을 위한 navigate 훅

  useEffect(() => {
    // 처음 로딩할 때만 기본 이미지 미리보기 설정
    setImagePreview(defaultImageUrl);
  }, []);

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
  }, );

  // 테스트용 임시 memberNo 설정
  const memberNo = 8; // 여기서 임시로 지정한 memberNo를 사용합니다.

  const handleSubmit = async () => {
    if (mentorBoardTitle.trim() === "" || mentorBoardContent.trim() === "") {
      alert("제목과 본문을 모두 입력해주세요.");
      return;
    }
  
    // 게시글을 먼저 등록합니다.
    const formData = {
      mentorBoardTitle,
      mentorBoardContent,
      mentorBoardImage: defaultImageUrl,
      memberNo: memberNo,
    };
  
    try {
      // 게시글 등록 API 호출
      const response = await mentorBoardApi.createMentorBoard(formData);
      const mentorBoardNo = response.data.mentorBoardNo; // 응답에서 mentorBoardNo 추출
      alert("게시글이 성공적으로 등록되었습니다!");
  
      // 이미지 업로드 처리
      if (mentorBoardNo && mentorBoardImage && mentorBoardImage !== defaultImageUrl) {
        await handleImageUpload(mentorBoardNo); // 이미지 업로드 진행
      } else {
        alert("기본 이미지가 선택되었습니다.");
      }
  
      // 초기화
      setMentorBoardTitle("");
      setMentorBoardContent("");
      setMentorBoardImage(null); // 이미지 상태 초기화
      setImagePreview(""); // 미리보기 초기화

      // input[type="file"] 초기화
      if (fileInputRef.current) {
        fileInputRef.current.value = "";  // file input 초기화
      }

    } catch (err) {
      console.error("게시글 등록 실패:", err);
      alert("게시글 등록에 실패했습니다.");
    }
  };
  
  const handleImageUpload = async (mentorBoardNo) => {
    // 기본 이미지는 업로드하지 않음
    if (mentorBoardImage === defaultImageUrl) {
      console.log("기본 이미지가 선택되어 업로드하지 않습니다.");
      return; // 기본 이미지는 업로드하지 않음
    }
  
    // 사용자가 선택한 이미지(file객체)를 formData 객체에 추가
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
    const shouldCancel = window.confirm("게시글 작성을 취소할까요?\n(작성된 내용은 사라집니다.)");
    if (shouldCancel) {
      navigate(-1); // 이전 페이지로 이동
    }
  };

  return (
    <div className="mentor-board-form-container">
      {/* 상단 제목 추가 */}
      <h1 className="form-title">멘토 콘텐츠 작성하기</h1>
  
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
        <label htmlFor="mentorBoardImage">썸네일 이미지(미선택시 기본이미지가 선택됩니다)</label>
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
          등록
        </button>
        <button className="cancel-button" onClick={handleCancel}>
          취소
        </button>
      </div>
    </div>
  );
}

export default MentorBoardCreate;