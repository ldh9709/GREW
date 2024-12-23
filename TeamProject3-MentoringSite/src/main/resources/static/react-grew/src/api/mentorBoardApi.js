// 기본 URL 설정
const BACKEND_SERVER = "";


// 멘토 보드 상세 조회
export const getMentorBoard = async (id) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-board/${id}`);

    if (!response.ok) {
        const errorData = await response.json();  // 서버에서 보내는 에러 메시지 출력
        throw new Error(`Failed to fetch mentor board: ${JSON.stringify(errorData)}`);
    }
    
    return response.json();  // 조회한 멘토 보드 데이터 반환
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


// 멘토 보드에 이미지를 업로드합니다.
export const uploadMentorBoardImage = async (id, formData) => {
 
    const response = await fetch(`${BACKEND_SERVER}/mentor-board/${id}/upload-image`, {
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
export const updateMentorBoard = async (id, data) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-board/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data), // JSON 형식으로 전송
    });

    if (!response.ok) {
        const errorData = await response.json();  // 서버에서 보내는 에러 메시지 출력
        throw new Error(`Failed to update mentor board: ${JSON.stringify(errorData)}`);
    }
    
    return response.json();  // 수정된 멘토 보드 데이터 반환
};

// 멘토 보드 삭제 (상태 변경)
export const deleteMentorBoard = async (id) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-board/${id}/status`, {
        method: 'PUT', // PUT 방식으로 상태 변경
    });

    if (!response.ok) {
        const errorData = await response.json();  // 서버에서 보내는 에러 메시지 출력
        throw new Error(`Failed to delete mentor board: ${JSON.stringify(errorData)}`);
    }
    
    return response.json();  // 삭제된 보드의 데이터 반환
};
