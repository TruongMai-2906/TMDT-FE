import { Row, Col } from 'antd';
import React from 'react';
import Introduce from '~/components/Banner/Introduce';
import ProductItem from '~/components/Products/ProductItem/ProductItem';
import { useLocation } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import Paging from '~/components/Paging/Paging';
export default function Search() {
    const param = useParams();
    console.log('param', param);
    const location = useLocation();
    console.log('location ', location);

    return (
        <>
            <Introduce title="Trang chủ" body="Trang chủ / Tìm kiếm sản phẩm" />
            <div className="container">
                <Row gutter={16}>
                    <Col xs={12} m={12} md={8} lg={8} xxl={8}>
                        <ProductItem />
                    </Col>
                    <Col xs={12} m={12} md={8} lg={8} xxl={8}>
                        <ProductItem />
                    </Col>
                    <Col xs={12} m={12} md={8} lg={8} xxl={8}>
                        <ProductItem />
                    </Col>
                    <Col xs={12} m={12} md={8} lg={8} xxl={8}>
                        <ProductItem />
                    </Col>
                    <Col xs={12} m={12} md={8} lg={8} xxl={8}>
                        <ProductItem />
                    </Col>
                    <Col xs={12} m={12} md={8} lg={8} xxl={8}>
                        <ProductItem />
                    </Col>
                    <Col xs={12} m={12} md={8} lg={8} xxl={8}>
                        <ProductItem />
                    </Col>
                    <Col xs={12} m={12} md={8} lg={8} xxl={8}>
                        <ProductItem />
                    </Col>
                    <Col xs={12} m={12} md={8} lg={8} xxl={8}>
                        <ProductItem />
                    </Col>
                    <Col span={24}>
                        <Paging size={80} />
                    </Col>
                </Row>
            </div>
        </>
    );
}
