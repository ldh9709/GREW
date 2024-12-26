import React, { useState } from "react";
import "../../css/mentor.css";
import * as responseStatus from "../../api/responseStatusCode";
import * as memberApi from "../../api/memberApi";
import { useNavigate } from "react-router-dom";
import { useMemberAuth } from "../../util/AuthContext";

const MentorJoinForm = () => {

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
  const handleChangeMentorJoinForm = (e) => {
    setMentor((prevMentor) => ({
      ...prevMentor, // 이전 상태를 펼치고
      [e.target.name]: e.target.value, // 업데이트하려는 필드만 덮어씌움
    }));
  };
  /***** 입력 업데이트 핸들러 END *****/

  /***** 멘토 생성 버튼 START *****/
  const mentorProfileCreateAction = async () => {
    //반환객체 미리 선언
    let responseJsonObject;
    
    //멘토 프로필 번호가 0(멘토 프로필이 없다는 뜻)이면 생성 액션
    if(mentorProfileNo === 0) {
      responseJsonObject = await memberApi.mentorProfileCreateAction(
        member.memberNo,
        mentor
      );
    //그게 아니라면 수정 액션
    } else {
      responseJsonObject = await memberApi.mentorProfileUpdateAction(
        mentorProfileNo,
        mentor
      );
    }
    
    switch (responseJsonObject.status) {
      case responseStatus.CREATED_MENTOR_PROFILE_SUCCESS_CODE:
        alert("멘토 가입 성공");
        navigate("/member/profile");
        break;
        
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
      <form className="mentor-join-form">
        {/* 분야 */}
        <div className="form-group-profile horizontal-field">
          <label htmlFor="categoryNo">
            전문 분야<span className="red-text">필수</span>
          </label>
          <select
            id="categoryNo"
            name="categoryNo"
            value={mentor.categoryNo}
            onChange={handleChangeMentorJoinForm}
            required
          >
            <option value="">-- 선택하세요 --</option>
            <option value="2">인사/총무/노무</option>
            <option value="3">영업/영업관리</option>
            <option value="4">IT개발/데이터</option>
            <option value="6">중학생 교육</option>
            <option value="7">고등학생 교육</option>
            <option value="8">대학입시 상담</option>
            <option value="10">음악</option>
            <option value="11">글쓰기</option>
            <option value="12">미술</option>
            <option value="13">사진/영상 제작</option>
            <option value="14">연기/연극</option>
            <option value="16">스타트업 아이디어</option>
            <option value="17">마케팅 전략</option>
            <option value="18">법률 특허 상담</option>
            <option value="22">피트니스</option>
            <option value="23">요가/필라테스</option>
            <option value="24">재활 운동</option>
            <option value="25">식단/영양 상담</option>
          </select>
        </div>

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

        {/********** 경력 **********/}
        <div className="form-group-profile horizontal">
        <label htmlFor="mentorCareer">
          경력<span className="red-text">필수</span>
        </label>
        <div className="textarea-container">
          {/* 
            [careerFields.map의 역할]
            careerFields 배열을 순회하며 각 요소에 대해 입력 필드(텍스트 영역)를 생성합니다.

            careerFields:
            이 배열은 사용자가 입력한 경력 정보의 목록을 저장합니다.
            각 요소는 개별 경력 정보를 나타냅니다.
            예시:
            careerFields = ["경력 1", "경력 2"]

            .map()
            배열의 각 요소(career)와 해당 인덱스(index)를 순회하며, JSX 요소를 생성합니다.
            반환 값: 각 요소에 대해 <div>를 생성.
           */}
          {careerFields.map((career, index) => (
            /* 
              React에서 리스트를 렌더링할 때, 각 자식 요소를 고유하게 식별하기 위해 사용.
              인덱스를 key로 설정하여 React가 효율적으로 변경 사항을 추적.
             */
            <div key={index} className="textarea-row"> 
              <textarea
                /* careerFields 배열의 현재 요소 값(career)을 텍스트 영역에 표시, 사용자가 입력하거나 수정한 값이 텍스트 영역에 반영. */
                value={career}
                /* 사용자가 아무것도 입력하지 않았을 때 표시되는 안내 문구. */
                placeholder={"경력 입력"}
                /* 텍스트 영역의 기본 높이 설정 (줄 수 기준) */
                rows="1"
                /* 해당 index의 값을 업데이트, careerFields와 mentor.mentorCareer를 동시에 변경 */
                onChange={(e) => handleCareerChange(index, e.target.value)}
                /* 필수 입력 필드로 지정, 값이 비어 있으면 폼 제출 시 유효성 검사가 발생 */
                required
              ></textarea>
              <div className="button-container">
                {/* + 추가 버튼은 배열의 마지막 입력 필드에만 표시 */}
                {index === careerFields.length - 1 ? (
                  <button
                    type="button"
                    /* 클릭 시 새로운 입력 필드를 추가 */
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
