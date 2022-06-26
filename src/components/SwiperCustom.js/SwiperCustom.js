import React, { useState, useEffect } from 'react';
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
import { methodGet } from '~/Utils/Request';
export default function SwiperCustom({ children }) {
    const [listproduct, setListProduct] = useState();
    useEffect(() => {
        const number = Math.floor(Math.random() * 3) + 1;
        const getData = async () => {
            const rs = await methodGet(`/product/getListProduct?type=mu&pageIndex=${number}`);
            console.log('swieper api', rs?.data);
            setListProduct(rs?.data);
        };

        getData();
    }, []);
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
                {listproduct &&
                    listproduct?.productDTOList?.map((item, index) => {
                        return (
                            <SwiperSlide key={index}>
                                <ProductItem
                                    id={item?.id}
                                    img={item?.image}
                                    name={item?.name}
                                    score={item?.score}
                                    price={item?.price}
                                    priceSale={item?.price_sale}
                                />
                            </SwiperSlide>
                        );
                    })}
                {/* <SwiperSlide>
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
                </SwiperSlide> */}
            </Swiper>
        </div>
    );
}
