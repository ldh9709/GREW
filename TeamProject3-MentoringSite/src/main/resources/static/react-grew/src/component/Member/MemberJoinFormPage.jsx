import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import * as memberApi from "../../api/memberApi";
import * as responseStatus from "../../api/responseStatusCode";
import "../../css/memberPage.css"

export const MemberJoinFormPage = () => {
  const navigate = useNavigate();

  const [member, setMember] = useState({
    memberId: "",
    memberPassword: "",
    memberEmail: "",
    memberName: "",
    interests: [],
  });

  const [tempCode, setTempCode] = useState("");

  // 입력 필드 업데이트 핸들러
  const handleChangeJoinForm = (e) => {
    setMember({ ...member, [e.target.name]: e.target.value });
  };

  // 체크박스 변경 핸들러
  const handleChangeCheckBox = (e) => {
    const { value, checked } = e.target;
    setMember((prevState) => {
      const updatedInterests = checked
        ? [...prevState.interests, { categoryNo: parseInt(value, 10) }]
        : prevState.interests.filter(
            (interest) => interest.categoryNo !== parseInt(value, 10)
          );
      return { ...prevState, interests: updatedInterests };
    });
  };

  // 인증번호 발송
  const sendJoinCode = async () => {
    const response = await memberApi.sendJoinCode(member.memberEmail);
    setTempCode(response.data);
  };

  // 회원가입 액션
  const MemberJoinAction = async () => {
    if (!member.memberId) {
      alert("아이디를 입력하세요.");
      return;
    }

    const responseJsonObject = await memberApi.joinAction(member, tempCode);

    switch (responseJsonObject.status) {
      case responseStatus.CREATED_MEMBER_SUCCESS:
        navigate("/main");
        break;
      case responseStatus.CREATED_MEMBER_FAIL:
        alert("가입 실패");
        break;
      default:
        alert("에러 발생");
        break;
    }
  };

  return (
    <div className="member-signup-container">
    <h2 className="member-signup-title">회원가입</h2>
    <form className="member-signup-form">
        <div className="member-form-join-group">
            <input
                type="text"
                name="memberId"
                placeholder="아이디를 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
        </div>
        <div className="member-form-join-group">
            <input
                type="password"
                name="memberPassword"
                placeholder="비밀번호를 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
        </div>
        <div className="member-form-join-group">
            <input
                type="text"
                name="memberName"
                placeholder="이름을 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
        </div>
        <div className="member-form-join-group">
            <input
                type="email"
                name="memberEmail"
                placeholder="이메일을 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
            <input
                type="button"
                value="인증번호 발송"
                onClick={sendJoinCode}
                className="member-join-button"
            />
        </div>
        <div className="member-form-join-group">
            <input
                type="text"
                placeholder="인증번호를 입력하세요"
                onChange={(e) => setTempCode(e.target.value)}
                required
            />
        </div>
        <div className="member-form-interest-group">
            <label>관심사</label>
            <fieldset>
                <legend>직무상담</legend>
                <label>
                    <input
                        type="checkbox"
                        value={2}
                        onChange={handleChangeCheckBox}
                    />
                    인사/총무/노무
                </label>
                <label>
                    <input
                        type="checkbox"
                        value={3}
                        onChange={handleChangeCheckBox}
                    />
                    영업/영업관리
                </label>
                <label>
                    <input
                        type="checkbox"
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
                        value={6}
                        onChange={handleChangeCheckBox}
                    />
                    중학생
                </label>
                <label>
                    <input
                        type="checkbox"
                        value={7}
                        onChange={handleChangeCheckBox}
                    />
                    고등학생
                </label>
                <label>
                    <input
                        type="checkbox"
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
                        value={10}
                        onChange={handleChangeCheckBox}
                    />
                    음악
                </label>
                <label>
                    <input
                        type="checkbox"
                        value={11}
                        onChange={handleChangeCheckBox}
                    />
                    글쓰기
                </label>
                <label>
                    <input
                        type="checkbox"
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
                        value={16}
                        onChange={handleChangeCheckBox}
                    />
                    스타트업 아이디어
                </label>
                <label>
                    <input
                        type="checkbox"
                        value={17}
                        onChange={handleChangeCheckBox}
                    />
                    마케팅 전략
                </label>
                <label>
                    <input
                        type="checkbox"
                        value={18}
                        onChange={handleChangeCheckBox}
                    />
                    법률 특허 상담
                </label>
            </fieldset>
        </div>
        <input
            type="button"
            value="회원가입"
            onClick={MemberJoinAction}
            className="member-signup-button"
        />
    </form>
</div>


  );
};

export default MemberJoinFormPage;
