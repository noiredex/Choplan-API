// 점주 회원가입 페이지 백앤드와 동일한 조건으로 수정
// 사업자등록증은 PDF 파일로 별도첨부
// 회원가입시 작성해야 하는 필수 항목 별도 표기

import React, { useState } from "react";
import axios from "../api/axiosInstance";

const SignupPage = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    confirmPassword: "",
    realName: "",
    phone: "",
    storeName: "",
    storePhone: "",
    roadAddress: "",
    detailAddress: "",
    businessNumber: "",
    businessRegistrationDoc: null,
  });

  const [errors, setErrors] = useState({});

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

    if (formData.password !== formData.confirmPassword) {
      setErrors({ password: "비밀번호가 일치하지 않습니다." });
      return;
    }

    const data = new FormData();
    Object.keys(formData).forEach((key) => {
      if (formData[key] !== null && key !== "confirmPassword") {
        data.append(key, formData[key]);
      }
    });

    try {
      const response = await axios.post("/auth/owner/signup", data, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      alert(response.data.message);
      setErrors({});
    } catch (error) {
      if (error.response?.data?.errors) {
        setErrors(error.response.data.errors);
      } else {
        alert("회원가입 중 오류가 발생했습니다.");
      }
    }
  };

  return (
    <div className="signup-container">
      <h2 className="signup-title">점주 회원가입</h2>

      <form className="signup-form" onSubmit={handleSubmit} encType="multipart/form-data">
        {/* ====== 사업자 대표 정보 ====== */}
        <h3 style={{ textAlign: "center", margin: "20px 0 10px" }}>[ 사업자 대표 정보란 ]</h3>
        <hr style={{ border: "0", borderTop: "2px solid #ddd", marginBottom: "20px" }} />

        <div className="form-group">
          <input
            type="email"
            name="email"
            placeholder="이메일 *"
            value={formData.email}
            onChange={handleChange}
            required
          />
          {errors.email && <p className="error-message">{errors.email}</p>}
        </div>

        <div className="form-group">
          <input
            type="password"
            name="password"
            placeholder="비밀번호 *"
            value={formData.password}
            onChange={handleChange}
            required
          />
          {errors.password && <p className="error-message">{errors.password}</p>}
        </div>

        <div className="form-group">
          <input
            type="password"
            name="confirmPassword"
            placeholder="비밀번호 확인 *"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />
          {errors.confirmPassword && <p className="error-message">{errors.confirmPassword}</p>}
        </div>

        <div className="form-group">
          <input
            type="text"
            name="realName"
            placeholder="이름 *"
            value={formData.realName}
            onChange={handleChange}
            required
          />
          {errors.realName && <p className="error-message">{errors.realName}</p>}
        </div>

        <div className="form-group">
          <input
            type="text"
            name="phone"
            placeholder="개인 전화번호 *"
            value={formData.phone}
            onChange={handleChange}
            required
          />
          {errors.phone && <p className="error-message">{errors.phone}</p>}
        </div>

        {/* ====== 사업장 정보 ====== */}
        <h3 style={{ textAlign: "center", margin: "25px 0 10px" }}>[ 사업장 정보란 ]</h3>
        <hr style={{ border: "0", borderTop: "2px solid #ddd", marginBottom: "20px" }} />

        {[
          ["storeName", "가게 이름 *"],
          ["storePhone", "가게 전화번호 *"],
          ["roadAddress", "가게 주소 *"],
          ["detailAddress", "가게 상세주소 *"],
          ["businessNumber", "사업자 등록번호 *"],
        ].map(([key, placeholder]) => (
          <div key={key} className="form-group">
            <input
              type="text"
              name={key}
              placeholder={placeholder}
              value={formData[key]}
              onChange={handleChange}
              required
            />
            {errors[key] && <p className="error-message">{errors[key]}</p>}
          </div>
        ))}

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
