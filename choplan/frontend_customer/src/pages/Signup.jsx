import { useState } from "react";
import api from "../api/api";

function Signup() {
  const [form, setForm] = useState({
    email: "",
    password: "",
    realName: "",
    phone: "",
    nickname: "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // 유효성 검사 함수
  const validateForm = () => {
    // 비밀번호: 최소 10자, 숫자 + 대문자 포함
    const passwordRegex = /^(?=.*[A-Z])(?=.*\d).{10,}$/;
    if (!passwordRegex.test(form.password)) {
      alert("비밀번호는 최소 10자리 이상, 숫자와 대문자를 포함해야 합니다.");
      return false;
    }

    // 이름: 한글만 가능
    const nameRegex = /^[가-힣]+$/;
    if (!nameRegex.test(form.realName)) {
      alert("이름은 한글만 입력 가능합니다.");
      return false;
    }

    // 전화번호: 숫자만
    const phoneRegex = /^[0-9]+$/;
    if (!phoneRegex.test(form.phone)) {
      alert("전화번호는 숫자만 입력 가능합니다.");
      return false;
    }

    // 닉네임: 2글자 이상
    if (form.nickname.length < 2) {
      alert("닉네임은 최소 2글자 이상이어야 합니다.");
      return false;
    }

    return true;
  };

  const handleSignup = async (e) => {
    e.preventDefault();

    if (!validateForm()) return; // 조건 불만족 시 API 요청 안 함

    try {
      const res = await api.post("/auth/signup/customer", form);
      alert("회원가입 성공! 로그인 페이지로 이동합니다.");
      window.location.href = "/login";
    } catch (error) {
      alert("회원가입 실패. 입력값을 확인하세요.");
    }
  };

  return (
    <div>
      <h2>고객 회원가입</h2>
      <form onSubmit={handleSignup}>
        <input
          type="email"
          name="email"
          placeholder="이메일"
          value={form.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="비밀번호"
          value={form.password}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="realName"
          placeholder="이름 (한글만)"
          value={form.realName}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="phone"
          placeholder="전화번호 (숫자만)"
          value={form.phone}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="nickname"
          placeholder="닉네임 (2글자 이상)"
          value={form.nickname}
          onChange={handleChange}
          required
        />
        <button type="submit">회원가입</button>
      </form>
    </div>
  );
}

export default Signup;
