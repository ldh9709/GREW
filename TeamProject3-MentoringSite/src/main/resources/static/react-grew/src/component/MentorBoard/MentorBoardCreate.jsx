import React, { useEffect, useState } from "react";
import * as categoryApi from "../../api/categoryApi"; // 카테고리 데이터를 조회하는 API
import * as mentorProfileApi from "../../api/mentorProfileApi";
import * as mentorBoardApi from "../../api/mentorBoardApi"; // API 호출 부분
import "../../css/MentorBoardCreate.css";
import { useParams } from "react-router-dom";

function MentorBoardCreate({ onSubmit }) {
  const [category, setCategory] = useState(""); // 카테고리명 선언
  const [mentorBoardTitle, setMentorBoardTitle] = useState("");
  const [mentorBoardContent, setMentorBoardContent] = useState("");
  const [mentorBoardImage, setMentorBoardImage] = useState(null); // 이미지 파일
  const [mentorBoardImageUrl, setMentorBoardImageUrl] = useState(""); // 이미지 URL 상태
  const { mentorProfileNo } = useParams();  // URL에서 mentorProfileNo 가져오기
  const [mentorBoardNo, setMentorBoardNo] = useState(null); // 등록된 mentorBoardNo 저장

  
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
      mentorBoardImage: "", // 이미지 URL은 빈 값으로 초기화
      memberNo: memberNo,
    };
  
    try {
      // 게시글 등록 API 호출
      const response = await mentorBoardApi.createMentorBoard(formData);
      const mentorBoardNo = response.data.mentorBoardNo; // 응답에서 mentorBoardNo 추출
      setMentorBoardNo(mentorBoardNo); // 등록된 mentorBoardNo 저장
      alert("게시글이 성공적으로 등록되었습니다!");
  
      // 이미지 업로드 처리
      if (mentorBoardNo && mentorBoardImage) {
        await handleImageUpload(mentorBoardNo); // 이미지 업로드 진행
      }
  
      // 초기화
      setMentorBoardTitle("");
      setMentorBoardContent("");
      setMentorBoardImage(null);
    } catch (err) {
      console.error("게시글 등록 실패:", err);
      alert("게시글 등록에 실패했습니다.");
    }
  };
  
  const handleImageUpload = async (mentorBoardNo) => {
    if (!mentorBoardImage) {
      alert("이미지 파일을 선택해 주세요.");
      return;
    }
  
    const formData = new FormData();
    formData.append("file", mentorBoardImage);
    console.log("업로드할 파일:", mentorBoardImage);  // 선택된 파일 확인
  
    try {
      // 이미지 업로드 API 호출
      const response = await mentorBoardApi.uploadMentorBoardImage(mentorBoardNo, formData);
      console.log("업로드 응답:", response);  // 서버 응답 확인
      const uploadedImageUrl = response.data; // 업로드된 이미지 URL
  
      // 이미지 URL 상태 업데이트
      setMentorBoardImageUrl(uploadedImageUrl);
      alert("이미지 업로드가 완료되었습니다.");
  
    } catch (err) {
      console.error("이미지 업로드 실패:", err);  // 에러 메시지 출력
      alert("이미지 업로드 실패!");
    }
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
        <button className="submit-button" onClick={handleSubmit}>
          등록
        </button>
      </div>
    </div>
  );
}

export default MentorBoardCreate;
