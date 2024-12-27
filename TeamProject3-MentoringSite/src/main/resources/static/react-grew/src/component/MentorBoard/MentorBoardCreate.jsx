import React, { useEffect, useState, useRef } from "react";
import * as mentorProfileApi from "../../api/mentorProfileApi";
import * as mentorBoardApi from "../../api/mentorBoardApi"; // API 호출 부분
import "../../css/mentorBoardForm.css";
import { useNavigate } from "react-router-dom";  // useNavigate 추가
import { useMemberAuth } from "../../util/AuthContext"

const DEFAULT_IMAGE_URL = "/images/mentor-board/defaultImage.png"; // 기본이미지 URL 설정

function MentorBoardCreate() {
  const { token, member } = useMemberAuth(); // 토큰과 멤버 선언하여 Context에 담긴 정보 가져오기
  const memberNo = member?.memberNo || null;  // member에서 memberNo 추출, 값이 없을경우 null값 입력력
  const [mentorBoardTitle, setMentorBoardTitle] = useState("");
  const [mentorBoardContent, setMentorBoardContent] = useState("");
  const [mentorBoardImage, setMentorBoardImage] = useState(null); // 이미지 파일
  const [imagePreview, setImagePreview] = useState(""); // 이미지 미리보기 URL
  const [categoryName, setCategoryName] = useState(""); // 카테고리명 선언
  const fileInputRef = useRef(null); // file input을 참조하기 위한 useRef 추가
  const [categoryNo, setCategoryNo] = useState(null);

  const navigate = useNavigate();  // 페이지 이동을 위한 navigate 훅

  useEffect(() => {
    // 처음 로딩할 때만 기본 이미지 미리보기 설정
    setImagePreview(DEFAULT_IMAGE_URL);
  }, []);

  useEffect(() => {
    // 미리보기 URL 변경 시 이전 URL 해제
    return () => {
      if (imagePreview) {
        URL.revokeObjectURL(imagePreview);
      }
    };
  }, [imagePreview]);

  useEffect(() => {
    const fetchCategory = async () => {
      try {

        // 멘토권한 확인
        if (!member || member?.memberRole !== "ROLE_MENTOR") {
          alert("멘토만 게시글을 작성할 수 있습니다.");
          navigate(-1);
          return; // 추가 동작 방지
        }

        // 프로필 정보 조회
        const profileResponse = await mentorProfileApi.getMentorProfileByMemberNo(memberNo);

        // 프로필 정보를 통해 카테고리 번호 조회
        if (profileResponse.status !== 2355 || !profileResponse.data?.categoryNo) {
          setCategoryName("프로필 정보를 불러오지 못했습니다.");
          return;
        }
        
        setCategoryNo(profileResponse.data.categoryNo);
        setCategoryName(profileResponse.data.categoryName);

      } catch (err) {
        console.error("카테고리 데이터 로드 중 오류 발생:", err);
        setCategoryName("카테고리 오류 발생");
      }
    };

    if (memberNo) {
      fetchCategory();
    }
  }, [memberNo, member, navigate]);


  // 등록 버튼 누를시
  const handleSubmit = async () => {
    if (mentorBoardTitle.trim() === "" || mentorBoardContent.trim() === "") {
      alert("제목과 본문을 모두 입력해주세요.");
      return;
    }
  

    // 게시글을 먼저 등록합니다.
    const formData = {
      mentorBoardTitle,
      mentorBoardContent,
      mentorBoardImage: DEFAULT_IMAGE_URL, // 기본 이미지를 선택
      memberNo: memberNo,
      categoryNo,
      categoryName,
    };
  
    try {
      // 게시글 등록 API 호출
      console.log("메소드동작전 token",token);
      console.log("메소드동작전 formdata",formData);
      const response = await mentorBoardApi.createMentorBoard(token,formData);
      console.log("메소드동작후 token",token);
      console.log("메소드동작후 formdata",formData);
      console.log("생성된 멘토보드:",response);
      const mentorBoardNo = response.data.mentorBoardNo; // 응답에서 mentorBoardNo 추출
      alert("게시글이 성공적으로 등록되었습니다!");
  
      // 이미지 업로드 처리
      if (mentorBoardNo && mentorBoardImage && mentorBoardImage !== DEFAULT_IMAGE_URL) {
        await handleImageUpload(mentorBoardNo); // 이미지 업로드 진행
      } else {
        alert("기본 이미지가 선택되었습니다.");
      }
  
      // 초기화
      setMentorBoardTitle("");
      setMentorBoardContent("");
      setMentorBoardImage(null); // 이미지 상태 초기화
      setImagePreview(DEFAULT_IMAGE_URL); // 미리보기 초기화

      // input[type="file"] 초기화
      if (fileInputRef.current) {
        fileInputRef.current.value = "";  // file input 초기화
      }

      navigate(`/mentor-board/detail/${mentorBoardNo}`); // 수정된 게시글 페이지로 이동
      
    } catch (err) {
      console.error("게시글 등록 실패:", err);
      alert("게시글 등록에 실패했습니다.");
    }
  };
  
  const handleImageUpload = async (mentorBoardNo) => {

    if (!mentorBoardImage) return; // 이미지 없으면 업로드 생략
  
    // 사용자가 선택한 이미지(file객체)를 formData 객체에 추가
    const formData = new FormData();
    formData.append("file", mentorBoardImage);
  
    try {
      // 이미지 업로드 API 호출
      await mentorBoardApi.uploadMentorBoardImage(mentorBoardNo, formData);
      alert("이미지 업로드가 완료되었습니다.");
    } catch (err) {
      console.error("이미지 업로드 실패:", err);  // 에러 메시지 출력
      alert("이미지 업로드 실패!");
    }
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      // 이전 미리보기 URL 해제
      if (imagePreview) {
        URL.revokeObjectURL(imagePreview);
      }

      const previewUrl = URL.createObjectURL(file); // 새 미리보기 URL 생성
      setMentorBoardImage(file);
      setImagePreview(previewUrl);
    }
  };

  // 취소 버튼 클릭 시 이전 페이지로 이동
  const handleCancel = () => {
    const shouldCancel = window.confirm("게시글 작성을 취소할까요?\n(작성된 내용은 사라집니다.)");
    if (shouldCancel) {
      navigate(-1, { replace: true }); // 이전 페이지로 이동, 브라우저 기록 덮어쓰기
    }
  };

  return (
    <div className="mentor-board-form-container">
      {/* 상단 제목 추가 */}
      <h1 className="form-title">멘토 콘텐츠 작성하기</h1>
  
      <div className="field">
        <label htmlFor="category">전문 분야</label>
        <span className="category">{categoryName}</span>
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