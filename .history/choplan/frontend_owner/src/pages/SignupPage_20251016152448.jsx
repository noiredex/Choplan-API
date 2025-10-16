// 점주 회원가입 페이지 백앤드와 동일한 조건으로 수정
// 사업자등록증은 PDF 파일로 별도첨부
// 회원가입시 작성해야 하는 필수 항목 별도 표기

import React, { useState } from "react";
import axios from "../api/axiosInstance";

const SignupPage = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    realName: "",
    phone: "",
    storeName: "",
    storePhone: "",
    roadAddress: "",
    detailAddress: "",
    businessNumber: "",
    businessRegistrationDoc: null,
  });

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "businessRegistrationDoc") {
      setFormData({ ...formData, [name]: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = new FormData();
    Object.keys(formData).forEach((key) => {
      if (formData[key] !== null) data.append(key, formData[key]);
    });

    try {
      const response = await axios.post("/auth/owner/signup", data, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      alert(response.data.message || "회원가입 성공!");
    } catch (error) {
      alert(error.response?.data?.message || "회원가입 중 오류가 발생했습니다.");
    }
  };

  return (
    <div className="signup-container">
      <h2 className="signup-title">점주 회원가입</h2>

      <form className="signup-form" onSubmit={handleSubmit} encType="multipart/form-data">
        <input
          type="email"
          name="email"
          placeholder="이메일 *"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="비밀번호 *"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="realName"
          placeholder="이름 *"
          value={formData.realName}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="phone"
          placeholder="개인 전화번호 *"
          value={formData.phone}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="storeName"
          placeholder="가게 이름 *"
          value={formData.storeName}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="storePhone"
          placeholder="가게 전화번호 *"
          value={formData.storePhone}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="roadAddress"
          placeholder="가게 주소 *"
          value={formData.roadAddress}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="detailAddress"
          placeholder="가게 상세주소 *"
          value={formData.detailAddress}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="businessNumber"
          placeholder="사업자 등록번호 *"
          value={formData.businessNumber}
          onChange={handleChange}
          required
        />
        <label className="file-label">
          사업자등록증 (PDF 첨부) *
          <input
            type="file"
            name="businessRegistrationDoc"
            accept="application/pdf"
            onChange={handleChange}
            required
          />
        </label>

        <button type="submit" className="signup-button">
          회원가입
        </button>
      </form>

      <p className="login-link">
        이미 계정이 있으신가요?{" "}
        <a href="/login" className="login-link-text">
          로그인하기
        </a>
      </p>
    </div>
  );
};

export default SignupPage;



