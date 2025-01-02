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

  const [mentorImage, setMentorImage] = useState(null); // 이미지 파일

  const navigate = useNavigate();
  /***** Context 가져오기 END *****/

  /* 네비게이트 */

  /**** 멘토토 선언 START ****/
  const [mentor, setMentor] = useState({
    categoryNo: "",
    mentorStatus: "",
    mentorIntroduce: "",
    mentorHeadline: "",
    careerDtos: [{ careerCompanyName: "", careerJobTitle: "", careerStartDate: "", careerEndDate: "", mentorProfileNo: mentorProfileNo}],
    mentorImage: "",
  });
  /**** 멘토 선언 END ****/

  /**** 카테고리 선언 START ****/
  const [categories, setCategories] = useState([]);
  const [selectedParent, setSelectedParent] = useState("");
  const [selectedChild, setSelectedChild] = useState("");

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

  const handleImageChange = (e) => {
    setMentorImage(e.target.files[0]);
    setMentor((prevMentor) => ({
      ...prevMentor,
      [e.target.name]: `/upload/mentor-profile/${mentorProfileNo}/${e.target.files[0].name}`,
    }));
  }

  const handleFocus = (e) => {
    e.target.type = "date"; // 포커스되면 type을 date로 변경
  };

  const handleBlur = (e) => {
    if (!e.target.value) {
      e.target.type = "text"; // 값이 없으면 type을 다시 text로 변경
    }
  };

  const fetchMentorInfo = async () => {
    if (!mentorProfileNo || categories.length === 0) return;

    try {
      const res = await memberApi.getMentorProfile(mentorProfileNo);
      if (res.data) {
        const { categoryNo, mentorIntroduce, mentorHeadline, careerDtos, mentorStatus, mentorImage} = res.data;
        setMentor({ categoryNo, mentorIntroduce, mentorHeadline, careerDtos: careerDtos || [], mentorStatus: mentorStatus, mentorImage: mentorImage});
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
  
  const uploadImage= async () => {
      const response = await memberApi.uploadMentorProfileImage(mentorProfileNo, mentorImage);
      console.log("이미지 업로드 response : ", response);
  
  }


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
      if (mentorImage) {
        await uploadImage(); // 생성된 번호로 이미지 업로드
      } else {
        alert("이미지를 선택하지 않아 기존의 이미지가 적용되었습니다.");
      }
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
    <div className="mentor-edit-container">
      <h1 className="mentor-edit-title">멘토 수정</h1>
      <div className="mentor-edit-form-group">
        <label className="mentor-edit-label">
          전문분야<span className="mentor-edit-required">*</span>
        </label>
        <div className="mentor-edit-select-wrapper">
          <select
            className="mentor-edit-select"
            id="parentCategory"
            name="parentCategory"
            value={selectedParent}
            onChange={handleParentChange}
          >
            <option value="">-- 대분류 선택 --</option>
            {categories
              .filter((cat) => cat.categoryDepth === 1)
              .map((parent) => (
                <option key={parent.categoryNo} value={parent.categoryNo}>
                  {parent.categoryName}
                </option>
              ))}
          </select>
          <select
            className="mentor-edit-select"
            id="childCategory"
            name="childCategory"
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
      </div>
      <form className="mentor-edit-form">
        <div className="mentor-edit-form-group">
          <label className="mentor-edit-label">
            본인 소개<span className="mentor-edit-required">*</span>
          </label>
          <textarea
            className="mentor-edit-textarea"
            id="mentorIntroduce"
            name="mentorIntroduce"
            placeholder="소개글 입력"
            value={mentor.mentorIntroduce}
            onChange={handleChangeMentorEditForm}
            required
          ></textarea>
        </div>
        <div className="mentor-edit-form-group">
          <label className="mentor-edit-label">
            한줄 소개<span className="mentor-edit-required">*</span>
          </label>
          <textarea
            className="mentor-edit-textarea"
            id="mentorHeadline"
            name="mentorHeadline"
            placeholder="한 줄 소개 입력"
            value={mentor.mentorHeadline}
            onChange={handleChangeMentorEditForm}
            required
          ></textarea>
        </div>
        <div className="mentor-edit-form-group">
          <label className="mentor-edit-label">
            경력<span className="mentor-edit-required">*</span>
          </label>
          <div className="mentor-edit-career-container">
            {mentor.careerDtos.map((career, index) => (
              <div key={index} className="mentor-edit-career-row">
                <input
                  type="text"
                  className="mentor-edit-input"
                  placeholder="회사명"
                  value={career.careerCompanyName}
                  onChange={(e) =>
                    handleCareerChange(index, "careerCompanyName", e.target.value)
                  }
                  required
                />
                <input
                  type="text"
                  className="mentor-edit-input"
                  placeholder="직책"
                  value={career.careerJobTitle}
                  onChange={(e) =>
                    handleCareerChange(index, "careerJobTitle", e.target.value)
                  }
                  required
                />
                <input
                  type="text"
                  name="startDate"
                  className="mentor-edit-input"
                  onFocus={handleFocus}
                  onBlur={handleBlur}
                  value={career.careerStartDate}
                  onChange={(e) =>
                    handleCareerChange(index, "careerStartDate", e.target.value)
                  }
                  placeholder="입사년월"
                  required
                />
                <input
                  type="text"
                  name="endDate"
                  className="mentor-edit-input"
                  onFocus={handleFocus}
                  onBlur={handleBlur}
                  value={career.careerEndDate}
                  onChange={(e) =>
                    handleCareerChange(index, "careerEndDate", e.target.value)
                  }
                  placeholder="퇴사년월"
                  required
                />
              </div>
            ))}
            <button
              type="button"
              className="mentor-edit-add-button"
              onClick={addCareerField}
            >
              + 추가
            </button>
          </div>
        </div>
        <div className="mentor-edit-form-group">
          <label className="mentor-edit-label">
            프로필 사진<span className="mentor-edit-required">*</span>
          </label>
          <input
            type="file"
            className="mentor-edit-input"
            id="profileImage"
            name="mentorImage"
            accept="image/*"
            onChange={handleImageChange}
          />
        </div>
        <button
          type="button"
          className="mentor-edit-submit-button"
          onClick={mentorProfileUpdateAction}
        >
          수정하기
        </button>
      </form>
    </div>
  );
};

export default MentorEditForm;
