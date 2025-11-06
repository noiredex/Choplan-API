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
      img: "/imges/banner4.jpg",
      alt: "추천 매장 1",
    },
    {
      id: 2,
      img: "/images/banner2.jpg",
      alt: "추천 매장 2",
    },
    {
      id: 3,
      img: "/images/banner3.jpg",
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
