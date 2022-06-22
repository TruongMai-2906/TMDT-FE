import React, { useEffect, useRef, useState } from 'react';
import { Col, Divider, Form, InputNumber, Rate, Row, Space } from 'antd';

import '~/pages/ProductDetail/ProductDetail.scss';
import Introduce from '~/components/Banner/Introduce';
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
import { Radio, Button } from 'antd';
import Mgtop from '~/components/MgTop/Mgtop';
import { Link, Routes, Route, useLocation, useParams } from 'react-router-dom';
import MoreInformation from '~/components/ProductDetail/MoreInformation';
import Vote from '~/components/ProductDetail/Vote';
import Description from '~/components/ProductDetail/Description';
import SwiperCustom from '~/components/SwiperCustom.js/SwiperCustom';
import Avatar from '~/components/Avatar/Avatar';
import Footer from '~/components/Footer/Footer';
import { methodGet, methodPost } from '~/Utils/Request';
import { NotifyError, NotifySuccess } from '~/Utils/Notice';
export default function ProductDetail() {
    const params = useParams();
    const [productDetail, SetProductDetail] = useState({});
    console.log('location là ', params);
    const onFinish = async (values) => {
        console.log(values);
        const dataPost = {
            productid: 1,
            quantity: 1,
        };
        const rs = await methodPost('/cart/add', dataPost).catch((e) => {
            NotifyError(e);
        });
        console.log('data add product trả về', rs);
        if (rs?.data) {
            NotifySuccess('Thêm vào giỏ hàng thành công');
        } else {
            const mesage = rs?.data?.mesage;
            NotifyError('Thêm vào giỏ hàng thất bại =>>' + mesage);
        }
    };
    const initialValue = {
        amount: params?.id,
        mausac: productDetail?.color,
    };

    const handleAddCart = async () => {};
    useEffect(() => {
        const getProductDetail = async () => {
            const { id } = params;
            console.log('id lấy ra là', id);
            const rs = await methodGet(`/product/detail/${id}`).catch((e) => {
                console.log('get product detail failed');
            });
            console.log('kq get api get product detail la', rs.data);
            SetProductDetail(rs?.data);
        };
        getProductDetail();
    }, []);

    const [renderComponent, setRenderComponent] = useState();
    const [active, setActive] = useState(1);
    return (
        <>
            <Introduce title="Chi tiết sản phẩm" body="Trang chủ / Chi tiết sản phẩm" />
            <div className="container">
                <Row gutter={5}>
                    <Col xs={24} sm={24} md={10} lg={10} xxl={10}>
                        <div className="img-product">
                            <img src="/images/about.jpg" alt="Image Product Load Failed" />
                        </div>
                    </Col>
                    <Col xs={24} sm={24} md={14} lg={14} xxl={14}>
                        <Form onFinish={onFinish} initialValues={initialValue}>
                            <Space direction="vertical" size="small">
                                <div className="product_detail">
                                    <h1 className="title">
                                        {productDetail?.name} <span className="Price">{productDetail?.price}k</span>
                                    </h1>
                                    <div className="vote">
                                        <FontAwesomeIcon icon={faStar} />
                                        <FontAwesomeIcon icon={faStar} />
                                        <FontAwesomeIcon icon={faStar} />
                                        <FontAwesomeIcon icon={faStar} />
                                        <FontAwesomeIcon icon={faStar} />
                                    </div>
                                    <div className="stt">
                                        <p className="">
                                            Tình trạng : <span className="status">Còn hàng</span>
                                        </p>
                                    </div>
                                    {/* <Form onFinish={onFinish} initialValues={initialValue}> */}
                                    <Space size="large">
                                        <Form.Item name="amount" rules={[{}]}>
                                            <Form.Item name="mausac" label="Màu sắc">
                                                <Radio.Group defaultValue={productDetail?.color}>
                                                    <Radio checked={true} value={productDetail?.color}>
                                                        {productDetail?.color}
                                                    </Radio>
                                                </Radio.Group>
                                            </Form.Item>
                                        </Form.Item>
                                    </Space>
                                    <Form.Item wrapperCol={{ span: 1, offset: 2 }}>
                                        <Button type="danger" htmlType="submit">
                                            Thêm vào giỏ hàng{' '}
                                            <FontAwesomeIcon style={{ marginLeft: '10px' }} icon={faHeart} />
                                        </Button>
                                    </Form.Item>
                                    <div className="product_social">
                                        <span className="title">Chia sẻ </span>
                                        <div className="product_social_icon">
                                            <FontAwesomeIcon icon={faBriefcaseClock} />
                                            <FontAwesomeIcon icon={faFaceAngry} />
                                            <FontAwesomeIcon icon={faFaceGrinHearts} />
                                            <FontAwesomeIcon icon={faFaceGrinTears} />
                                        </div>
                                    </div>
                                </div>
                            </Space>
                        </Form>
                    </Col>
                </Row>
                <Divider style={{ backgroundColor: '#c3c3c3' }}></Divider>
                <div className="menu-detail">
                    {/* <Link to="/product_detail/themthongtin">Thêm thông tin</Link>
                    <Link to="/product_detail/mieuta">Miêu tả</Link>
                    <Link to="/product_detail/danhgia">Đánh giá</Link> */}
                    <div
                        className={`menu-detail-item ${active === 1 ? 'active' : ''}`}
                        onClick={() => {
                            setRenderComponent(<MoreInformation />);
                            setActive(1);
                        }}
                    >
                        Thêm thông tin
                    </div>
                    <div
                        className={`menu-detail-item ${active === 2 ? 'active' : ''}`}
                        onClick={() => {
                            setRenderComponent(<Description description={productDetail.description} />);
                            setActive(2);
                        }}
                    >
                        Miêu tả
                    </div>
                    <div
                        className={`menu-detail-item ${active === 3 ? 'active' : ''}`}
                        onClick={() => {
                            setRenderComponent(<Vote />);
                            setActive(3);
                        }}
                    >
                        Đánh giá
                    </div>
                </div>
                <Row className="more-detail-container">
                    <Col xs={24} sm={24} md={24} lg={24}></Col>
                    {renderComponent}
                    {/* <Routes>
                        <Route path="/product_detail/themthongtin" element={<MoreInformation />}></Route>
                        <Route path="/product_detail/mieuta" element={<Description />}></Route>
                        <Route path="/product_detail/danhgia" element={<Vote />}></Route>
                    </Routes> */}
                </Row>
                <Row>
                    <Col>
                        <div className="relative">
                            <h1 className="relative-title">Sản phẩm liên quan</h1>

                            <SwiperCustom />
                        </div>
                    </Col>
                </Row>
                <Mgtop />
                <Row gutter={10}>
                    <Col xs={8} sm={8} md={8} lg={4} xxl={4}>
                        <Avatar src="./images/about.jpg" />
                    </Col>
                    <Col xs={8} sm={8} md={8} lg={4} xxl={4}>
                        <Avatar src="./images/about.jpg" />
                    </Col>
                    <Col xs={8} sm={8} md={8} lg={4} xxl={4}>
                        <Avatar src="./images/about.jpg" />
                    </Col>
                    <Col xs={8} sm={8} md={8} lg={4} xxl={4}>
                        <Avatar src="./images/about.jpg" />
                    </Col>
                    <Col xs={8} sm={8} md={8} lg={4} xxl={4}>
                        <Avatar src="./images/about.jpg" />
                    </Col>
                    <Col xs={8} sm={8} md={8} lg={4} xxl={4}>
                        <Avatar src="./images/about.jpg" />
                    </Col>
                </Row>
            </div>
            <Mgtop />
            <Footer />
        </>
    );
}
