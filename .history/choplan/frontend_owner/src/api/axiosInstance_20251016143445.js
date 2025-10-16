import axios from "axios";

const axiosInstance = axios.create({
    caseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json"
    },
});

axiosInstance.interceptors.request.use((config => {
    const token = localStorage.getItem("axxessToken");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}));

export default axiosInstance;