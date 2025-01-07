import "../../css/mentorJoin.css";
import React, { useEffect, useRef, useState } from "react";
import * as responseStatus from "../../api/responseStatusCode";
import * as memberApi from "../../api/memberApi";
import * as categoryApi from "../../api/categoryApi";
import { useNavigate } from "react-router-dom";
import { useMemberAuth } from "../../util/AuthContext";

const MentorJoinForm = () => {
  /***** Context 가져오기 START *****/
  const auth = useMemberAuth(); // 사용자 인증 정보를 가져온다
  const token = auth?.token || null; // 사용자 인증 토큰
  const login = auth.login; //사용자 로그인 관련(토큰 수정)
  const member = auth?.member || {}; // 사용자 관련 정보 객체
  const mentorProfileNo = token ? member.mentorProfileNo : null; // 사용자 멘토 프로필 번호
  /***** Context 가져오기 END *****/
  
  /* 이미지 파일을 위한 메소드 선언 */
  const [mentorImage, setMentorImage] = useState(null); // 이미지 파일
  const [imagePreview, setImagePreview] = useState(""); // 이미지 미리보기 URL
  const fileInputRef = useRef(null); // file input 참조

  /***** 네비게이트 *****/
  const navigate = useNavigate();


  /**** 멘토 선언 START ****/
  const [mentor, setMentor] = useState({
      categoryNo: "",
      mentorStatus: 2,
      mentorIntroduce: "",
      mentorHeadline: "",
      careerDtos: [{ careerCompanyName: "", careerJobTitle: "", careerStartDate: "", careerEndDate: "", mentorProfileNo: mentorProfileNo}],
      mentorImage: "",
    });
  /**** 멘토 선언 END ****/


  /**** 카테고리 선언 START ****/
  const [categories, setCategories] = useState([]);

  //selectedParent / selectedChild
  const [selectedParent, setSelectedParent] = useState("");
  const [selectedChild, setSelectedChild] = useState("");

  /**** 카테고리 선언 END ****/

  

  const addCareerField = () => {
    setMentor((prevMentor) => ({
      ...prevMentor,
      careerDtos: [
        ...prevMentor.careerDtos,
        { careerCompanyName: "", careerJobTitle: "", careerStartDate: "", careerEndDate: "", mentorProfileNo: mentorProfileNo},
      ],
    }));
  };

  const handleCareerChange = (index, field, value) => {
    setMentor((prevMentor) => {
      const updatedCareers = [...prevMentor.careerDtos];
      updatedCareers[index][field] = value;
      return {
        ...prevMentor,
        careerDtos: updatedCareers,
      };
    });
  };

  const handleFocus = (e) => {
    e.target.type = "date"; // 포커스되면 type을 date로 변경
  };

  const handleBlur = (e) => {
    if (!e.target.value) {
      e.target.type = "text"; // 값이 없으면 type을 다시 text로 변경
    }
  };

  const skip = () => {
    alert("멘토 신청은 마이페이지에서 다시 할 수 있습니다.");
    navigate('/main');
    return;
  }
  /********** 경력 관련 메소드 END ***********/

  
  /********** 카테고리 트리 구조 불러오기 START ***********/
  const getCategories = async () => {
    // 서버에서 이미 대분류 객체의 childCategories에 소분류가 들어있는 구조를 내려준다고 가정
    const res = await categoryApi.ListCategory();
    // 여기서는 별도의 필터를 안 합니다. 
    // data 예: [
    //   { categoryNo:1, categoryName:'직무 상담', categoryDepth:1, childCategories:[
    //       { categoryNo:2, categoryName:'인사/총무/노무', categoryDepth:2, childCategories:[] },
    //       ...
    //   ]},
    //   { categoryNo:5, categoryName:'학습/교육', categoryDepth:1, childCategories:[...], ...},
    //   ...
    // ]
    setCategories(res.data);
  }

  const getCareer = async () => {
    const res = await memberApi.getCareer(mentorProfileNo);
    const careerDtos = res.data;
    setMentor({ careerDtos: careerDtos || []});
  }

  useEffect(() => {
    if(mentorProfileNo || mentorProfileNo == 0){
      getCategories();
      getCareer();
    }
  }, [mentorProfileNo]);

  // 현재 선택된 "대분류" 객체를 찾는다
  const parentObj = categories.find(
    (cat) => String(cat.categoryNo) === String(selectedParent)
  );
  // 만약 parentObj가 있으면, 그 안의 childCategories가 소분류 목록
  const childCategories = parentObj ? parentObj.childCategories : [];

  // 대분류 변경 시
  const handleParentChange = (e) => {
    const parentNo = e.target.value;
    setSelectedParent(parentNo);
    setSelectedChild(""); // 소분류 초기화

    // mentor.categoryNo도 초기화(아직 소분류를 안 골랐으므로)
    setMentor((prev) => ({
      ...prev,
      categoryNo: "",
    }));
  };

  // 소분류 변경 시
  const handleChildChange = (e) => {
    const childNo = e.target.value;
    setSelectedChild(childNo);

    // 실제 mentor.categoryNo에 저장
    setMentor((prev) => ({
      ...prev,
      categoryNo: childNo,
    }));
  };
  /********** 카테고리 트리 구조 불러오기 END ***********/

  /***** 입력 업데이트 핸들러 START *****/
  const handleChangeMentorJoinForm = (e) => {
    setMentor((prevMentor) => ({
      ...prevMentor,
      [e.target.name]: e.target.value,
    }));
  };
  /***** 입력 업데이트 핸들러 END *****/

  /***** 멘토 생성 버튼 START *****/
  const mentorProfileCreateAction = async () => {
    if (!mentor.categoryNo || !mentor.mentorIntroduce.trim()) {
      alert("필수 항목을 모두 입력해주세요.");
      return;
    }
  
    try {
      // Step 1: 멘토 프로필 생성
      let responseJsonObject = null;
      let check = true;
      mentor.careerDtos.every((career) => {
        if (career.careerStartDate && career.careerEndDate) {
          const startDate = new Date(career.careerStartDate);
          const endDate = new Date(career.careerEndDate);
          if (endDate <= startDate) {
            alert(
              `시작일(${career.careerStartDate})은 종료일(${career.careerEndDate})보다 이전이어야 합니다. 날짜를 수정해주세요.`
            );
            check = false; // 유효성 검사 실패
          }
        }
        return true; // 유효성 검사 통과
      });

      if(mentorProfileNo === 0 && check){
        responseJsonObject = await memberApi.mentorProfileCreateAction(token, mentor);
      }else if (check){
        responseJsonObject = await memberApi.mentorProfileUpdateAction(mentorProfileNo, mentor);
      }

      if (responseJsonObject.status === responseStatus.UPDATE_MENTOR_PROFILE_SUCCESS_CODE || responseJsonObject.status === responseStatus.CREATED_MENTOR_PROFILE_SUCCESS_CODE) {
        alert("멘토 신청 성공");
        navigate("/member/profile");
      } else {
        alert("등록 실패");
      }
        // Step 3: 이미지 업로드 (필수 이미지가 있다면)
      if (mentorImage && responseJsonObject) {
        await uploadImage(responseJsonObject.data.mentorProfileNo); // 생성된 번호로 이미지 업로드
      } else {
        alert("이미지 없이 멘토 프로필이 저장되었습니다.");
      }
      if(responseJsonObject.appdata){
        await login(responseJsonObject.addData.accessToken);
      }
      // 완료 후 페이지 이동
      navigate("/member/profile");
    } catch (error) {
      console.error("프로필 생성 중 오류 발생:", error);
      alert("프로필 생성 중 오류가 발생했습니다.");
    }
  };
  /***** 멘토 생성 버튼 END *****/
  
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      // 이전 미리보기 URL 해제
      if (imagePreview) {
        URL.revokeObjectURL(imagePreview);
      }

      const previewUrl = URL.createObjectURL(file);
      setMentorImage(file);
      setImagePreview(previewUrl);

      setMentor((prevMentor) => ({
        ...prevMentor,
        [e.target.name]: `/upload/mentor-profile/${mentorProfileNo}/${file.name}`,
      }));
    }
  }

  const uploadImage= async (mentorProfileNo) => {

    if(!mentorImage) {
      alert("이미지를 선택해주세요.");
      return;
    }

    const formData = new FormData();
    formData.append("file", mentorImage);


    const response = await memberApi.uploadMentorProfileImage(mentorProfileNo, mentorImage);
    console.log("이미지 업로드 response : ", response);
  }
  
  useEffect(() => {
    // 미리보기 URL 변경 시 이전 URL 해제
    return () => {
      if (imagePreview) {
        URL.revokeObjectURL(imagePreview);
      }
    };
  }, [imagePreview]);

  
  return (
    <div className="mentor-join-container">
      <h1 className="mentor-form-title">멘토 신청하기</h1>

      {/* 멘토 가입 폼 */}
      <form className="mentor-join-form">
        {/* 대/소분류 선택 폼 */}
        <label className="parent-label">
          전문분야<span className="red-text">*</span>
        </label>
        <div className="form-group-profile horizontal-combined">
          {/* 대분류 선택 */}
          <select
            className="category-select"
            value={selectedParent}
            onChange={handleParentChange}
          >
            <option value="">-- 대분류 선택 --</option>
            {categories
              .filter((cat) => cat.categoryDepth === 1) // depth=1인 것만 추려낸다
              .map((parent) => (
                <option key={parent.categoryNo} value={parent.categoryNo}>
                  {parent.categoryName}
                </option>
              ))}
          </select>

          {/* 소분류 선택 */}
          <select
            className="category-select"
            value={selectedChild}
            onChange={handleChildChange}
            disabled={!selectedParent}
          >
            <option value="">-- 소분류 선택 --</option>
            {childCategories.map((child) => (
              <option key={child.categoryNo} value={child.categoryNo}>
                {child.categoryName}
              </option>
            ))}
          </select>
        </div>
        {/* 한 줄 소개글 */}
        <div className="form-group-profile horizontal">
          <label htmlFor="mentorIntroduce" className="parent-label">
            한줄 소개<span className="red-text">*</span>
          </label>
          <textarea
            id="mentorHeadline"
            className="mentor-join-headline"
            name="mentorHeadline"
            placeholder="한 줄 소개(50자 이내)"
            rows="1"
            maxLength={50}
            value={mentor.mentorHeadline}
            onChange={handleChangeMentorJoinForm}
            required
          ></textarea>
        </div>

        {/* 소개글 */}
        <div className="form-group-profile horizontal">
          <label htmlFor="mentorIntroduce" className="parent-label">
            소개글<span className="red-text">*</span>
          </label>
          <textarea
            id="mentorIntroduce"
            className="mentor-join-introduce"
            name="mentorIntroduce"
            placeholder="소개글(1,000자 이내)"
            rows="5"
            value={mentor.mentorIntroduce}
            onChange={handleChangeMentorJoinForm}
            required
          ></textarea>
        </div>
        
        {/* 경력 START*/}
        <div className="form-group-profile horizontal">
          <label className="parent-label">
            경력<span className="red-text">*</span>
          </label>
          <div className="career-container">
          {mentor.careerDtos.map((career, index) => (
            <div key={index}>
              <div className="career-row">
                  <input
                    type="text"
                    placeholder="회사명"
                    onChange={(e) => handleCareerChange(index, "careerCompanyName", e.target.value)}
                    required
                  />
                  <input
                    type="text"
                    name="startDate"
                    onFocus={handleFocus}
                    onBlur={handleBlur}
                    onChange={(e) => handleCareerChange(index, "careerStartDate", e.target.value)}
                    placeholder="입사년월"
                    required
                  />
                  <input
                    type="text"
                    name="endDate"
                    onFocus={handleFocus}
                    onBlur={handleBlur}
                    onChange={(e) => handleCareerChange(index, "careerEndDate", e.target.value)}
                    placeholder="퇴사년월"
                    required
                  />
                  </div>
                  <div>
                    <input
                      type="text"
                      placeholder="직책 또는 직무"
                      onChange={(e) => handleCareerChange(index, "careerJobTitle", e.target.value)}
                      required
                    />
                  </div>
                </div>
                ))}
              {/* 추가 버튼을 필드 목록 외부로 이동 */}
              <div className="career-add-button">
                <button type="button" className="add-career-button" onClick={addCareerField}>
                  + 추가
                </button>
              </div>
          </div>
        </div>
        {/* 경력 END*/}
         

        {/* 프로필 사진 첨부 */}
        <div className="form-group-profile horizontal">
          <label htmlFor="profileImage">
            프로필 사진<span className="red-text">*</span>
          </label>
          <input
            type="file"
            className="form-group-profileImage"
            id="profileImage"
            name="profileImage"
            ref={fileInputRef}
            onChange={handleImageChange}
            accept="image/*"
            required
          />
          {imagePreview && (
            <div className="image-preview">
              <img src={imagePreview} alt="Preview" />
            </div>
          )}
        </div>
        {/* 제출 버튼 */}
        <div className="mentor-submit-container">
          <button
            type="button"
            onClick={mentorProfileCreateAction}
            className="submit-button"
          >
            신청하기
          </button>
          <button
            type="button"
            onClick={skip}
            className="skip-button"
          >
            건너뛰기
          </button>
        </div>
      </form>
    </div>

  );
};

export default MentorJoinForm;
