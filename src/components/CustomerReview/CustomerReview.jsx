import React from 'react';

export default function CustomerReview() {
    return (
        <div className="customer">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <div className="titlepage">
                            <h2>Nhận xét của khách hàng</h2>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-12">
                        <div id="myCarousel" className="carousel slide customer_Carousel " data-ride="carousel">
                            <ol className="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" className="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li>
                            </ol>
                            <div className="carousel-inner">
                                <div className="carousel-item active">
                                    <div className="container">
                                        <div className="carousel-caption ">
                                            <div className="row">
                                                <div className="col-md-9 offset-md-3">
                                                    <div className="test_box">
                                                        <i>
                                                            <img src="images/cos.png" alt="#" />
                                                        </i>
                                                        <h4>Hoàng Văn Hiệp</h4>
                                                        <p>
                                                            Tôi chưa từng trải qua cảm giác thích thú và hưng phấn đến
                                                            vậy khi mua hàng Online Shop làm ăn rất nhiệt tình và điều
                                                            quan trọng đó là giá cả rất phải chăng , hợp với tui tiền
                                                            của tôi Cho hẳn đánh giá 9/10 , quá ổn áp sẽ còn gặp shop
                                                            dài dài
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="carousel-item">
                                    <div className="container">
                                        <div className="carousel-caption">
                                            <div className="row">
                                                <div className="col-md-9 offset-md-3">
                                                    <div className="test_box">
                                                        <i>
                                                            <img src="images/cos.png" alt="#" />
                                                        </i>
                                                        <h4>Nguyễn Hoàng Minh</h4>
                                                        <p>
                                                            Là một tín đồ của thời trang , tôi đã tìm thấy những bộ
                                                            trang phục đẹp mắt và giá thành cực kì bình dân làm tôi thấy
                                                            rất vui , sẽ là một khách hàng lâu dài của bên shop , tặng
                                                            shop 10/10 sao luôn
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="carousel-item">
                                    <div className="container">
                                        <div className="carousel-caption">
                                            <div className="row">
                                                <div className="col-md-9 offset-md-3">
                                                    <div className="test_box">
                                                        <i>
                                                            <img src="images/cos.png" alt="#" />
                                                        </i>
                                                        <h4>Mr Bế Lâm</h4>
                                                        <p>
                                                            Tôi là một người khó tính nhưng sau khi biết đến shop thì
                                                            tôi không còn lời gì để nói nữa từ cách phục vụ đến chăm sóc
                                                            khách hàng , giá cả ko quan trọng đối với tôi bởi vì tôi
                                                            giàu nhưng cách phục vụ bên shop phải làm cho người sử dụng
                                                            mê mẩn , cho hẳn 11/10 oke luôn =)))
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a
                                className="carousel-control-prev"
                                href="https://learn-tech-tips.blogspot.com/"
                                role="button"
                                data-slide="prev"
                            >
                                <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span className="sr-only">Previous</span>
                            </a>
                            <a
                                className="carousel-control-next"
                                href="https://learn-tech-tips.blogspot.com/"
                                role="button"
                                data-slide="next"
                            >
                                <span className="carousel-control-next-icon" aria-hidden="true"></span>
                                <span className="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
