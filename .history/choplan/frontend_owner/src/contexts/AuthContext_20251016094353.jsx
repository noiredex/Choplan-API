// 점주 로그인 및 인증 관련 Context
import { createContext, useState, useEggect } from "react";

export const AuthContext = creatContext();

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem("accessToken") || null);
    
    useEffect(() => {
        if (token) localStorage.setItem("accessToken", token);
        else localStorage.removeItem("accessToken");
    }, [token]);

    const logout = () => {
        setToken(null);
        localStorage.removeItem("accessToken");
    };

    return (
        <AuthContext.Provider value={{ token, setToken, logout }}>
            {children}
            </AuthContext.Provider>
    );
};