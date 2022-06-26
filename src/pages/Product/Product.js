import React, { useEffect, useState } from 'react';
import Introduce from '~/components/Banner/Introduce';
import { Row, Col } from 'antd';
import SlideBar from '~/components/Products/SlideBar/SlideBar';
import ProductItem from '~/components/Products/ProductItem/ProductItem';
import Paging from '~/components/Paging/Paging';
import { methodGet } from '~/Utils/Request';
import { Select } from 'antd';
const { Option } = Select;
export default function Product() {
    const [pageIndex, setPageIndex] = useState({
        page: 1,
        sizetotalPage: 80,
        currentPage: 1,
    });
    const handleChangePage = (pagenek) => {
        console.log('page', pagenek);
        setPageIndex({
            ...pageIndex,
            page: pagenek,
            currentPage: pagenek,
        });
    };
    console.log('page index', pageIndex);
    const [listProduct, setListProduct] = useState([]);
    const onChange = (value) => {
        console.log(`selected ${value}`);
    };
    useEffect(() => {
        const getListProduct = async () => {
            const url = `/product/getListProduct?type=mu&pageIndex=${pageIndex.page}`;
            console.log('url là', url);
            const getProduct = await methodGet(url).catch((e) => {
                console.log('Lỗi get product');
            });

            console.log('list product', getProduct?.data);
            setListProduct(getProduct?.data?.productDTOList);
            setPageIndex({
                ...pageIndex,
                sizetotalPage: getProduct?.data?.sizeTotal * 10,
            });
            // setListProduct(getProduct?.data);
        };
        getListProduct();
    }, [pageIndex.page]);
    return (
        <div>
            <Introduce title="Trang chủ" body={'Trang chủ / Danh sách sản phẩm'} />
            <div className="container">
                <Row>
                    <Col xs={24} sm={8} md={8} lg={8} xxl={8}>
                        <SlideBar />
                    </Col>

                    <Col xs={24} m={16} md={16} lg={16} xxl={16}>
                        <Select placeholder="Lọc theo tiêu chí ?" onChange={onChange} style={{ marginBottom: '10px' }}>
                            <Option value="increase">Theo giá tăng dần +</Option>
                            <Option value="decrease">Theo giá giảm dần -</Option>
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
