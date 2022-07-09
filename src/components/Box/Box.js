import React from 'react';

export default function Box() {
    return (
        <div className="three_box">
            <div className="container">
                <div className="row">
                    <div className="col-md-4">
                        <div className="box_text">
                            <i>
                                <img src="images/box1.png" alt="#" />
                            </i>
                            <h3>Giá thành</h3>
                            <p>
                                Được sản xuất và phân phối chính hãng tại Việt Nam nên giá thành sản phẩm luôn luôn tốt
                                cho mọi người dùng
                            </p>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="box_text">
                            <i>
                                <img src="images/box2.png" alt="#" />
                            </i>
                            <h3>Phục vụ</h3>
                            <p>
                                Tự tin là một trong những shop có chất lượng phục vụ online tốt nhất ! Hỗ trợ tư vấn
                                24/7 trừ chủ nhật và ngày lễ
                            </p>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="box_text">
                            <i>
                                <img src="images/box3.png" alt="#" />
                            </i>
                            <h3>Đổi trả</h3>
                            <p>
                                Khi mua hàng tại shop mà không ưng ý có thể đổi trả trong vòng 3-7 ngày và sẽ được giao
                                liền những ngày sau đó
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
