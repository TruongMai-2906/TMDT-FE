import React, { useEffect, useState } from 'react';
import Introduce from '~/components/Banner/Introduce';
import { Row, Col } from 'antd';
import SlideBar from '~/components/Products/SlideBar/SlideBar';
import ProductItem from '~/components/Products/ProductItem/ProductItem';
import Paging from '~/components/Paging/Paging';
import { methodGet } from '~/Utils/Request';
import { Select } from 'antd';
import { useLocation, useNavigate } from 'react-router-dom';
import { NotifyError } from '~/Utils/Notice';
const { Option } = Select;
export default function Product() {
    const location = useLocation();
    const navigate = useNavigate();

    const [pageIndex, setPageIndex] = useState({
        page: 1,
        sizetotalPage: 80,
        currentPage: 1,
        urlOriginal: '/product/products?',
        urlMain: '/product/products?',
        category: '',
        sort: '',
    });
    console.log('pageIndex', pageIndex);
    const [listProduct, setListProduct] = useState([]);

    const ChangeStateComponentCha = (e) => {
        setPageIndex(e);
    };
    const handleChangePage = (pagenek) => {
        console.log('page', pagenek);
        setPageIndex({
            ...pageIndex,
            page: pagenek,
            currentPage: pagenek,
        });
    };

    const onChange = (value) => {
        if (pageIndex?.urlMain.includes(value)) {
            NotifyError('gì z cha');
        } else {
            console.log('value la', value);
            const url = `${pageIndex?.urlMain}${pageIndex.category}${value}&page=${pageIndex.page}`;
            console.log('url increase', url);
            navigate(url);
            setPageIndex({
                ...pageIndex,
                sort: value,
            });
        }
    };

    useEffect(() => {
        const getListProduct = async () => {
            console.log('location trc khi get API là', location?.search);
            const url = `${pageIndex.urlMain}${pageIndex.category}${pageIndex.sort}&page=${pageIndex.page}`;
            const getProduct = await methodGet(url).catch((e) => {
                console.log('Lỗi get product');
            });
            if (getProduct?.data) {
                console.log('list product', getProduct?.data);
                setListProduct(getProduct?.data?.productDTOList);
                setPageIndex({
                    ...pageIndex,
                    sizetotalPage: getProduct?.data?.sizeTotal * 10,
                });
                navigate(url);
            }
        };
        getListProduct();
    }, [pageIndex.page, pageIndex.urlMain, pageIndex.sort]);
    return (
        <div>
            <Introduce title="Trang chủ" body={'Trang chủ / Danh sách sản phẩm'} />
            <div className="container">
                <Row>
                    <Col xs={24} sm={8} md={8} lg={8} xxl={8}>
                        <SlideBar setChangeUrl={ChangeStateComponentCha} pageIndex={pageIndex} />
                    </Col>

                    <Col xs={24} m={16} md={16} lg={16} xxl={16}>
                        <Select placeholder="Lọc theo tiêu chí ?" onChange={onChange} style={{ marginBottom: '10px' }}>
                            <Option value="&sort=price&order=ASC">Theo giá tăng dần +</Option>
                            <Option value="&sort=price&order=DESC">Theo giá giảm dần -</Option>
                            <Option value="nam">Dành cho nam $</Option>
                            <Option value="nu">Dành cho nữ *</Option>
                        </Select>
                        <Row className="product-list">
                            {listProduct?.map((item, index) => {
                                return (
                                    <Col key={index} xs={11} m={12} md={7} lg={7} xxl={7} className="product-container">
                                        <ProductItem
                                            id={item.id}
                                            img={item.image}
                                            name={item.name}
                                            price={item.price}
                                            priceSale={item?.price_sale}
                                        />
                                    </Col>
                                );
                            })}

                            <Col span={24}>
                                <Paging
                                    current={pageIndex.currentPage}
                                    handleChange={handleChangePage}
                                    size={pageIndex?.sizetotalPage}
                                />
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </div>
            {console.log('product neeee')}
        </div>
    );
}
