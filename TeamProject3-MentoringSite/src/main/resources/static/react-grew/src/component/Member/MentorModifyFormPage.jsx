import React, { useState, useEffect } from "react";
import "../../css/mentor.css";
import * as responseStatus from "../../api/responseStatusCode";
import * as memberApi from "../../api/memberApi";
import * as categoryApi from "../../api/categoryApi";
import { useNavigate } from "react-router-dom";
import { useMemberAuth } from "../../util/AuthContext";

const MentorEditForm = () => {

  /***** Context 가져오기 START *****/
  const auth = useMemberAuth(); //사용자 인증 정보를 가져온다
  const token = auth?.token || null;  //사용자 인증 토큰
  const member = auth?.member || {};  //사용자 관련 정보 객체
  const mentorProfileNo = token ? member.mentorProfileNo : null;  //사용자 멘토 프로필 번호

  const navigate = useNavigate();
  /***** Context 가져오기 END *****/

  /* 네비게이트 */

  /**** 멘토토 선언 START ****/
  const [mentor, setMentor] = useState({
    categoryNo: "",
    mentorIntroduce: "",
    mentorHeadline: "",
    careerDtos: [{ companyName: "", jobTitle: "", startDate: "", endDate: "" }],
    mentorImage: "",
  });
  /**** 멘토 선언 END ****/

  /**** 카테고리 선언 START ****/
  const [categories, setCategories] = useState([]);
  const [selectedParent, setSelectedParent] = useState("");
  const [selectedChild, setSelectedChild] = useState("");

  const addCareerField = () => {
    console.log(mentor);
    setMentor((prevMentor) => ({
      ...prevMentor,
      careerDtos: [
        ...prevMentor.careerDtos,
        { companyName: "", jobTitle: "", startDate: "", endDate: "" },
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


  /********** 카테고리 불러오기 START ***********/
  const fetchCategories = async () => {
    try {
      const res = await categoryApi.ListCategory();
      setCategories(res.data);
    } catch (err) {
      console.error("카테고리 로드 실패:", err);
    }
  };
  
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
  /********** 카테고리 불러오기 END ***********/

  /***** 입력 업데이트 핸들러 START *****/
  const handleChangeMentorEditForm = (e) => {
    setMentor((prevMentor) => ({
      ...prevMentor,// 이전 상태를 펼치고
      [e.target.name]: e.target.value,// 업데이트하려는 필드만 덮어씌움
    }));
  };
  /***** 입력 업데이트 핸들러 END *****/

  const handleChangeMentorJoinForm = (e) => {
    setMentor((prevMentor) => ({
      ...prevMentor,
      [e.target.name]: e.target.value,
    }));
  };

  const fetchMentorInfo = async () => {
    if (!mentorProfileNo || categories.length === 0) return;

    try {
      const res = await memberApi.getMentorProfile(mentorProfileNo);
      if (res.data) {
        const { categoryNo, mentorIntroduce, mentorHeadline, careerDtos} = res.data;
        setMentor({ categoryNo, mentorIntroduce, mentorHeadline, careerDtos: careerDtos || []});
        initializeCategorySelection(categoryNo);
      }
    } catch (err) {
      console.error("멘토 정보 로드 실패:", err);
    }
  };

  // 초기화 함수: 카테고리 설정
  const initializeCategorySelection = (categoryNo) => {
    const parentCategory = categories.find((cat) =>
      cat.childCategories.some((child) => String(child.categoryNo) === String(categoryNo))
    );
    if (parentCategory) {
      setSelectedParent(parentCategory.categoryNo);
      setSelectedChild(categoryNo);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  useEffect(() => {
    fetchMentorInfo();
  }, [categories, mentorProfileNo]);
  



  const mentorProfileUpdateAction = async () => {
    try {
      mentor.careerDtos.every((career) => {
        if (career.startDate && career.endDate) {
          const startDate = new Date(career.startDate);
          const endDate = new Date(career.endDate);
          if (endDate <= startDate) {
            alert(
              `시작일(${career.startDate})은 종료일(${career.endDate})보다 이전이어야 합니다. 날짜를 수정해주세요.`
            );
            return false; // 유효성 검사 실패
          }
        }
        return true; // 유효성 검사 통과
      });
      const response = await memberApi.mentorProfileUpdateAction(mentorProfileNo, mentor);
      if (response.status === responseStatus.UPDATE_MENTOR_PROFILE_SUCCESS_CODE) {
        alert("멘토 정보 수정 성공");
        navigate("/main");
      } else {
        alert("수정 실패");
      }
    } catch (err) {
      console.error("수정 실패:", err);
      alert("수정 중 오류 발생");
    }
  };
    return (
      <div className="mentor-join-container">
        <h1 className="form-title">멘토 수정</h1>
        {/* 대/소분류 선택 폼 */}
        <div className="form-group-profile horizontal-combined">
          {/* 대분류 선택 */}
          <label className="parent-label">전문분야<span className="red-text">필수</span></label>
          <select className="category-select" value={selectedParent} onChange={handleParentChange}>
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
          <label className="child-label"></label>
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
  
  
        <form className="mentor-join-form">
          {/* 소개글 */}
          <div className="form-group-profile horizontal">
            <label htmlFor="mentorIntroduce">
              본인 소개<span className="red-text">필수</span>
            </label>
            <textarea
              id="mentorIntroduce"
              name="mentorIntroduce"
              placeholder="소개글 입력"
              rows="5"
              value={mentor.mentorIntroduce}
              onChange={handleChangeMentorEditForm}
              required
            ></textarea>
          </div>

          {/* 한 줄 소개글 */}
          <div className="form-group-profile horizontal">
            <label htmlFor="mentorIntroduce">
              한줄 소개<span className="red-text">필수</span>
            </label>
            <textarea
              id="mentorHeadline"
              name="mentorHeadline"
              placeholder="한 줄 소개 입력"
              rows="1"
              value={mentor.mentorHeadline}
              onChange={handleChangeMentorJoinForm}
              required
            ></textarea>
          </div>

          {/* 경력 */}
          <div className="form-group-profile horizontal">
            <label htmlFor="mentorCareer">
              경력<span className="red-text">필수</span>
            </label>
            <div className="career-container">
            {mentor.careerDtos.map((career, index) => (
              <div key={index} className="career-row">
                <input
                  type="text"
                  placeholder="회사명"
                  value={career.careerCompanyName}
                  onChange={(e) => handleCareerChange(index, "companyName", e.target.value)}
                  required
                />
                <input
                  type="text"
                  placeholder="직책"
                  value={career.careerJobTitle}
                  onChange={(e) => handleCareerChange(index, "jobTitle", e.target.value)}
                  required
                />
                <input
                  type="date"
                  value={career.careerStartDate}
                  onChange={(e) => handleCareerChange(index, "startDate", e.target.value)}
                  required
                />
                <input
                  type="date"
                  value={career.careerEndDate}
                  onChange={(e) => handleCareerChange(index, "endDate", e.target.value)}
                />
              </div>
                ))}
                {/* 추가 버튼을 필드 목록 외부로 이동 */}
                <button type="button" className="add-career-button" onClick={addCareerField}>
                  + 추가
                </button>
            </div>
          </div>
  
          {/* 프로필 사진 첨부 */}
          <div className="form-group-profile horizontal">
            <label htmlFor="profileImage">
              프로필 사진<span className="red-text">필수</span>
            </label>
            <input
              type="file"
              className="form-group-profileImage"
              id="profileImage"
              name="profileImage"
              accept="image/*"
            />
          </div>
  
          {/* 제출 버튼 */}
          <button
            type="button"
            onClick={mentorProfileUpdateAction}
            className="submit-button"
          >
            수정하기
          </button>
        </form>
      </div>
    );
};

export default MentorEditForm;
