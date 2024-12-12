import React, { useState } from "react";
import "../css/JoinFormPage.css";
import { useNavigate } from "react-router-dom";
import * as memberApi from "../api/memberApi";
import * as responseStatus from "../api/responseStatusCode";


export const JoinFormPage = () => {
  const navigate = useNavigate();

  const [member, setMember] = useState({
    memberId: "",
    memberPassword: "",
    memberEmail: "",
    memberName: "",
    interests: [] /* interest는 여러개라 배열로 받음 */
  });

  //발송한 임시 번호
  const [tempCode, setTempCode] = useState({
    tempCode:""
  });

  //회원가입에 사용할 핸들체인지
  const handleChangeJoinForm = (e) => {
    setMember({
      ...member,
      [e.target.name]:e.target.value
    });
    console.log("member-name : ", e.target.name);
    console.log("member-value : ", e.target.value);
  };

  //체크박스에 사용할 핸들체인지
  const handleChangeCheckBox = (e) => {
    //체크박스의 값(Value), 체크박스의 체크상태(checked=>true, false)
    const { value, checked } = e.target;

    console.log("e.target.value : ", e.target.value);
    
    //상태 업데이트(prevState : 상태변경 이전의 Member) => 기존 상태를 복사 후 interests 속성 업데이트
    setMember((prevState) => {
      //체크 상태에 따라 updatedInterests를 계산
      const updatedInterests = checked
          /* 체크박스가 선택된 경우, interests 배열을 그대로 유지하면서 새 항목 { categoryNo: parseInt(value, 10) }을 추가 parseInt(value, 10) : 체크박스 값(value)을 숫자로 변환 */
        ? [...prevState.interests, { categoryNo: parseInt(value, 10) }]
        
          /* 체크박스가 선택되지 않은 경우 기존의 interests 배열에서 categoryNo가 value와 일치하지 않는 항목만 남긴다. 즉, 체크 해제된 항목은 배열에서 제거 */
        : prevState.interests.filter(
            (interest) => interest.categoryNo !== parseInt(value, 10) // categoryNo로 필터링
          );

      /* 기존 member 객체를 복사(...prevState)한 뒤, interests 속성만 updatedInterests로 업데이트 */
      return {
        ...prevState,
        interests: updatedInterests,
      };
    });
  };
  
  //인증번호 발송
  const sendJoinCode = async () => {

    console.log("member : ", member);
    console.log("member : ", member.memberEmail);

    const response = await memberApi.sendJoinCode(member.memberEmail);
    setTempCode(response.data);
    console.log("Response from sendJoinCode:", response);
  }


  //회원가입 액션
  const MemberJoinAction = async () => {
    
    console.log("member : ", member);
    console.log("tempCode : ", tempCode.data);
    
    if(member.memberId === "") {
      alert("아이디를 입력하세요.")
    }

    const responseJsonObject = await memberApi.joinAction(member, tempCode);

    switch (responseJsonObject.status) {
      case responseStatus.CREATED_USER:
        navigate('/main');
        break;
      default:
        console.log("가입실패 : " , responseJsonObject);
        alert("가입 실패");
    }
  };


  /* 디자인 부분 */
  return (
    <div className="signup-container">
      <h2 className="signup-title">회원가입</h2>

      <form className="signup-form">
        <div className="form-join-group">
          <input
            type="text"
            id="memberId"
            name="memberId"
            placeholder="아이디를 입력하세요"
            onChange={handleChangeJoinForm}
            required
          />
        </div>

        <div className="form-join-group">
          <input
            type="password"
            id="password"
            name="memberPassword"
            placeholder="비밀번호를 입력하세요"
            onChange={handleChangeJoinForm}
            required
          />
        </div>

        <div className="form-join-group">
          <input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            placeholder="비밀번호를 다시 입력하세요"
            onChange={handleChangeJoinForm}
            required
          />
        </div>

        <div className="form-join-group">
          <input
            type="text"
            id="name"
            name="memberName"
            placeholder="이름을 입력하세요"
            onChange={handleChangeJoinForm}
            required
          />
        </div>

        <div className="form-join-group">
          <input
            type="email"
            id="email"
            name="memberEmail"
            placeholder="이메일을 입력하세요"
            onChange={handleChangeJoinForm}
            required
          />
           <input
            type="button"
            id="btn_send_join_code_action"
            value="인증번호 발송"
            onClick={sendJoinCode}
            className="member-join-button"
          />
        </div>

        <div className="form-join-group">
          <input
            type="text"
            id="emailCode"
            name="emailCode"
            placeholder="인증번호를 입력하세요"
            onChange={(e) => setTempCode(e.target.value)}
            required
          />
        </div>

        <div className="form-interest-group">
          <label>관심사</label>

          <fieldset>
            <legend>직무상담</legend>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={2}
                onChange={handleChangeCheckBox}
              />
              인사/총무/노무
            </label>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={3}
                onChange={handleChangeCheckBox}
              />
              영업/영업관리
            </label>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={4}
                onChange={handleChangeCheckBox}
              />
              IT개발/데이터
            </label>
          </fieldset>

          <fieldset>
            <legend>학습/교육</legend>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={6}
                onChange={handleChangeCheckBox}
              />
              중학생
            </label>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={7}
                onChange={handleChangeCheckBox}
              />
              고등학생
            </label>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={8}
                onChange={handleChangeCheckBox}
              />
              대학입시상담
            </label>
          </fieldset>

          <fieldset>
            <legend>예술/창작</legend>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={10}
                onChange={handleChangeCheckBox}
              />
              음악
            </label>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={11}
                onChange={handleChangeCheckBox}
              />
              글쓰기
            </label>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={12}
                onChange={handleChangeCheckBox}
              />
              미술
            </label>

          </fieldset>
          <fieldset>
            <legend>창업/비즈니스</legend>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={16}
                onChange={handleChangeCheckBox}
              />
              스타트업 아이디어
            </label>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={17}
                onChange={handleChangeCheckBox}
              />
              마케팅 전략
            </label>
            <label>
              <input
                type="checkbox"
                name="interests"
                value={18}
                onChange={handleChangeCheckBox}
              />
              법률 특허 상담
            </label>
          </fieldset>
        </div>

          <input
            type="button"
            id="btn_member_join_action"
            value="회원 가입"
            onClick={MemberJoinAction}
            className="member-join-button"
          />
          
      </form>
    </div>
  );
}

export default JoinFormPage;
