import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import { Autoplay, Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/pagination";
import "../../styles/BannerSlider.css";

export default function BannerSlider() {
  const banners = [
    {
      id: 1,
      img: "https://placehold.co/1200x400/007bff/ffffff?text=오늘의+추천매장+1",
      alt: "추천 매장 1",
    },
    {
      id: 2,
      img: "https://placehold.co/1200x400/f07b00/ffffff?text=오늘의+추천매장+2",
      alt: "추천 매장 2",
    },
    {
      id: 3,
      img: "https://placehold.co/1200x400/008c4a/ffffff?text=오늘의+추천매장+3",
      alt: "추천 매장 3",
    },
  ];

  return (
    <div className="banner-slider-container">
      <Swiper
        modules={[Autoplay, Pagination]}
        pagination={{ clickable: true }}
        autoplay={{ delay: 4000, disableOnInteraction: false }}
        loop={true}
        spaceBetween={0}
        slidesPerView={1}
        className="banner-swiper"
      >
        {banners.map((banner) => (
          <SwiperSlide key={banner.id}>
            <img
              src={banner.img}
              alt={banner.alt}
              className="banner-image"
            />
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}
