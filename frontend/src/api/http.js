import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL ?? '/api', // .env.local 파일에서 설정한 API 기본 URL
  withCredentials: true, // 쿠키를 포함한 요청을 보내기 위해 설정
  headers: {
    'Content-Type': 'application/json',
  },
});

http.interceptors.request.use(
  (res) => res,
  async (err) => {
    throw err;
  }
);

export default http;