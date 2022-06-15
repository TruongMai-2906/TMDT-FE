import React from 'react';
import Banner from '~/components/Banner/Banner';
import Info from '~/components/Banner/Info';
import Sale from '~/components/Banner/Sale';
import Box from '~/components/Box/Box';
import Contact from '~/components/Contact/Contact';
import CustomerReview from '~/components/CustomerReview/CustomerReview';
import Footer from '~/components/Footer/Footer';
import Header from '~/components/Header/Header';
export default function Home() {
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
                                    <div className="col-md-4 margin_bottom1">
                                        <div className="product_box">
                                            <figure>
                                                <img src="images/product1.png" alt="#" />
                                            </figure>
                                            <h3>Computer</h3>
                                        </div>
                                    </div>
                                    <div className="col-md-4 margin_bottom1">
                                        <div className="product_box">
                                            <figure>
                                                <img src="images/product2.png" alt="#" />
                                            </figure>
                                            <h3>Laptop</h3>
                                        </div>
                                    </div>
                                    <div className="col-md-4 margin_bottom1">
                                        <div className="product_box">
                                            <figure>
                                                <img src="images/product3.png" alt="#" />
                                            </figure>
                                            <h3>Tablet</h3>
                                        </div>
                                    </div>
                                    <div className="col-md-4 margin_bottom1">
                                        <div className="product_box">
                                            <figure>
                                                <img src="images/product4.png" alt="#" />
                                            </figure>
                                            <h3>Speakers</h3>
                                        </div>
                                    </div>
                                    <div className="col-md-4 margin_bottom1">
                                        <div className="product_box">
                                            <figure>
                                                <img src="images/product5.png" alt="#" />
                                            </figure>
                                            <h3>internet</h3>
                                        </div>
                                    </div>
                                    <div className="col-md-4 margin_bottom1">
                                        <div className="product_box">
                                            <figure>
                                                <img src="images/product6.png" alt="#" />
                                            </figure>
                                            <h3>Hardisk</h3>
                                        </div>
                                    </div>
                                    <div className="col-md-4">
                                        <div className="product_box">
                                            <figure>
                                                <img src="images/product7.png" alt="#" />
                                            </figure>
                                            <h3>Rams</h3>
                                        </div>
                                    </div>
                                    <div className="col-md-4">
                                        <div className="product_box">
                                            <figure>
                                                <img src="images/product8.png" alt="#" />
                                            </figure>
                                            <h3>Bettery</h3>
                                        </div>
                                    </div>
                                    <div className="col-md-4">
                                        <div className="product_box">
                                            <figure>
                                                <img src="images/product9.png" alt="#" />
                                            </figure>
                                            <h3>Drive</h3>
                                        </div>
                                    </div>
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
            <Info title="Tưng bừng khai trương" img="images/pc.png" body="Giảm giá cực sốc" />
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
