import React from 'react';
import './ProductItem.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAdd, faStar } from '@fortawesome/free-solid-svg-icons';
import { Button } from 'antd';
import { useNavigate } from 'react-router-dom';
export default function ProductItem({ score, id, img, name }) {
    const navigate = useNavigate();
    const handleOnClick = () => {
        if (id) {
            navigate(`/product-detail/${id}`);
        }
    };

    return (
        <div className="swiper-item">
            <div className="swiper-item-img">
                <img src={img} alt='"Ảnh sản phẩm loading....' />
                <div className="swiper-item-other">
                    <div className="swiper-item-other-add">
                        <Button danger icon={<FontAwesomeIcon icon={faAdd} />}>
                            Thêm vào giỏ hàng
                        </Button>
                    </div>
                    <div className="swiper-item-other-watch">
                        <Button type="primary" onClick={handleOnClick}>
                            Xem chi tiết
                        </Button>
                    </div>
                </div>
            </div>
            <div className="swiper-item-body">
                <div className="title">
                    <h5 className="text-item product__text-name">{name}</h5>
                    <div className="product-item-content-container">
                        <h3 className="price-old text-item">30900đ</h3>
                        <h2 className="text-item price-sale">29900đ</h2>
                    </div>
                </div>
                <div className="love">
                    <div className="vote text-item">
                        {}
                        <FontAwesomeIcon icon={faStar} color="green" />
                        <FontAwesomeIcon icon={faStar} color="green" />
                        <FontAwesomeIcon icon={faStar} color="green" />
                        <FontAwesomeIcon icon={faStar} />
                        <FontAwesomeIcon icon={faStar} />
                    </div>
                </div>
            </div>
        </div>
    );
}
