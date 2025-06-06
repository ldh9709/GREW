// 성공 코드 (2000 ~ 2049)
export const CREATED_MEMBER_SUCCESS = 2000; // 회원 생성 성공
export const READ_MEMBER_SUCCESS = 2001; // 회원 조회 성공
export const READ_MEMBER_LIST_SUCCESS = 2002; // 회원 목록 조회 성공
export const UPDATE_MEMBER_SUCCESS = 2003; // 회원 정보 수정 성공
export const DELETE_MEMBER_SUCCESS = 2004; // 회원 삭제 성공
export const LOGIN_MEMBER_SUCCESS = 2005; // 회원 로그인 성공
export const LOGOUT_MEMBER_SUCCESS = 2006; // 회원 로그아웃 성공
export const ACTIVATE_MEMBER_SUCCESS = 2007; // 회원 활성화 성공
export const DEACTIVATE_MEMBER_SUCCESS = 2008; // 회원 비활성화 성공
export const PASSWORD_RESET_SUCCESS = 2009; // 비밀번호 재설정 성공
export const EMAIL_SEND_SUCCESS = 2010; // 이메일 발송 성공
export const INPUTCODE_CONFIRM_SUCCESS = 2011; // 인증 코드 확인 성공
export const UPDATE_ROLE_SUCCESS = 2012; // 회원 권한 수정 성공
export const CONFIRM_EMAIL_SUCCESS = 2013;

// 실패 코드 (2050 ~ )
export const CREATED_MEMBER_FAIL = 2050; // 회원 생성 실패
export const INVALID_AUTH_CODE = 2051; // 인증 코드 불일치
export const READ_MEMBER_FAIL = 2052; // 특정 회원 조회 실패
export const READ_MEMBER_LIST_FAIL = 2053; // 회원 목록 조회 실패
export const UPDATE_MEMBER_FAIL = 2054; // 회원 정보 수정 실패
export const DELETE_MEMBER_FAIL = 2055; // 회원 삭제 실패
export const LOGIN_MEMBER_FAIL = 2056; // 회원 로그인 실패
export const PASSWORD_RESET_FAIL = 2057; // 비밀번호 재설정 실패
export const EMAIL_SEND_FAIL = 2058; // 이메일 발송 실패
export const INPUTCODE_CONFIRM_FAIL = 2059; // 인증 코드 확인 실패
export const MEMBER_PROVIDER_IS_NOT_EMAIL = 2060; // 이메일로 가입한 회원이 아님
export const AUTHENTICATION_FAILED = 2061; // 토큰과 정보가 일치하지 않음
export const UPDATE_ROLE_FAIL = 2062; // 회원 권한 수정 실패
export const DUPLICATION_MEMBER_ID = 2063; // 아이디 중복
export const DUPLICATION_MEMBER_EMAIL = 2064; // 이메일 중복
export const NOT_FOUND_MEMBER = 2065; // 가입하려는 회원정보 null
export const NOT_AGREEMENT_MEMBER_ID = 2066; // 회원 ID와 정보가 일치하지 않음
export const NOT_AGREEMENT_MEMBER_NAME = 2067; // 회원 NAME과 정보가 일치하지 않음
export const INPUT_NULL = 2068; // NULL 값 입력


//MENTOR 성공 코드
export const CREATED_MENTOR_PROFILE_SUCCESS_CODE = 2351; //멘토 가입 성공
export const UPDATE_MENTOR_PROFILE_SUCCESS_CODE = 2361 //멘토 수정 성공