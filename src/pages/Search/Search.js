import { Row, Col } from 'antd';
import React, { useEffect, useState } from 'react';
import Introduce from '~/components/Banner/Introduce';
import ProductItem from '~/components/Products/ProductItem/ProductItem';
import { useLocation, useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import Paging from '~/components/Paging/Paging';
import { methodGet } from '~/Utils/Request';
export default function Search() {
    const [listKetqua, SetListKetQua] = useState({});
    const param = useParams();
    const navigate = useNavigate();
    const keywork = param.keywork;
    console.log('keywork', keywork);
    const [pageIndex, setPageIndex] = useState({
        page: 1,
        sizetotalPage: 20,
        currentPage: 1,
    });
    const handleChangePage = (pagenek) => {
        console.log('page', pagenek);
        setPageIndex({
            ...pageIndex,
            page: pagenek,
            currentPage: pagenek,
        });
        // navigate(`/search/products?name=${keywork}&page=${pageIndex.page}`);
    };

    useEffect(() => {
        const getdata = async () => {
            const rs = await methodGet(`/product/products?name=${keywork}&page=${pageIndex.page}`).catch((e) => {
                console.log('lỗi search');
            });
            console.log('ket qua search', rs?.data);
            if (rs?.data) {
                SetListKetQua(rs.data);
                setPageIndex({
                    ...pageIndex,
                    sizetotalPage: rs?.data?.sizeTotal * 10,
                });
            } else {
            }
        };
        getdata();
    }, [pageIndex.page, param.keywork]);
    return (
        <>
            <Introduce title="Trang chủ" body="Trang chủ / Tìm kiếm sản phẩm" />
            <div className="container">
                <Row gutter={16}>
                    {listKetqua ? (
                        listKetqua?.productDTOList?.map((item, index) => {
                            return (
                                <Col key={index} xs={12} m={12} md={8} lg={8} xxl={8}>
                                    <ProductItem
                                        id={item?.id}
                                        img={item?.image}
                                        name={item?.name}
                                        score={item?.score}
                                        price={item?.price}
                                        priceSale={item?.price_sale}
                                    />
                                </Col>
                            );
                        })
                    ) : (
                        <h1 style={{ textAlign: 'center' }}>Không có kết quả phù hợp ,vui lòng tìm kiếm lại</h1>
                    )}

                    <Col span={24}>
                        <Paging
                            current={pageIndex?.currentPage}
                            handleChange={handleChangePage}
                            size={pageIndex?.sizetotalPage}
                        />
                    </Col>
                </Row>
            </div>
        </>
    );
}
