const BACKEND_SERVER = "";
const BASE_URL = '/mentor-board';

//특정 멘토 컨텐츠 리스트 조회
export const listBoardContentsByMemberNo = async(token,page,size) => {

    const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/list/member?page=${page}&size=${size}`,{
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

// 새로운 멘토 보드를 생성합니다.
export const createMentorBoard = async (token,data) => {
    const response = await fetch(`${BACKEND_SERVER}${BASE_URL}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(data), // JSON 형식으로 전송
    });
    if (!response.ok) {
        const errorData = await response.json();  // 서버에서 보내는 에러 메시지 출력
        throw new Error(`Failed to create mentor board: ${JSON.stringify(errorData)}`);
    }
    return response.json();
};


// 멘토 보드에 이미지를 업로드합니다.
export const uploadMentorBoardImage = async (id, formData) => {

    const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/${id}/upload-image`, {
        method: 'POST',
        body: formData, // `Content-Type` 헤더는 자동으로 설정되므로, 별도로 설정하지 않습니다.
    });

    if (!response.ok) {
        const errorData = await response.text(); // 서버 에러 메시지 읽기
        console.error('업로드 실패:', errorData); // 디버깅
        throw new Error('Failed to upload mentor board image');
    }

    // 응답을 JSON으로 처리
    const data = await response.json();  // 응답을 JSON으로 파싱

    return data;  // 서버에서 반환한 JSON 객체를 그대로 반환
};


// 멘토 보드 수정
export const updateMentorBoard = async (id, data, token) => {
    const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(data), // JSON 형식으로 전송
    });

    if (!response.ok) {
        const errorData = await response.json();  // 서버에서 보내는 에러 메시지 출력
        throw new Error(`Failed to update mentor board: ${JSON.stringify(errorData)}`);
    }

    return response.json();  // 수정된 멘토 보드 데이터 반환
};


/**
 * 🔥 멘토 보드 조회수 기준으로 정렬된 리스트를 가져옵니다.
 * @param {number} page - 페이지 번호
 * @param {number} size - 페이지 크기
 * @returns {Promise} - 멘토 보드 목록을 포함하는 프로미스
 */
export const listMentorBoardsByViews = async (page = 0, size = 10) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/sorted/views?page=${page}&size=${size}`);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 보드 상세 조회
 * @param {number} mentorBoardNo - 멘토 보드 번호
 * @returns {Promise} - 멘토 보드 데이터를 포함하는 프로미스
 */
export const getMentorBoardDetail = async (mentorBoardNo) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/${mentorBoardNo}`);
  if (!response.ok) {
    throw new Error('API 호출 실패');
  }
  return await response.json();
};



/**
 * 🔥 멘토 보드를 삭제(상태 변경)합니다.
 * @param {number} mentorBoardNo - 멘토 보드 번호
 * @returns {Promise} - 삭제 성공 메시지를 포함하는 프로미스
 */
export const deleteMentorBoard = async (token,mentorBoardNo) => {
  const response = await fetch(`${BACKEND_SERVER}${BASE_URL}/${mentorBoardNo}/status`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
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
export const searchMentorBoards = async (search, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}${BASE_URL}/search/${search}?page=${page}&size=${size}`
  );
  const responseJsonObject = await response.json();
  console.log(responseJsonObject);
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
 /////////////////////////카테고리 대분류 소분류 게시판


// 조회수 많은 순으로 카테고리별 멘토 보드 가져오기
export const listMentorBoardByCategoryView = async (categoryNo, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}${BASE_URL}/${categoryNo}/view-count?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

// 조회수 많은 순으로 카테고리별(대분류) 멘토 보드 가져오기
export const listMentorBoardByParentCategoryView = async (categoryNo, page = 0, size = 10) => {
  try {
    const response = await fetch(
      `${BACKEND_SERVER}${BASE_URL}/${categoryNo}/parent/view-count?page=${page}&size=${size}`
    );
    if (!response.ok) throw new Error("API 호출 실패");
    return await response.json();
  } catch (error) {
    console.error("API 호출 오류:", error);
  }
};

// 최신 순으로 카테고리별 멘토 보드 가져오기
export const listMentorBoardByCategoryDate = async (categoryNo, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}${BASE_URL}/${categoryNo}/date?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

// 최신 순으로 카테고리별(대분류) 멘토 보드 가져오기
export const listMentorBoardByParentCategoryDate = async (categoryNo, page = 0, size = 10) => {
  const response = await fetch(
   `${BACKEND_SERVER}${BASE_URL}/${categoryNo}/parent/date?page=${page}&size=${size}`,
   
    {
      method: "GET",
    }
    
  );
  
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 상태값과 조회수 기준으로 정렬된 멘토 보드 리스트를 가져옵니다.
*/
export const getMentorBoardsSortedByViews = async (status, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}/${BASE_URL}/sorted/views/status?status=${status}&page=${page}&size=${size}`,
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }
  );

  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const listMentorBoardsByProfile = async (mentorProfileNo, page, size) => {
  const response = await fetch(
    `/mentor-board/list/${mentorProfileNo}?page=${page}&size=${size}`,
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
  return response.json();
};////////////////////////// 12/30일추가