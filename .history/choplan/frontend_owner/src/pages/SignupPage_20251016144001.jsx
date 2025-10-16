import { useState } from "react";
import axios from "axios";

export default function SignupPage() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    phoneNumber: "",
    businessRegistrationNumber: "",
    businessRegistrationDoc: null,
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "businessRegistrationDoc") {
      setFormData({ ...formData, businessRegistrationDoc: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");

    const dataToSend = new FormData();
    Object.entries(formData).forEach(([key, value]) => {
      dataToSend.append(key, value);
    });

    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/owner/signup",
        dataToSend,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );
      console.log("서버 응답:", response.data);
      setMessage("회원가입 성공! 관리자 승인 후 로그인할 수 있습니다.");
      setTimeout(() => (window.location.href = "/"), 2000);
    } catch (error) {
      console.error("회원가입 오류:", error);
      setMessage("회원가입 실패: 입력값을 확인하세요.");
    }
  };

  return (
    <div className="signup-container">
      <h2>점주 회원가입</h2>
      <form onSubmit={handleSubmit} encType="multipart/form-data">
        <input
          type="text"
          name="name"
          placeholder="이름"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="이메일"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="비밀번호"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <input
          type="tel"
          name="phoneNumber"
          placeholder="전화번호"
          value={formData.phoneNumber}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="businessRegistrationNumber"
          placeholder="사업자 등록번호"
          value={formData.businessRegistrationNumber}
          onChange={handleChange}
          required
        />
        <label htmlFor="businessRegistrationDoc" className="file-label">
          사업자등록증 (PDF 첨부)
        </label>
        <input
          id="businessRegistrationDoc"
          type="file"
          name="businessRegistrationDoc"
          accept="application/pdf"
          onChange={handleChange}
          required
        />

        <button type="submit">회원가입</button>
      </form>
      {message && <p>{message}</p>}
      <p>
        이미 계정이 있으신가요?{" "}
        <a href="/" className="link">
          로그인하기
        </a>
      </p>
    </div>
  );
}
