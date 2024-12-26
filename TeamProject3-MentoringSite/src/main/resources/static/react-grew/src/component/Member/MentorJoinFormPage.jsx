import React, { useEffect, useState } from "react";
import "../../css/mentor.css";
import * as responseStatus from "../../api/responseStatusCode";
import * as memberApi from "../../api/memberApi";
import * as categoryApi from "../../api/categoryApi";
import { useNavigate } from "react-router-dom";
import { useMemberAuth } from "../../util/AuthContext";

const MentorJoinForm = () => {
  /***** Context 가져오기 START *****/
  const auth = useMemberAuth(); // 사용자 인증 정보를 가져온다
  const token = auth?.token || null; // 사용자 인증 토큰
  const member = auth?.member || {}; // 사용자 관련 정보 객체
  const mentorProfileNo = token ? member.mentorProfileNo : null; // 사용자 멘토 프로필 번호
  /***** Context 가져오기 END *****/

  /* 네비게이트 */
  const navigate = useNavigate();

  /**** 멘토 선언 START ****/
  const [mentor, setMentor] = useState({
    categoryNo: "", // 전문분야 번호(소분류 최종 선택값)
    mentorIntroduce: "",
    mentorCareer: "",
    mentorImage: "",
  });
  /**** 멘토 선언 END ****/

  /**** 카테고리 선언 START ****/
  const [categories, setCategories] = useState([]);
  // selectedParent / selectedChild
  const [selectedParent, setSelectedParent] = useState("");
  const [selectedChild, setSelectedChild] = useState("");
  /**** 카테고리 선언 END ****/

  /********** 경력 관련 메소드 START ***********/
  const [careerFields, setCareerFields] = useState([""]);

  const addCareerField = () => {
    setCareerFields((prev) => [...prev, ""]);
  };

  const handleCareerChange = (index, value) => {
    const updatedFields = [...careerFields];
    updatedFields[index] = value;
    setCareerFields(updatedFields);

    // 줄바꿈으로 합친 값을 mentor 객체에 저장
    setMentor((prev) => ({
      ...prev,
      mentorCareer: updatedFields.join("\n"),
    }));
  };
  /********** 경력 관련 메소드 END ***********/

  /********** 카테고리 트리 구조 불러오기 START ***********/
  useEffect(() => {
    async function getCategories() {
      // 서버에서 이미 대분류 객체의 childCategories에 소분류가 들어있는 구조를 내려준다고 가정
      const res = await categoryApi.ListCategory();
      console.log("category res : ", res);
      const data = await res.data;
      console.log("category data : ", data);
      // 여기서는 별도의 필터를 안 합니다. 
      // data 예: [
      //   { categoryNo:1, categoryName:'직무 상담', categoryDepth:1, childCategories:[
      //       { categoryNo:2, categoryName:'인사/총무/노무', categoryDepth:2, childCategories:[] },
      //       ...
      //   ]},
      //   { categoryNo:5, categoryName:'학습/교육', categoryDepth:1, childCategories:[...], ...},
      //   ...
      // ]
      setCategories(data);
    }
    getCategories();
  }, []);

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
    let responseJsonObject;
    // mentorProfileNo === 0 이면 새로 생성, 아니면 업데이트
    if (mentorProfileNo === 0) {
      responseJsonObject = await memberApi.mentorProfileCreateAction(
        member.memberNo,
        mentor
      );
    } else {
      responseJsonObject = await memberApi.mentorProfileUpdateAction(
        mentorProfileNo,
        mentor
      );
    }

    switch (responseJsonObject.status) {
      case responseStatus.CREATED_MENTOR_PROFILE_SUCCESS_CODE:
      case responseStatus.UPDATE_MENTOR_PROFILE_SUCCESS_CODE:
        alert("멘토 가입 성공");
        navigate("/member/profile");
        break;
      default:
        alert("가입 실패");
        break;
    }
  };
  /***** 멘토 생성 버튼 END *****/

  return (
    <div className="mentor-join-container">
      <h1 className="form-title">회원가입</h1>
      <h3 className="form-subtitle">멘토 가입을 환영합니다!</h3>

      {/* 대/소분류 선택 폼 */}
      <div className="form-group-profile horizontal-combined">
        {/* 대분류 선택 */}
        <label className="parent-label">
          전문분야<span className="red-text">필수</span>
        </label>
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

      {/* 멘토 가입 폼 */}
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
            onChange={handleChangeMentorJoinForm}
            required
          ></textarea>
        </div>

        {/* 경력 */}
        <div className="form-group-profile horizontal">
          <label htmlFor="mentorCareer">
            경력<span className="red-text">필수</span>
          </label>
          <div className="textarea-container">
            {careerFields.map((career, index) => (
              <div key={index} className="textarea-row">
                <textarea
                  value={career}
                  placeholder="경력 입력"
                  rows="1"
                  onChange={(e) => handleCareerChange(index, e.target.value)}
                  required
                ></textarea>
                <div className="button-container">
                  {index === careerFields.length - 1 ? (
                    <button
                      type="button"
                      onClick={addCareerField}
                      className="add-career-button"
                    >
                      + 추가
                    </button>
                  ) : null}
                </div>
              </div>
            ))}
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
            required
          />
        </div>

        {/* 제출 버튼 */}
        <button
          type="button"
          onClick={mentorProfileCreateAction}
          className="submit-button"
        >
          회원가입
        </button>
      </form>
    </div>

  );
};

export default MentorJoinForm;
