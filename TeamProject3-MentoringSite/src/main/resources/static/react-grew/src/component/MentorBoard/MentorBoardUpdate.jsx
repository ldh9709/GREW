import React, { useEffect, useState, useRef } from "react";
import * as categoryApi from "../../api/categoryApi"; // 카테고리 데이터를 조회하는 API
import * as mentorProfileApi from "../../api/mentorProfileApi";
import * as mentorBoardApi from "../../api/mentorBoardApi"; // API 호출 부분
import "../../css/mentorBoardForm.css";
import { useParams, useNavigate } from "react-router-dom";  // useNavigate 추가
import { useMemberAuth } from "../../util/AuthContext";

const DEFAULT_IMAGE_URL = "/images/mentor-board/defaultImage.png"; // 기본이미지 URL 설정

function MentorBoardUpdate() {
  const { token, member } = useMemberAuth(); // 토큰과 멤버 선언하여 Context에 담긴 정보 가져오기
  const [category, setCategory] = useState(""); // 카테고리명 선언
  const [mentorBoardTitle, setMentorBoardTitle] = useState("");
  const [mentorBoardContent, setMentorBoardContent] = useState("");
  const [mentorBoardImage, setMentorBoardImage] = useState(null); // 이미지 파일
  const [imagePreview, setImagePreview] = useState(""); // 이미지 미리보기 URL
  const { mentorBoardNo } = useParams();  // URL에서 mentorBoardNo 가져오기
  const fileInputRef = useRef(null); // file input을 참조하기 위한 useRef 추가
  const navigate = useNavigate();  // 페이지 이동을 위한 navigate 훅

  const memberRef = useRef(member); // member의 최신 값 유지하기 위한 코드

  // 미리보기 URL 관리
  useEffect(() => {
    return () => {
      if (imagePreview) {
        URL.revokeObjectURL(imagePreview);
      }
    };
  }, [imagePreview]);

  useEffect(() => {
    memberRef.current = member;
  }, [member]);


  // 수정할 게시글을 가져오는 함수
  useEffect(() => {

    const fetchBoardData = async () => {
      try {

        const response = await mentorBoardApi.getMentorBoardDetail(mentorBoardNo);
        
        // 게시글 작성자 확인 멘토 권한 확인
        if (memberRef.current.memberNo !== response.data.memberNo) {
          alert("권한이 없습니다. 게시글 작성자만 수정할 수 있습니다.");
          navigate("/mentor-board/list");
          return; // 추가 동작 방지
        } else if(memberRef.current.memberRole !== "ROLE_MENTOR"){
          console.log("4번번호:",memberRef.current.memberNo);
          alert("멘토 상태일 때만 게시글 수정이 가능합니다.");
          navigate("/mentor-board/list");
          return; // 추가 동작 방지
        }

        if (response.status === 2310 && response.data) {
          const board = response.data;
          setMentorBoardTitle(board.mentorBoardTitle);
          setMentorBoardContent(board.mentorBoardContent);
          setMentorBoardImage(board.mentorBoardImage); // 이미지 파일은 초기화
          setImagePreview(board.mentorBoardImage || DEFAULT_IMAGE_URL);

          const categoryResponse = await mentorProfileApi.getMentorProfileByMemberNo(board.memberNo);
          if (categoryResponse.status === 2355 && categoryResponse.data) {
            const categoryNo = categoryResponse.data.categoryNo;
            if (categoryNo) {
              const childCategoryResponse = await categoryApi.childCategory(categoryNo);
              setCategory(childCategoryResponse.data?.categoryName || "카테고리 없음");
            } else {
              setCategory("카테고리 없음");
            }
          }
        }
      } catch (err) {
        console.error("게시글 데이터 로드 중 오류 발생:", err);
        alert("게시글 정보를 가져오는 데 실패했습니다.");
        navigate("/mentor-board/list");
      }
    };

    if (mentorBoardNo) {
      fetchBoardData();
    }
  }, [mentorBoardNo, navigate]);

  const handleSubmit = async () => {
    if (mentorBoardTitle.trim() === "" || mentorBoardContent.trim() === "") {
      alert("제목과 본문을 모두 입력해주세요.");
      return;
    }

    const formData = {
      mentorBoardTitle,
      mentorBoardContent,
      mentorBoardImage: imagePreview, // 기존 이미지 URL 사용(image upload 동작시 자동 수정됨)
    };

    try {
      const response = await mentorBoardApi.updateMentorBoard(mentorBoardNo, formData, token);
      alert("게시글이 성공적으로 수정되었습니다!");

      // 이미지 업로드 처리 (새 이미지가 있을 경우에만)
      if (mentorBoardImage && mentorBoardImage !== imagePreview) {
        await handleImageUpload(response.data.mentorBoardNo);
      }

      // 초기화
      setMentorBoardTitle("");
      setMentorBoardContent("");
      setMentorBoardImage(null); // 이미지 상태 초기화
      setImagePreview(DEFAULT_IMAGE_URL); // 미리보기 초기화

      navigate(`/mentor-board/detail/${mentorBoardNo}`); // 수정된 게시글 페이지로 이동
    } catch (err) {
      console.error("게시글 수정 실패:", err);
      alert("게시글 수정에 실패했습니다. 다시 시도해주세요.");
    }
  };

  const handleImageUpload = async (mentorBoardNo) => {
    const formData = new FormData();
    formData.append("file", mentorBoardImage);

    try {
      await mentorBoardApi.uploadMentorBoardImage(mentorBoardNo, formData);
      alert("이미지 업로드가 완료되었습니다.");
    } catch (err) {
      console.error("이미지 업로드 실패:", err);
      alert("이미지 업로드 실패!");
    }
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      if (imagePreview) {
        URL.revokeObjectURL(imagePreview);
      }

      const previewUrl = URL.createObjectURL(file);
      setMentorBoardImage(file);
      setImagePreview(previewUrl);
    }
  };

  const handleCancel = () => {
    const shouldCancel = window.confirm("게시글 수정을 취소하시겠습니까? 작성된 내용은 저장되지 않습니다.");
    if (shouldCancel) {
      navigate(-1, { replace: true });
    }
  };

  return (
    <div className="mentor-board-form-container">
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
        <label htmlFor="mentorBoardImage">썸네일 이미지 (미선택 시 기존 이미지 유지)</label>
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
        <button className="board-submit-button" onClick={handleSubmit}>수정</button>
        <button className="board-cancel-button" onClick={handleCancel}>취소</button>
      </div>
    </div>
  );
}

export default MentorBoardUpdate;
