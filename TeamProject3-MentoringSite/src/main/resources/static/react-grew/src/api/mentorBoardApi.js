// 기본 URL 설정
const BACKEND_SERVER = '';

// 멘토 보드 목록을 날짜 기준으로 내림차순으로 가져옵니다. (페이징)
export const getMentorBoards = async (page = 0, size = 10) => {
    const response = await fetch(`${BACKEND_SERVER}/sorted/date?page=${page}&size=${size}`);
    if (!response.ok) throw new Error('Failed to fetch mentor boards');
    return response.json();
};

// 특정 멘토 보드의 상세 정보를 가져옵니다.
export const getMentorBoardDetail = async (id) => {
    const response = await fetch(`${BACKEND_SERVER}/${id}`);
    if (!response.ok) throw new Error('Failed to fetch mentor board detail');
    return response.json();
};

// 새로운 멘토 보드를 생성합니다.
export const createMentorBoard = async (data) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-board`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data), // JSON 형식으로 전송
    });
    if (!response.ok) {
        const errorData = await response.json();  // 서버에서 보내는 에러 메시지 출력
        throw new Error(`Failed to create mentor board: ${JSON.stringify(errorData)}`);
    }
    return response.json();
};

// 특정 멘토 보드를 수정합니다.
export const updateMentorBoard = async (id, data) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-board/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Failed to update mentor board');
    return response.json();
};

// 특정 멘토 보드를 소프트 삭제합니다. (상태 변경)
export const deleteMentorBoard = async (id) => {
    const response = await fetch(`${BACKEND_SERVER}/${id}/status`, {
        method: 'PUT',
    });
    if (!response.ok) throw new Error('Failed to delete mentor board');
    return response.json();
};

// 특정 멘토 보드의 조회수를 1 증가시킵니다.
export const increaseViewMentorBoard = async (id) => {
    const response = await fetch(`${BACKEND_SERVER}/${id}/views`, {
        method: 'PUT',
    });
    if (!response.ok) throw new Error('Failed to increase mentor board view count');
    return response.json();
};

// 특정 사용자가 작성한 멘토 보드 목록을 가져옵니다. (페이징)
export const getMentorBoardsByMember = async (memberNo, page = 0, size = 10) => {
    const response = await fetch(`${BACKEND_SERVER}/member/${memberNo}?page=${page}&size=${size}`);
    if (!response.ok) throw new Error('Failed to fetch mentor boards by member');
    return response.json();
};

// 검색어를 기반으로 멘토 보드를 검색합니다. (페이징)
export const searchMentorBoards = async (query, page = 0, size = 10) => {
    const response = await fetch(`${BACKEND_SERVER}/search?query=${encodeURIComponent(query)}&page=${page}&size=${size}`);
    if (!response.ok) throw new Error('Failed to search mentor boards');
    return response.json();
};

// 멘토 보드에 이미지를 업로드합니다.
export const uploadMentorBoardImage = async (id, file) => {
    const formData = new FormData();
    formData.append('file', file);

    const response = await fetch(`${BACKEND_SERVER}/mentor-board/${id}/upload-image`, {
        method: 'POST',
        body: formData,
    });
    
    if (!response.ok) throw new Error('Failed to upload mentor board image');
    
    // 응답을 JSON으로 처리
    const data = await response.json();  // 응답을 JSON으로 파싱

    return data;  // 서버에서 반환한 JSON 객체를 그대로 반환
};

// 멘토 보드의 이미지 URL을 가져옵니다.
export const getMentorBoardImageUrl = async (id) => {
    const response = await fetch(`${BACKEND_SERVER}/${id}/image-url`);
    if (!response.ok) throw new Error('Failed to fetch mentor board image URL');
    return response.text();
};
