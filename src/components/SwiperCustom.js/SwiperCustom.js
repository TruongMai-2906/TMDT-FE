import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
// import './SwiperCustom.scss';
import { Pagination, Navigation } from 'swiper';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faBriefcaseClock,
    faFaceAngry,
    faFaceDizzy,
    faFaceGrinHearts,
    faFaceGrinTears,
    faHeart,
    faStar,
} from '@fortawesome/free-solid-svg-icons';
import ProductItem from '../Products/ProductItem/ProductItem';
export default function SwiperCustom({ children }) {
    return (
        <div>
            <Swiper
                slidesPerView={4}
                spaceBetween={30}
                slidesPerGroup={1}
                loop={true}
                loopFillGroupWithBlank={true}
                // onPlay={true}
                // pagination={{
                //     clickable: false,
                // }}
                navigation={true}
                modules={[Pagination, Navigation]}
                className="mySwiper"
            >
                <SwiperSlide>
                    <ProductItem />
                </SwiperSlide>
                <SwiperSlide>
                    <ProductItem />
                </SwiperSlide>
                <SwiperSlide>
                    <ProductItem />
                </SwiperSlide>
                <SwiperSlide>
                    <ProductItem />
                </SwiperSlide>
                <SwiperSlide>
                    <ProductItem />
                </SwiperSlide>
                <SwiperSlide>
                    <ProductItem />
                </SwiperSlide>
                <SwiperSlide>
                    <ProductItem />
                </SwiperSlide>
                <SwiperSlide>
                    <ProductItem />
                </SwiperSlide>
                <SwiperSlide>
                    <ProductItem />
                </SwiperSlide>
            </Swiper>
        </div>
    );
}
