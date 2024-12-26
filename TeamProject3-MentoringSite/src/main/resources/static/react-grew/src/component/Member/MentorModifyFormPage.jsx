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
  /***** Context 가져오기 END *****/

  /* 네비게이트 */
  const navigate = useNavigate();

  /**** 멘토토 선언 START ****/
  const [mentor, setMentor] = useState({
    categoryNo: "", //전문분야 번호
    mentorIntroduce: "",  //소개글
    mentorCareer: "", //경력
    mentorImage: "",  //프로필 이미지지
  });
  /**** 멘토 선언 END ****/

  /**** 카테고리 선언 START ****/
    const [categories, setCategories] = useState([]);
    // selectedParent / selectedChild
    const [selectedParent, setSelectedParent] = useState("");
    const [selectedChild, setSelectedChild] = useState("");
    /**** 카테고리 선언 END ****/

  /********** 카테고리 불러오기 START ***********/
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
    /********** 카테고리 불러오기 END ***********/

  /********** 경력 관련 메소드 START ***********/
  /*
    경력 필드 선언, 사용자가 입력하는 경력을 관리하며, 다중 입력 필드를 추가/제거하는 데 사용
    초기값: 빈 문자열 배열 [""]로 시작
    각 요소는 경력 정보의 한 줄(입력 필드 값)을 나타낸다.
    setCareerFields : 입력 필드 추가, 변경 등의 작업에 사용.
  */
  const [careerFields, setCareerFields] = useState([""]);

  /*
    + 추가 버튼 클릭 시 careerFields 배열에 빈 문자열을 추가
    현재의 careerFields 배열을 펼치고, 새로운 빈 문자열 ""을 추가
    초기 상태: careerFields = [""]
    + 추가 버튼 클릭 후: careerFields = ["", ""]
  */
  const addCareerField = () => {
    setCareerFields([...careerFields, ""]);
  };

  /* 
    특정 경력 필드의 값이 변경되면 careerFields 배열을 업데이트
    모든 경력을 줄바꿈(\n)으로 연결해 mentor.mentorCareer에 저장 
    1. careerFields 배열의 복사본(updatedFields)을 생성
    2. 변경된 값(value)을 해당 index 위치에 업데이트
    3. setCareerFields(updatedFields): 변경된 배열로 상태를 업데이트하여 UI를 리렌더링.
    4. setMentor: 모든 경력을 줄바꿈(\n)으로 연결해 mentor.mentorCareer 필드에 저장
   */
  const handleCareerChange = (index, value) => {
    const updatedFields = [...careerFields];
    updatedFields[index] = value;
    setCareerFields(updatedFields);

    /* 줄바꿈으로 합친 값을 mentor 객체에 저장 */
    setMentor((prev) => ({
      ...prev,
      /* 배열로 관리 중인 careerFields(각 경력을 담고 있는 상태)를 줄바꿈(\n)으로 연결하여 하나의 문자열로 변환 */
      mentorCareer: updatedFields.join("\n"),
    }));
  };

  /* 
    예시)
    기존 careerFields: ["경력 1", "경력 2"]
    1번 인덱스에서 "경력 2"를 "새로운 경력 2"로 수정
    ==>
    updatedFields = ["경력 1", "새로운 경력 2"]
    mentor.mentorCareer = "경력 1\n새로운 경력 2"
   */
  /********** 경력 관련 메소드 END ***********/

  /***** 입력 업데이트 핸들러 START *****/
  const handleChangeMentorEditForm = (e) => {
    setMentor((prevMentor) => ({
      ...prevMentor,// 이전 상태를 펼치고
      [e.target.name]: e.target.value,// 업데이트하려는 필드만 덮어씌움
    }));
  };
  /***** 입력 업데이트 핸들러 END *****/

  /* mentorProfileNo가 변경될 때 실행 */
  useEffect(() => {
    /* 멘토 정보 가져오기 */
    const fetchMentorInfo = async () => {
      try {
        /* 멘토 프로필 번호로 멘토 프로필 가져와 반환 받음 */
        const response = await memberApi.getMentorProfile(mentorProfileNo);
        /* 데이터가 있으면 */
        if (response?.data) {
          /* categoryNo, mentorIntroduce, mentorCareer에 데이터 대입 */
          const { categoryNo, mentorIntroduce, mentorCareer } = response.data;
          /* 멤버 세팅 */
          setMentor({
            categoryNo: categoryNo || "",
            mentorIntroduce: mentorIntroduce || "",
            mentorCareer: mentorCareer || "",
            mentorImage: "",
          });

          /* mentorCareer 정보를 처리하여 careerFields 상태에 초기값을 설정 */
          /* 줄바꿈(\n)을 기준으로 나누어 배열로 변환 */
          const careerArray = mentorCareer ? mentorCareer.split("\n") : [""];
          setCareerFields(careerArray);

        // 카테고리 정보가 로드된 후 초기화
        if (categoryNo && categories.length > 0) {
          initializeCategorySelection(categoryNo);
        }
        }
      } catch (error) {
        console.error("멘토 정보 로드 실패:", error);
      }
    };

  // 초기화 함수: 카테고리 설정
  const initializeCategorySelection = (categoryNo) => {
    // 대분류를 찾는다
    const parentCategory = categories.find((cat) =>
      cat.childCategories.some(
        (child) => String(child.categoryNo) === String(categoryNo)
      )
    );

    if (parentCategory) {
      setSelectedParent(parentCategory.categoryNo); // 대분류 설정
      setSelectedChild(categoryNo); // 소분류 설정
    }
  };


    //멘토 정보 가져오기 실행
    fetchMentorInfo();
  }, [mentorProfileNo, categories]);
  



  const mentorProfileUpdateAction = async () => {
    const responseJsonObject = await memberApi.mentorProfileUpdateAction(
      mentorProfileNo,
      mentor
    );
    switch (responseJsonObject.status) {
      case responseStatus.UPDATE_MENTOR_PROFILE_SUCCESS_CODE:
        alert("멘토 정보 수정 성공");
        navigate("/main");
        break;

      default:
        alert("수정 실패");
        break;
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
                  placeholder={index >= careerFields.length - 1 && career.trim() === "" ? "경력 입력" : ""}
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
