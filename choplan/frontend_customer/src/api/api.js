import axios from "axios";

// 백엔드 서버 주소 (Spring Boot)
const API_BASE_URL = "http://localhost:8080";

// axios 인스턴스 생성
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// -------------------- 요청 인터셉터 --------------------
api.interceptors.request.use(
  (config) => {
    // 요청 전에 JWT 토큰 자동 추가
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    // 요청 자체가 실패한 경우
    console.error("요청 에러:", error);
    return Promise.reject(error);
  }
);

// -------------------- 응답 인터셉터 --------------------
api.interceptors.response.use(
  (response) => {
    // 정상 응답일 때 그대로 반환
    return response;
  },
  (error) => {
    if (error.response) {
      const { status } = error.response;

      // 인증 오류 (401 Unauthorized)
      if (status === 401) {
        alert("세션이 만료되었습니다. 다시 로그인하세요.");
        localStorage.removeItem("token");
        window.location.href = "/login";
      }

      // 권한 오류 (403 Forbidden)
      if (status === 403) {
        alert("접근 권한이 없습니다.");
      }
    } else {
      // 서버 응답이 아예 없는 경우 (네트워크 오류 등)
      console.error("서버에 연결할 수 없습니다.", error);
    }

    return Promise.reject(error);
  }
);

export default api;
