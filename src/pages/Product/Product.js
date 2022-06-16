import React, { useEffect, useState } from 'react';
import Introduce from '~/components/Banner/Introduce';
import { Row, Col } from 'antd';
import SlideBar from '~/components/Products/SlideBar/SlideBar';
import ProductItem from '~/components/Products/ProductItem/ProductItem';
import Paging from '~/components/Paging/Paging';
import { methodGet } from '~/Utils/Request';
export default function Product() {
    const [pageIndex, setPageIndex] = useState({
        page: 2,
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

    useEffect(() => {
        const getListProduct = async () => {
            const url = `/product/getListProduct?type=mu&pageIndex=1`;
            console.log('url là', url);
            const getProduct = await methodGet(url).catch((e) => {
                console.log('Lỗi get product');
            });

            console.log('list product', getProduct?.data?.productDTOList);
            setListProduct(getProduct?.data?.productDTOList);
            setPageIndex({
                ...pageIndex,
                sizetotalPage: getProduct?.data?.sizTotal * 10,
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
                    <Col xs={8} sm={8} md={8} lg={8} xxl={8}>
                        <SlideBar />
                    </Col>

                    <Col xs={16} m={16} md={16} lg={16} xxl={16}>
                        <Row gutter={20}>
                            {console.log('size list product', listProduct)}
                            {listProduct?.map((item, index) => {
                                return (
                                    <Col key={index} xs={12} m={12} md={8} lg={8} xxl={8} className="product-container">
                                        <ProductItem id={item.id} img={item.image} name={item.name} />
                                    </Col>
                                );
                            })}

                            <Col span={24}>
                                <Paging
                                    current={pageIndex.currentPage}
                                    handleChange={handleChangePage}
                                    size={pageIndex.sizetotalPage}
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
