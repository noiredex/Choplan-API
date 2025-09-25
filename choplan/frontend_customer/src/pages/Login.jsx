import { useState } from "react";
import api from "../api/api";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/auth/login", { email, password });
      const { token } = res.data.data;

      // 토큰 저장
      localStorage.setItem("token", token);

      alert("로그인 성공!");
      window.location.href = "/"; // 로그인 후 이동할 페이지 -> * 두희님 웹페이지랑 연결
    } catch (error) {
      alert("로그인 실패. 이메일/비밀번호를 확인하세요.");
    }
  };

  return (
    <div>
      <h2>로그인</h2>
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
    </div>
  );
}

export default Login;
