import { useState } from "react";
import { ownerSignup } from "../api/ownerApi";

export default function SignupPage() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    phone: "",
    storeName: "",
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    try {
      const result = await ownerSignup(formData);
      setMessage("회원가입 성공! 관리자 승인 후 로그인할 수 있습니다.");
      console.log(result);
      setTimeout(() => (window.location.href = "/"), 2000);
    } catch (err) {
      setMessage("회원가입 실패: 이미 등록된 이메일이거나 서버 오류입니다.");
    }
  };

  return (
    <div className="signup-container">
      <h2>점주 회원가입</h2>
      <form onSubmit={handleSubmit}>
        <input
          name="name"
          type="text"
          placeholder="이름"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          name="email"
          type="email"
          placeholder="이메일"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          name="password"
          type="password"
          placeholder="비밀번호"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <input
          name="phone"
          type="tel"
          placeholder="연락처"
          value={formData.phone}
          onChange={handleChange}
          required
        />
        <input
          name="storeName"
          type="text"
          placeholder="매장 이름"
          value={formData.storeName}
          onChange={handleChange}
          required
        />
        <button type="submit">회원가입</button>
      </form>
      {message && <p>{message}</p>}
      <p>
        이미 계정이 있으신가요?{" "}
        <a href="/" className="link">로그인하기</a>
      </p>
    </div>
  );
}
