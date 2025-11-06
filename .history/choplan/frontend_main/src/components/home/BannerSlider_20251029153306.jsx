import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import { Autoplay, Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/pagination";
import "../../styles/BannerSlider.css";

const images = [
  "/images/banner4.jpg",
  "/images/banner2.jpg",
  "/images/banner3.jpg"
];

export default function BannerSlider() {
  return (
    <div className="banner-slider">
      <Swiper
        modules={[Autoplay, Pagination]}
        slidesPerView={1.15} // 중앙 메인 + 양 옆 일부 노출
        centeredSlides={true}
        spaceBetween={20}
        loop={true}
        autoplay={{ delay: 3000, disableOnInteraction: false }}
        pagination={{ clickable: true }}
        className="custom-swiper"
      >
        {images.map((src, index) => (
          <SwiperSlide key={index} className="banner-slide">
            <img src={src} alt={`배너 ${index + 1}`} />
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}
