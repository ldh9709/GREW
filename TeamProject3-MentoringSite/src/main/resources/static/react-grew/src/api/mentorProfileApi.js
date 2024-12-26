const BACKEND_SERVER = ''; // 🛠️ 서버 주소를 지정하세요
const BASE_URL = '/mentor-profile';






// 🔥 멘토 프로필 번호로 특정 멘토의 프로필 정보를 가져오는 함수
export const getMentorProfileByNo = async (mentorProfileNo) => {
  const response = await fetch(`${BASE_URL}/${mentorProfileNo}`);
  const data = await response.json();
  return data;
};






/**
 * 🔥 특정 상태의 멘토 프로필 리스트를 조회합니다.
 * @param {number} status - 멘토의 상태값
 * @param {number} page - 페이지 번호
 * @param {number} size - 페이지 크기
 * @returns {Promise} - 멘토 목록을 포함하는 프로미스
 */
export const listMentorProfiles = async (status = 3, page = 0, size = 10) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/status/${status}?page=${page}&size=${size}`);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};





/**
 * 🔥 특정 멘토 프로필을 조회합니다.
 * @param {number} mentorProfileNo - 멘토 프로필 번호
 * @returns {Promise} - 멘토 프로필 데이터를 포함하는 프로미스
 */
export const getMentorProfile = async (mentorProfileNo) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}`);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};



/**
 * 🔥 새로운 멘토 프로필을 생성합니다.
 * @param {number} memberNo - 멤버 번호
 * @param {object} mentorProfileDto - 멘토 프로필 데이터
 * @returns {Promise} - 생성 성공 메시지를 포함하는 프로미스
 */
export const createMentorProfile = async (memberNo, mentorProfileDto) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${memberNo}/create-profile`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    body: JSON.stringify(mentorProfileDto)
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 프로필을 수정합니다.
 * @param {number} mentorProfileNo - 멘토 프로필 번호
 * @param {object} mentorProfileDto - 수정할 멘토 프로필 데이터
 * @returns {Promise} - 수정 성공 메시지를 포함하는 프로미스
 */
export const updateMentorProfile = async (mentorProfileNo, mentorProfileDto) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    body: JSON.stringify(mentorProfileDto)
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 특정 카테고리의 멘토 프로필을 조회합니다.
 * @param {number} categoryNo - 카테고리 번호
 * @param {number} page - 페이지 번호
 * @param {number} size - 페이지 크기
 * @returns {Promise} - 특정 카테고리의 멘토 목록을 포함하는 프로미스
 */
export const getMentorProfilesByCategoryNo = async (categoryNo, page = 0, size = 10) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/category/${categoryNo}?page=${page}&size=${size}`);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 프로필을 검색합니다.
 * @param {string} keyword - 검색 키워드
 * @param {number} page - 페이지 번호
 * @param {number} size - 페이지 크기
 * @returns {Promise} - 검색된 멘토 목록을 포함하는 프로미스
 */
export const searchMentorProfiles = async (search, page = 0, size = 10) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/search/${search}?page=${page}&size=${size}`);
  const responseJsonObject = await response.json();
  console.log(responseJsonObject);
  return responseJsonObject;
};

/**
 * 🔥 멘토 프로필의 이미지 URL을 조회합니다.
 * @param {number} mentorProfileNo - 멘토 프로필 번호
 * @returns {Promise} - 이미지 URL을 포함하는 프로미스
 */
export const getMentorProfileImageUrl = async (mentorProfileNo) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}/image-url`);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토 프로필의 이미지를 업로드합니다.
 * @param {number} mentorProfileNo - 멘토 프로필 번호
 * @param {File} file - 업로드할 이미지 파일
 * @returns {Promise} - 업로드 성공 메시지를 포함하는 프로미스
 */
export const uploadMentorProfileImage = async (mentorProfileNo, file) => {
  const formData = new FormData();
  formData.append('file', file);
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}/upload-image`, {
    method: 'POST',
    body: formData
  });
  const responseJsonObject = await response.text();
  return responseJsonObject;
};

/**
 * 🔥 멘토의 멘토링 횟수를 조회합니다.
 * @param {number} mentorProfileNo - 멘토 프로필 번호
 * @returns {Promise} - 멘토링 횟수를 포함하는 프로미스
 */
export const getMentorMentoringCount = async (mentorProfileNo) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}/mentoring-count`);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토의 팔로우 수를 조회합니다.
 * @param {number} mentorProfileNo - 멘토 프로필 번호
 * @returns {Promise} - 팔로우 수를 포함하는 프로미스
 */
export const getMentorFollowCount = async (mentorProfileNo) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}/follow-count`);
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 * 🔥 멘토의 활동 수를 조회합니다.
 * @param {number} mentorProfileNo - 멘토 프로필 번호
 * @returns {Promise} - 활동 수를 포함하는 프로미스
 */
export const getMentorActivityCount = async (mentorProfileNo) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}/activity-count`);
  const responseJsonObject = await response.json();
  return responseJsonObject;

  
};


//멘토 활동수 ,팔로우순 , 멘토링수  
export const listMentorsByFollowCount = async (page, size) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/follow-count?page=${page}&size=${size}`, {
    method: 'GET',
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const listMentorsByMentoringCount = async (page, size) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/mentoring-count?page=${page}&size=${size}`, {
    method: 'GET',
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

export const listMentorsByActivityCount = async (page, size) => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/activity-count?page=${page}&size=${size}`, {
    method: 'GET',
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
export const listMentorsByRating = async () => {
  const response = await fetch(`${BACKEND_SERVER}/mentor-profile/rating`, {
    method: 'GET',
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
};




///////////////////////////////////////////////////////////////////////////////////////////
// PARENT_CATEGORY_NO 기준 팔로우 순 멘토 리스트 조회
/**
 *  대분류 카테고리의 팔로우 순 멘토 리스트 조회
 */
export const listMentorsByParentCategoryFollowCount = async (parentCategoryNo, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}${BASE_URL}/${parentCategoryNo}/parent/follow?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 *  대분류 카테고리의 멘토링 횟수 순 멘토 리스트 조회
 */
export const listMentorsByParentCategoryMentoringCount = async (parentCategoryNo, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}${BASE_URL}/${parentCategoryNo}/parent/mentoring?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

/**
 *  대분류 카테고리의 활동 순 멘토 리스트 조회
 */
export const listMentorsByParentCategoryActivityCount = async (parentCategoryNo, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}${BASE_URL}/${parentCategoryNo}/parent/activity?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};


// CATEGORY_NO 기준 팔로우 순 멘토 리스트 조회
export const listMentorsByCategoryNoFollow = async (categoryNo, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}${BASE_URL}/category/${categoryNo}/follow?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

// CATEGORY_NO 기준 멘토링 횟수 순 멘토 리스트 조회
export const listMentorsByCategoryNoMentoring = async (categoryNo, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}${BASE_URL}/category/${categoryNo}/mentoring?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};

// CATEGORY_NO 기준 활동 수 순 멘토 리스트 조회
export const listMentorsByCategoryNoActivity = async (categoryNo, page = 0, size = 10) => {
  const response = await fetch(
    `${BACKEND_SERVER}${BASE_URL}/category/${categoryNo}/activity?page=${page}&size=${size}`,
    {
      method: "GET",
    }
  );
  const responseJsonObject = await response.json();
  return responseJsonObject;
};
