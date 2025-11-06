import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import { Autoplay, Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/pagination";
import "../../styles/BannerSlider.css";

const imagers = [
  "/images/banner4.jpg",
  "/images/banner2.jpg",
  "/images/banner3.jpg"
];

export default function BannerSlider() {
  return (
    <div className="banner-slider">
      <Swiper
      modules={[Autoplay, Pagination]}
      slidesPerView={1.15} // 한 화면에 슬라이드 1개 + 양쪽 약간 보이게
      centeredSlides={true} // 슬라이드 중앙 정렬
      spaceBetween={20} // 슬라이드 간격
      loop={true}
      autoplay={{ delay: 3000, disableOnInteraction: flase }}
      pagintion={{ clickable: true}}
      >
        {imagers.map((src, index) => (
          <SwiperSlide key={index} className="banner-slide">
            <img src={src} alt={`배너 ${index + 1}`} />
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}