// 점주 회원가입 페이지 백앤드와 동일한 조건으로 수정
// 사업자등록증은 PDF 파일로 별도첨부
// 회원가입시 작성해야 하는 필수 항목 별도 표기

import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./SignupPage.css";

function SignupPage() {
  const navigate = useNavigate();

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
  const [successMessage, setSuccessMessage] = useState("");

  // 입력 값 변경 핸들러
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // 파일 선택 핸들러
  const handleFileChange = (e) => {
    setFormData({ ...formData, businessRegistrationDoc: e.target.files[0] });
  };

  // 폼 제출
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setErrors({ confirmPassword: "비밀번호가 일치하지 않습니다." });
      return;
    }

    const data = new FormData();
    Object.entries(formData).forEach(([key, value]) => {
      data.append(key, value);
    });

    try {
      const response = await axios.post("/auth/owner/signup", data, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      setSuccessMessage(response.data.message);
      setErrors({});

      // 회원가입 완료 후 3초 후 로그인 페이지로 자동 이동
      setTimeout(() => {
        navigate("/login");
      }, 3000);
    } catch (error) {
      if (error.response?.data?.errors) {
        setErrors(error.response.data.errors);
      } else {
        setErrors({ general: "서버 오류가 발생했습니다." });
      }
    }
  };

  return (
    <div className="signup-container">
      <h2>점주 회원가입</h2>

      <form onSubmit={handleSubmit} className="signup-form">

        {/* -------- 사업자 대표 정보란 -------- */}
        <h3>[ 사업자 대표 정보란 ]</h3>
        <hr className="divider" />

        <input
          type="email"
          name="email"
          placeholder="이메일 *"
          value={formData.email}
          onChange={handleChange}
          required
        />
        {errors.email && <p className="error">{errors.email}</p>}

        <input
          type="password"
          name="password"
          placeholder="비밀번호 *"
          value={formData.password}
          onChange={handleChange}
          required
        />
        {errors.password && <p className="error">{errors.password}</p>}

        <input
          type="password"
          name="confirmPassword"
          placeholder="비밀번호 확인 *"
          value={formData.confirmPassword}
          onChange={handleChange}
          required
        />
        {errors.confirmPassword && <p className="error">{errors.confirmPassword}</p>}

        <input
          type="text"
          name="realName"
          placeholder="이름 *"
          value={formData.realName}
          onChange={handleChange}
          required
        />
        {errors.realName && <p className="error">{errors.realName}</p>}

        <input
          type="text"
          name="phone"
          placeholder="개인 전화번호 *"
          value={formData.phone}
          onChange={handleChange}
          required
        />
        {errors.phone && <p className="error">{errors.phone}</p>}

        {/* -------- 사업장 정보란 -------- */}
        <h3>[ 사업장 정보란 ]</h3>
        <hr className="divider" />

        <input
          type="text"
          name="storeName"
          placeholder="가게 이름 *"
          value={formData.storeName}
          onChange={handleChange}
          required
        />
        {errors.storeName && <p className="error">{errors.storeName}</p>}

        <input
          type="text"
          name="storePhone"
          placeholder="가게 전화번호 *"
          value={formData.storePhone}
          onChange={handleChange}
          required
        />
        {errors.storePhone && <p className="error">{errors.storePhone}</p>}

        <input
          type="text"
          name="roadAddress"
          placeholder="가게 주소 *"
          value={formData.roadAddress}
          onChange={handleChange}
          required
        />
        {errors.roadAddress && <p className="error">{errors.roadAddress}</p>}

        <input
          type="text"
          name="detailAddress"
          placeholder="가게 상세주소 *"
          value={formData.detailAddress}
          onChange={handleChange}
          required
        />
        {errors.detailAddress && <p className="error">{errors.detailAddress}</p>}

        <input
          type="text"
          name="businessNumber"
          placeholder="사업자 등록번호 *"
          value={formData.businessNumber}
          onChange={handleChange}
          required
        />
        {errors.businessNumber && <p className="error">{errors.businessNumber}</p>}

        <label className="file-label">
          사업자등록증 (PDF 첨부) *
          <input
            type="file"
            name="businessRegistrationDoc"
            accept="application/pdf"
            onChange={handleFileChange}
            required
          />
        </label>
        {errors.businessRegistrationDoc && (
          <p className="error">{errors.businessRegistrationDoc}</p>
        )}

        {/* -------- 제출 버튼 -------- */}
        <button type="submit">회원가입</button>

        {successMessage && <p className="success">{successMessage}</p>}
        {errors.general && <p className="error">{errors.general}</p>}
      </form>

      <p className="login-link">
        이미 계정이 있으신가요? <a href="/login">로그인하기</a>
      </p>
    </div>
  );
}

export default SignupPage;

