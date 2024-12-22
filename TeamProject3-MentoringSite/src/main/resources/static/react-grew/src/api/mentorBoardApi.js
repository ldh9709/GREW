// mentor-board 관련 API 요청을 위한 파일

// 기본 URL 설정
const BASE_URL = '';

// mentor-board와 관련된 모든 API 기능을 관리하는 객체

    /**
     * 멘토 보드 리스트를 상태별로 가져옵니다. (페이징)
     */
    getMentorBoardsByStatus: async (status, page = 0, size = 10) => {
        const response =await fetch(`${BASE_URL}/mentor-board/sorted/${status}?page=${page}&size=${size}`,{
            method: "GET" //HTTp메서드
        })
        console.log(response)
        return response.json();
    }
    //http://localhost:8080/mentor-board/sorted/1?page=0&size=10
   // http://localhost:8080/mentor-board/sorted/1?page=0&size=10
    /**
     * 멘토 보드 목록을 날짜 기준으로 내림차순으로 가져옵니다. (페이징)
     * @param {number} page - 가져올 페이지 번호 (기본값: 0)
     * @param {number} size - 한 페이지에 포함할 항목 수 (기본값: 10)
     * @returns {Promise} - 멘토 보드 목록
     */
    getMentorBoards: async (page = 0, size = 10) => {
        const response = await fetch(`${BASE_URL}/sorted/date/other?page=${page}&size=${size}`);
        if (!response.ok) throw new Error('Failed to fetch mentor boards');
        return response.json();
    };

    /**
     * 멘토 보드를 조회수 기준으로 정렬해서 가져옵니다. (페이징)
     * @param {number} page - 가져올 페이지 번호 (기본값: 0)
     * @param {number} size - 한 페이지에 포함할 항목 수 (기본값: 10)
     * @returns {Promise} - 조회수 기준 정렬된 멘토 보드 목록
     */
    getMentorBoardsSortedByViews: async (page = 0, size = 10) => {
        const response = await fetch(`${BASE_URL}/sorted/views?page=${page}&size=${size}`);
        if (!response.ok) throw new Error('Failed to fetch mentor boards sorted by views');
        return response.json();
    };

    /**
     * 특정 사용자가 작성한 멘토 보드 목록을 가져옵니다. (페이징)
     * @param {number} memberNo - 사용자의 고유 번호
     * @param {number} page - 가져올 페이지 번호 (기본값: 0)
     * @param {number} size - 한 페이지에 포함할 항목 수 (기본값: 10)
     * @returns {Promise} - 특정 사용자가 작성한 멘토 보드 목록
     */
    getMentorBoardsByMember: async (memberNo, page = 0, size = 10) => {
        const response = await fetch(`${BASE_URL}/member/${memberNo}?page=${page}&size=${size}`);
        if (!response.ok) throw new Error('Failed to fetch mentor boards by member');
        return response.json();
    };

    /**
     * 멘토 보드를 검색합니다. (페이징)
     * @param {string} query - 검색어
     * @param {number} page - 가져올 페이지 번호 (기본값: 0)
     * @param {number} size - 한 페이지에 포함할 항목 수 (기본값: 10)
     * @returns {Promise} - 검색 결과 목록
     */
    searchMentorBoards: async (query, page = 0, size = 10) => {
        const response = await fetch(`${BASE_URL}/search?query=${encodeURIComponent(query)}&page=${page}&size=${size}`);
        if (!response.ok) throw new Error('Failed to search mentor boards');
        return response.json();
    };

    /**
     * 멘토 보드의 상세 정보를 가져옵니다.
     * @param {number} id - 멘토 보드의 ID
     * @returns {Promise} - 멘토 보드의 상세 정보
     */
    getMentorBoardDetail: async (id) => {
        const response = await fetch(`${BASE_URL}/${id}`);
        if (!response.ok) throw new Error('Failed to fetch mentor board detail');
        return response.json();
    };

    /**
     * 멘토 보드의 조회수를 증가시킵니다.
     * @param {number} id - 멘토 보드의 ID
     * @returns {Promise} - 조회수가 증가된 멘토 보드 정보
     */
    increaseViewMentorBoard: async (id) => {
        const response = await fetch(`${BASE_URL}/${id}/views`, {
            method: 'PUT',
        });
        if (!response.ok) throw new Error('Failed to increase mentor board view count');
        return response.json();
    };

    /**
     * 멘토 보드를 생성합니다.
     * @param {Object} data - 멘토 보드 데이터 (제목, 내용 등)
     * @returns {Promise} - 생성된 멘토 보드 정보
     */
    createMentorBoard: async (data) => {
        const response = await fetch(`${BASE_URL}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),
        });
        if (!response.ok) throw new Error('Failed to create mentor board');
        return response.json();
    };

    /**
     * 멘토 보드를 수정합니다.
     * @param {number} id - 수정할 멘토 보드의 ID
     * @param {Object} data - 수정할 멘토 보드 데이터
     * @returns {Promise} - 수정된 멘토 보드 정보
     */
    updateMentorBoard: async (id, data) => {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),
        });
        if (!response.ok) throw new Error('Failed to update mentor board');
        return response.json();
    };
    /**
     * 멘토 보드를 삭제(상태 변경)합니다.
     * @param {number} id - 삭제할 멘토 보드의 ID
     * @returns {Promise} - 삭제된 멘토 보드 정보
     */
    deleteMentorBoard: async (id) => {
        const response = await fetch(`${BASE_URL}/${id}/status`, {
            method: 'PUT',
        });
        if (!response.ok) throw new Error('Failed to delete mentor board');
        return response.json();
    };

    /**
     * 멘토 보드 이미지 업로드
     * @param {number} id - 업로드 대상 보드 ID
     * @param {File} file - 업로드할 이미지 파일
     * @returns {Promise} - 업로드된 이미지 URL
     */
    uploadMentorBoardImage: async (id, file) => {
        const formData = new FormData();
        formData.append('file', file);

        const response = await fetch(`${BASE_URL}/${id}/upload-image`, {
            method: 'POST',
            body: formData,
        });
        if (!response.ok) throw new Error('Failed to upload mentor board image');
        return response.text();
    };

    /**
     * 멘토 보드의 이미지 URL 가져오기
     * @param {number} id - 이미지 URL을 가져올 보드 ID
     * @returns {Promise} - 이미지 URL
     */
    getMentorBoardImageUrl: async (id) => {
        const response = await fetch(`${BASE_URL}/${id}/image-url`);
        if (!response.ok) throw new Error('Failed to fetch mentor board image URL');
        return response.text();
    };



