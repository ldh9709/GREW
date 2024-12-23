
const BACKEND_SERVER = ''; // 🛠️ 서버 주소를 지정하세요
const BASE_URL = '/mentor-board';

//특정 멘토 컨텐츠 리스트 조회
export const listBoardContentsByMemberNo = async(token,page,size) => {
    
    const response = await fetch(`${BACKEND_SERVER}/mentor-board/list/member?page=${page}&size=${size}`,{
        method:'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })
    const responseJsonObject = await response.json();
    return responseJsonObject;
}

/**
 * 🔥 멘토 보드 리스트를 상태별로 조회합니다.
 * @param {number} status - 멘토 보드 상태값
 * @param {number} page - 페이지 번호
 * @param {number} size - 페이지 크기
 * @returns {Promise} - 멘토 보드 목록을 포함하는 프로미스
 */
export const listMentorBoardsByStatus = async (status, page = 0, size = 10) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/sorted/${status}?page=${page}&size=${size}`);
  console.log(response)
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 보드 조회수 기준으로 정렬된 리스트를 가져옵니다.
 * @param {number} page - 페이지 번호
 * @param {number} size - 페이지 크기
 * @returns {Promise} - 멘토 보드 목록을 포함하는 프로미스
 */
export const listMentorBoardsByViews = async (page = 0, size = 10) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/sorted/views?page=${page}&size=${size}`);
  console.log(response)
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 보드 상세 조회
 * @param {number} mentorBoardNo - 멘토 보드 번호
 * @returns {Promise} - 멘토 보드 데이터를 포함하는 프로미스
 */
export const getMentorBoardDetail = async (mentorBoardNo) => {
  const response = await fetch(`/mentor-board/${mentorBoardNo}`);
  if (!response.ok) {
    throw new Error('API 호출 실패');
  }
  return await response.json();
};


/**
 * 🔥 새로운 멘토 보드를 생성합니다.
 * @param {object} mentorBoardDto - 멘토 보드 데이터
 * @returns {Promise} - 생성 성공 메시지를 포함하는 프로미스
 */
export const createMentorBoard = async (mentorBoardDto) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    body: JSON.stringify(mentorBoardDto),
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 보드를 수정합니다.
 * @param {number} mentorBoardNo - 멘토 보드 번호
 * @param {object} mentorBoardDto - 수정할 데이터
 * @returns {Promise} - 수정 성공 메시지를 포함하는 프로미스
 */
export const updateMentorBoard = async (mentorBoardNo, mentorBoardDto) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/${mentorBoardNo}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    body: JSON.stringify(mentorBoardDto),
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 보드를 삭제(상태 변경)합니다.
 * @param {number} mentorBoardNo - 멘토 보드 번호
 * @returns {Promise} - 삭제 성공 메시지를 포함하는 프로미스
 */
export const deleteMentorBoard = async (mentorBoardNo) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/${mentorBoardNo}/status`, {
    method: 'PUT',
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 보드 조회수 증가
 * @param {number} mentorBoardNo - 멘토 보드 번호
 * @returns {Promise} - 조회수 증가 성공 메시지를 포함하는 프로미스
 */
export const increaseViewCount = async (mentorBoardNo) => {
  await fetch(`${BACKEND_SERVER}${BASE_URL}/${mentorBoardNo}/views`, { method: 'PUT' });
};

/**
 * 🔥 멘토 보드 검색
 * @param {string} keyword - 검색 키워드
 * @param {number} page - 페이지 번호
 * @param {number} size - 페이지 크기
 * @returns {Promise} - 검색된 멘토 보드 목록을 포함하는 프로미스
 */
export const searchMentorBoards = async (keyword, page = 0, size = 10) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/search?query=${encodeURIComponent(keyword)}&page=${page}&size=${size}`);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 보드의 이미지 URL을 조회합니다.
 * @param {number} mentorBoardNo - 멘토 보드 번호
 * @returns {Promise} - 이미지 URL을 포함하는 프로미스
 */
export const getMentorBoardImageUrl = async (mentorBoardNo) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/${mentorBoardNo}/image-url`);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 보드 이미지를 업로드합니다.
 * @param {number} mentorBoardNo - 멘토 보드 번호
 * @param {File} file - 업로드할 이미지 파일
 * @returns {Promise} - 업로드 성공 메시지를 포함하는 프로미스
 */
export const uploadMentorBoardImage = async (mentorBoardNo, file) => {
  const formData = new FormData();
  formData.append('file', file);
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/${mentorBoardNo}/upload-image`, {
    method: 'POST',
    body: formData,
  });
  return await response.text();
};

