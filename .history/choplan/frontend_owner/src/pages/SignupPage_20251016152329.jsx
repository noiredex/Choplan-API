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
    <div
      style={{
        maxWidth: "400px",
        margin: "60px auto",
        padding: "40px",
        borderRadius: "12px",
        boxShadow: "0 0 10px rgba(0, 0, 0, 0.1)",
        backgroundColor: "#fff",
      }}
    >
      <h2
        style={{
          textAlign: "center",
          marginBottom: "25px",
          fontWeight: "bold",
          color: "#333",
        }}
      >
        점주 회원가입
      </h2>

      <form
        onSubmit={handleSubmit}
        encType="multipart/form-data"
        style={{ display: "flex", flexDirection: "column", gap: "14px" }}
      >
        <div>
          <label>이메일<span style={{ color: "red" }}> *</span></label>
          <input type="email" name="email" value={formData.email} onChange={handleChange} required />
        </div>

        <div>
          <label>비밀번호<span style={{ color: "red" }}> *</span></label>
          <input type="password" name="password" value={formData.password} onChange={handleChange} required />
        </div>

        <div>
          <label>이름<span style={{ color: "red" }}> *</span></label>
          <input type="text" name="realName" value={formData.realName} onChange={handleChange} required />
        </div>

        <div>
          <label>개인 전화번호<span style={{ color: "red" }}> *</span></label>
          <input type="text" name="phone" value={formData.phone} onChange={handleChange} required />
        </div>

        <div>
          <label>가게 이름<span style={{ color: "red" }}> *</span></label>
          <input type="text" name="storeName" value={formData.storeName} onChange={handleChange} required />
        </div>

        <div>
          <label>가게 전화번호<span style={{ color: "red" }}> *</span></label>
          <input type="text" name="storePhone" value={formData.storePhone} onChange={handleChange} required />
        </div>

        <div>
          <label>가게 주소<span style={{ color: "red" }}> *</span></label>
          <input type="text" name="roadAddress" value={formData.roadAddress} onChange={handleChange} required />
        </div>

        <div>
          <label>가게 상세주소<span style={{ color: "red" }}> *</span></label>
          <input type="text" name="detailAddress" value={formData.detailAddress} onChange={handleChange} required />
        </div>

        <div>
          <label>사업자 등록번호<span style={{ color: "red" }}> *</span></label>
          <input type="text" name="businessNumber" value={formData.businessNumber} onChange={handleChange} required />
        </div>

        <div>
          <label>사업자등록증 (PDF 첨부)<span style={{ color: "red" }}> *</span></label>
          <input
            type="file"
            name="businessRegistrationDoc"
            accept="application/pdf"
            onChange={handleChange}
            required
          />
        </div>

        <button
          type="submit"
          style={{
            width: "100%",
            marginTop: "15px",
            padding: "12px",
            backgroundColor: "#1a2c5b",
            color: "white",
            fontWeight: "bold",
            border: "none",
            borderRadius: "8px",
            cursor: "pointer",
            fontSize: "15px",
          }}
        >
          회원가입
        </button>
      </form>

      <p style={{ textAlign: "center", marginTop: "18px", fontSize: "14px" }}>
        이미 계정이 있으신가요?{" "}
        <a href="/login" style={{ color: "#1a2c5b", fontWeight: "bold", textDecoration: "none" }}>
          로그인하기
        </a>
      </p>
    </div>
  );
};

export default SignupPage;


