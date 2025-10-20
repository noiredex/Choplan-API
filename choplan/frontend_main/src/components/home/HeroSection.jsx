import React, { useState, useEffect } from "react";
import "../../styles/HeroSection.css";

export default function HeroSection() {
  const banners = ["/assets/banner1.jpg", "/assets/banner2.jpg", "/assets/banner3.jpg"];
  const [index, setIndex] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setIndex((prev) => (prev + 1) % banners.length);
    }, 3000);
    return () => clearInterval(timer);
  }, []);

  return (
    <section className="hero-section">
      <img src={banners[index]} alt="이벤트 배너" className="hero-banner" />
    </section>
  );
}
