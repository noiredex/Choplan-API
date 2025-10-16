// JWT 응답필드 이름 맞추기
import axiosInstance from "./axiosInstance";

export const ownerLogin = async (loginData) => {
    const response = await axiosInstance.post("/auth/owner/login", loginData);
    // 백앤드 응답 필드에 맞게 토큰 반환 { token: "..."} 형태인지 확인
    localStorage.setItem("accessToken", response.data.token);
    return response.data;
};

export const ownerSignup = async (signupData) => {
  const response = await axiosInstance.post("/auth/owner/signup", signupData);
  return response.data;
};

export const fetchOwnerProfile = async () => {
    const response = await axiosInstance.get("/owner/me");
    return response.data;
};
