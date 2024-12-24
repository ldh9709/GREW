import React, { useState } from "react";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import * as memberApi from "../../api/memberApi";
import * as responseStatus from "../../api/responseStatusCode";
import "../../css/memberPage.css"
import google from '../../image/google.png';
import naver from '../../image/naver.png';
import kakao from '../../image/kakao.png';
import dhlogo from '../../image/dhlogo.png';

export const MemberJoinFormPage = () => {
  const navigate = useNavigate();

  //뒤에 붙은 URL을 추적하기 위한 선언
  const [searchParams] = useSearchParams();
  //뒤에 붙은 역할을 가져옴
  const role = searchParams.get('role');
  //에러 상태메세지
  const [memberIdError, setMemberIdError] = useState(""); 
  const [memberPasswordError, setMemberPasswordError] = useState(""); 
  const [memberEmailError, setMemberEmailError] = useState("");
  const [memberInterestError, setMemberInterestError] = useState("");
  //멤버 데이터
  const [member, setMember] = useState({
    memberId: "",
    memberPassword: "",
    memberEmail: "",
    memberName: "",
    interests: [],
  });
  //이메일 발송 코드
  const [tempCode, setTempCode] = useState("");

  // 입력 필드 업데이트 핸들러
  const handleChangeJoinForm = (e) => {
    setMember({ ...member, [e.target.name]: e.target.value });

    /* 아이디 유효성 검사 */
    if (e.target.name === "memberId") {
      if (e.target.value.length < 3 || e.target.value.length > 15) {
        setMemberIdError("아이디는 3자 이상 15자 이하로 입력해주세요.");
      } else if (!/^[a-zA-Z0-9_-]+$/.test(e.target.value)) {
        setMemberIdError("아이디는 영문자, 숫자, '-', '_'만 허용됩니다.");
      } else {
        setMemberIdError(""); // 규칙을 만족하면 에러 초기화
      }
    }

    /* 패스워드 유효성 검사 */
    if (e.target.name === "memberPassword") {
      if (e.target.value.length < 8) {
        setMemberPasswordError("비밀번호는 최소 8자 이상이어야 합니다.");
      } else if (!(/[a-zA-Z]/.test(e.target.value) && // 알파벳 포함 여부
        (/\d/.test(e.target.value) || /[@$!%*?&]/.test(e.target.value)) // 숫자 또는 특수문자 포함 여부
      )) {
        setMemberPasswordError( "비밀번호는 대문자, 소문자, 숫자, 특수문자 중 두 가지 이상을 포함해야 합니다.");
      } else {
        setMemberPasswordError(""); // 규칙을 만족하면 에러 초기화
      }
    }

    /* 이메일 유효성 검사 */
    if (e.target.name === "memberEmail") {
      const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      if (!emailRegex.test(e.target.value)) {
        setMemberEmailError("유효하지 않은 이메일 형식입니다.");
      } else {
        setMemberEmailError(""); // 규칙을 만족하면 에러 초기화
      }
    }

    /* 이메일 유효성 검사 */
    if (e.target.name === "memberEmail") {
      const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      if (!emailRegex.test(e.target.value)) {
        setMemberEmailError("이메일 형식을 올바르게 입력해주세요.");
      } else {
        setMemberEmailError(""); // 규칙을 만족하면 에러 초기화
      }
    }

  };

  // 인증번호 발송
  const sendJoinCode = async () => {
    const response = await memberApi.sendJoinCode(member.memberEmail);
    setTempCode(response.data);
  };

  /* 관심사 선택 시 배열 업데이트 */
  const handleInterestClick = (value) => {
    /* 상태를 업데이트, 함수형 업데이트 방식을 사용하여 이전 상태(prevState)를 기반으로 새로운 상태를 계산 */
    setMember((prevState) => {
      /* prevState.interests: 기존에 선택된 관심사의 배열, includes(value) : value가 interests 배열에 포함되어 있는지 확인, 있으면 true, 없으면 false 반환 */
      const alreadySelected = prevState.interests.includes(value);

      const updatedInterests = alreadySelected
          /* alreadySelected가 true일 경우(이미 선택된 경우에 또 눌렀을 때), filter를 사용하여 value를 제외한 나머지 항목들로 새로운 배열을 생성(이미 생성됐을 시 제거)  */
        ? prevState.interests.filter((interest) => interest !== value)
        /* 스프레드 연산자(...)를 사용하여 기존 배열의 모든 항목을 복사한 뒤, value를 추가, 선택되지 않은 경우에 추가(기존에 없을 시 추가) */
        : [...prevState.interests, value]; 

      // 최소 3개 선택하지 않았을 경우 에러 메시지 설정
      if (updatedInterests.length < 3 || updatedInterests.length > 3) {
        setMemberInterestError("3개의 관심사를 선택해주세요.");
      } else {
        setMemberInterestError(""); // 규칙을 만족하면 에러 초기화
      }

    return { ...prevState, interests: updatedInterests };
  });
};

  

  // 회원가입 액션
  const MemberJoinAction = async () => {
    if (!member.memberId) {
      alert("아이디를 입력하세요.");
      return;
    }

    if(!member.memberPassword) {
      alert("비밀번호를 입력하세요.");
      return;
    }

    if(!member.memberName) {
      alert("이름을 입력하세요.");
      return;
    }

    if(!member.memberEmail) {
      alert("이메일 형식을 올바르게 입력해주세요.");
      return;
    }

    if(!member.interests) {
      alert("3개의 관심사를 선택하세요.");
      return;
    }

    console.log("입력한 멤버 객체 : ", member);

    if(role === 'mentor') {
      const responseJsonObject = await memberApi.mentorJoinAction(member, tempCode);
      console.log("멘토 회원가입 시 반환 객체 : ", responseJsonObject);
      switch (responseJsonObject.status) {
        case responseStatus.CREATED_MEMBER_SUCCESS:
          navigate('/member/login');
          break;
        case responseStatus.CREATED_MEMBER_FAIL:
          alert("가입 실패");
          break;
        default:
          alert("에러 발생");
          break;
      }

    } else if(role ==='mentee') {
      const responseJsonObject = await memberApi.menteeJoinAction(member, tempCode);
      console.log("멘티 회원가입 시 반환 객체 : ", responseJsonObject);
      switch (responseJsonObject.status) {
        case responseStatus.CREATED_MEMBER_SUCCESS:
          navigate('/member/login');
          break;
        case responseStatus.CREATED_MEMBER_FAIL:
          alert("가입 실패");
          break;
        default:
          alert("에러 발생");
          break;
      }
     
    }
    
  };

  return (
    <div className="member-signup-container">
      
      <img src={dhlogo} alt="logo" className="logo-icon"/>
      <h2 className="logo-title">멘토멘티 매칭 플랫폼 No.1!</h2>
      <h3 className="logo-title">그루에 오신 것을 환영합니다</h3>

      <h3 className="member-join-sub-title">SNS 회원가입</h3>
        <div className="member-sns-login-group">
            <Link to="http://localhost:8080/oauth2/authorization/google">
                <img src={google} alt="Google" className="member-sns-icon" />
            </Link>
            <Link to="http://localhost:8080/oauth2/authorization/naver">
                <img src={naver} alt="Naver" className="member-sns-icon" />
            </Link>
            <Link to="http://localhost:8080/oauth2/authorization/kakao">
                <img src={kakao} alt="Kakao" className="member-sns-icon" />
            </Link>
          </div>
    <h2 className="member-signup-title">회원가입</h2>
    <form className="member-signup-form">
        <div className="member-form-join-group">
        <p className="member-form-join-p">
        아이디<span className="red-star">*</span>
        </p>
            <input
                type="text"
                name="memberId"
                placeholder="아이디를 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
        <p className={`member-form-join-check ${memberIdError ? "visible" : ""}`}>
        {memberIdError}</p>
        </div>
        <div className="member-form-join-group">
        <p className="member-form-join-p">
        비밀번호<span className="red-star">*</span>
        </p>
            <input
                type="password"
                name="memberPassword"
                placeholder="비밀번호를 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
         <p className={`member-form-join-check ${memberPasswordError ? "visible" : ""}`}>
          {memberPasswordError}</p>    
        </div>
        <div className="member-form-join-group">
        <p className="member-form-join-p">
        이름<span className="red-star">*</span>
        </p>
            <input
                type="text"
                name="memberName"
                placeholder="이름을 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
        </div>
        <div className="member-form-join-group">
        <p className="member-form-join-p">
        이메일<span className="red-star">*</span>
        </p>
            <input
                type="email"
                name="memberEmail"
                placeholder="이메일을 입력하세요"
                onChange={handleChangeJoinForm}
                required
            />
         <p className={`member-form-join-check ${memberEmailError ? "visible" : ""}`}>
         {memberEmailError}</p>   
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
            <p>관심사<span className="red-star">*</span></p><p className={`member-form-join-check ${memberInterestError ? "visible" : ""}`}>
            {memberInterestError}</p>
        <div
        /* 선택된 항목에는 selected 클래스를 추가, 기본 클래스 "interest-item"과 선택 여부에 따라 "selected" 클래스가 동적으로 추가(CSS), ${} : 동적으로 값을 삽입하기 위해 사용 */
        className={`interest-item ${
          /* selectedInterests.includes("인사/총무/노무")가 true면 selected 클래스가 적용, member.interests.includes("2")의 결과에 따라 "selected"가 추가되거나 추가되지 않는다.*/
          member.interests.includes("2") ? "selected" : ""
        }`}
        /* 클릭 시 handleClick 함수가 호출되고, 해당 관심사가 선택되거나 해제 */
        onClick={() => handleInterestClick("2")}
      >
        인사/총무/노무
      </div>
      <div
        className={`interest-item ${member.interests.includes("3") ? "selected" : ""}`}
        onClick={() => handleInterestClick("3")}
      >
        영업/영업관리</div>
        
        <div
        className={`interest-item ${member.interests.includes("4") ? "selected" : ""}`}
        onClick={() => handleInterestClick("4")}
      >
        IT개발/데이터</div>

        <div
        className={`interest-item ${member.interests.includes("6") ? "selected" : ""}`}
        onClick={() => handleInterestClick("6")}
      >
        중학생 교육육</div>
        
        <div
        className={`interest-item ${member.interests.includes("7") ? "selected" : ""}`}
        onClick={() => handleInterestClick("7")}
      >
        고등학생 교육육</div>
        
        <div
        className={`interest-item ${member.interests.includes("8") ? "selected" : ""}`}
        onClick={() => handleInterestClick("8")}
      >
        대학입시상담</div>

        <div
        className={`interest-item ${member.interests.includes("10") ? "selected" : ""}`}
        onClick={() => handleInterestClick("10")}
      >
        음악</div>

        <div
        className={`interest-item ${member.interests.includes("11") ? "selected" : ""}`}
        onClick={() => handleInterestClick("11")}
      >
        글쓰기</div>

        <div
        className={`interest-item ${member.interests.includes("12") ? "selected" : ""}`}
        onClick={() => handleInterestClick("12")}
      >
        미술</div>

        <div
        className={`interest-item ${member.interests.includes("13") ? "selected" : ""}`}
        onClick={() => handleInterestClick("13")}
      >
        사진/영상 제작/</div>
        
        <div
        className={`interest-item ${member.interests.includes("14") ? "selected" : ""}`}
        onClick={() => handleInterestClick("14")}
      >
        연기/연극</div>

        <div
        className={`interest-item ${member.interests.includes("16") ? "selected" : ""}`}
        onClick={() => handleInterestClick("16")}
      >
        스타트업 아이디어</div>

        <div
        className={`interest-item ${member.interests.includes("17") ? "selected" : ""}`}
        onClick={() => handleInterestClick("17")}
      >
        마케팅 전략</div>
        
        <div
        className={`interest-item ${member.interests.includes("18") ? "selected" : ""}`}
        onClick={() => handleInterestClick("18")}
      >
        법률 특허 상담</div>

        <div
        className={`interest-item ${member.interests.includes("22") ? "selected" : ""}`}
        onClick={() => handleInterestClick("22")}
      >
        피트니스</div>
        <div
        className={`interest-item ${member.interests.includes("23") ? "selected" : ""}`}
        onClick={() => handleInterestClick("23")}
      >
        요가/필라테스</div>
        <div
        className={`interest-item ${member.interests.includes("24") ? "selected" : ""}`}
        onClick={() => handleInterestClick("24")}
      >
        재활 운동</div>
        <div
        className={`interest-item ${member.interests.includes("25") ? "selected" : ""}`}
        onClick={() => handleInterestClick("25")}
      >
        식단/영양 상담</div>
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
