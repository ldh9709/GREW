export const CREATED_USER = 2000; // 사용자 생성 성공
export const LOGIN_SUCCESS = 2000; // 로그인 성공
export const READ_USER = 2002; // 사용자 정보 조회 성공
export const UPDATE_USER = 2003; // 사용자 정보 업데이트 성공
export const DELETE_USER = 2004; // 사용자 삭제 성공
export const LOGOUT_USER = 2005; // 로그아웃 성공

// 실패 상태 코드
export const LOGIN_FAIL_NOT_FOUND_USER = 2050; // 로그인 실패: 사용자 없음
export const LOGIN_FAIL_PASSWORD_MISMATCH_USER = 2051; // 로그인 실패: 비밀번호 불일치
export const CREATE_FAIL_EXISTED_USER = 2052; // 사용자 생성 실패: 이미 존재하는 사용자
export const UNAUTHORIZED_USER = 2053; // 인증 실패: 권한 없음
