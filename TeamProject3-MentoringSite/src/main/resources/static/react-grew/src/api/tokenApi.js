import axios from "axios";

const api = axios.create({
  baseURL: "/api", // 백엔드 API 주소
  withCredentials: true, // 쿠키를 포함해 요청 전송
});

// 요청 인터셉터: 쿠키를 사용하므로 Authorization 헤더 설정 필요 없음
api.interceptors.request.use(
  (config) => {
    // 추가적인 헤더 설정이 필요하면 여기서 처리
    return config;
  },
  (error) => Promise.reject(error)
);

// 응답 인터셉터: 리프레시 토큰을 이용한 갱신 처리
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    if (error.response.status === 401 && !error.config._retry) {
      error.config._retry = true;
      try {
        // 리프레시 토큰 API 호출
        await axios.post("/auth/refresh-token", {}, { withCredentials: true });
        // 실패한 요청 재시도
        return api(error.config);
      } catch (err) {
        console.error("리프레시 토큰 갱신 실패:", err);
        // 로그아웃 처리
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  }
);

export default api;
