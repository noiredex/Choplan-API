// 로그인 페이지

import { useState } from "react";
import { useAuth } from "../hooks/useAuth";
import { ownerLogin } from "../api/ownerApi";

export default function LoginPage() {
  const { setToken } = useAuth();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    try {
      const data = await ownerLogin({ email, password });
      setToken(data.token);
      window.location.href = "/dashboard";
    } catch (err) {
      setError("로그인 실패: 이메일 또는 비밀번호를 확인하세요.");
    }
  };

  return (
    <div className="login-container">
      <h2>점주 로그인</h2>
      <form onSubmit={handleLogin}>
        <input
          type="email"
          placeholder="이메일"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">로그인</button>
      </form>
      {error && <p className="error">{error}</p>}
      <p>
        계정이 없으신가요?{" "}
        <a href="/signup" className="link">회원가입하기</a>
      </p>
    </div>
  );
}
