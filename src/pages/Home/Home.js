
import Banner from '~/components/Banner/Banner';
import Info from '~/components/Banner/Info';
import Sale from '~/components/Banner/Sale';
import Box from '~/components/Box/Box';
import Contact from '~/components/Contact/Contact';
import CustomerReview from '~/components/CustomerReview/CustomerReview';
import Footer from '~/components/Footer/Footer';
import Header from '~/components/Header/Header';
import React, { useEffect, useState } from 'react';
import { methodGet } from '~/Utils/Request';
export default function Home() {

    const [listHome,setListHome]=useState([]);
    useEffect(() => {
        const getdata = async () => {
            const rs = await methodGet(`/product/listProductHome`).catch((e) => {
                console.log('lỗi search');
            });
        
            setListHome(rs.data)
          
        };
        getdata();
    }, []);
    console.log(listHome)
    return (
        // <div className="App">
        <div>
            {/* <div className="loader_bg">
                    <div className="loader">
                        <img src="images/loading.gif" alt="#" />
                    </div>
                </div> */}
            {/* <Header /> */}
            {/* <!-- banner --> */}
            <Banner />
            {/* <!-- end banner --> */}
            {/* <!-- three_box --> */}
            <Box />
            {/* <!-- three_box --> */}
            {/* <!-- products --> */}
            <div className="products">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="titlepage">
                                <h2>Sản phẩm nổi bật</h2>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-12">
                            <div className="our_products">
                                <div className="row">
                                {
                                    listHome.map((item,index)=>{
                                        return   <div key={index} className="col-md-4">
                                                    <div className="product_box">
                                                        <figure>
                                                            <img src={item.product.image} style={{width:"200px",height:"200px"}} alt="#" />
                                                        </figure>
                                                        <h3>{item.categoryName}</h3>
                                                    </div>
                                                </div>
                                    })
                                }
                                  
                                    <div className="col-md-12">
                                        <a className="read_more" href="/#">
                                            Xem thêm
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* <!-- end products --> */}
            {/* <!-- laptop  section --> */}
            <Info title="Tưng bừng khai trương" img="images/bannerBottom.png" body="Giảm giá cực sốc" />
            {/* <!-- end laptop  section --> */}
            {/* <!-- customer --> */}
            <CustomerReview />
            {/* <!-- end customer --> */}
            {/* <!--  contact --> */}
            <Contact />
            {/* <!-- end contact --> */}
            {/* <!--  footer --> */}
            <Footer />
            {/* <!-- end footer --> */}
            {/* <!-- Javascript files--> */}
        </div>
        // </div>
    );
}
