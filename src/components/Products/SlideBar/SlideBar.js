import React, { useState } from 'react';
import './SlideBar.scss';
export default function SlideBar() {

    const [category, setCategory] = useState([
        {
            key: "ao",
            value: "Áo",
        },
        {
            key: "quan",
            value: "Quần",
        },
        {
            key: "mu",
            value: "Mũ",
        },
        {
            key: "giay",
            value: "Giày",
        },
        {
            key: "khac",
            value: "Khác",
        },
    ])

    const onSelectCategogy = (key) => {
        //todo: key per cat for agrument to call api
    }

    return (
        <div className="slidebar">
            <h2 className="slidebar-title">Tổng quan</h2>
            <div id="shop-cate-toggle" className="category-menu sidebar-menu sidbar-style">
                <ul>
                    <li className="has-sub">
                        <a href="https://www.facebook.com/">Thể loại</a>
                        <ul className="category-sub">
                            {category && category.map((cat, index) => 
                                <li>
                                    <div key={`cat-${index}`} href="https://www.facebook.com/" onClick={onSelectCategogy(cat.key)}>{cat.value}</div>
                                </li>
                            )}
                        </ul>
                        {/* <!-- category submenu end--> */}
                    </li>
                    <li className="has-sub">
                        <a href="https://www.facebook.com/">Dành cho Nam</a>
                        <ul className="category-sub">
                            <li>
                                <a href="https://www.facebook.com/">Áo ( Len, Khoác,Phông ,Sơ mi)</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">Quần(Kaki, Jean)</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">Combo(Ngẫu nhiên)</a>
                            </li>
                        </ul>
                        {/* <!-- category submenu end--> */}
                    </li>
                    <li className="has-sub">
                        <a href="https://www.facebook.com/">Dành cho nữ</a>
                        <ul className="category-sub">
                            <li>
                                <a href="https://www.facebook.com/">Áo( Ni,Khoác,Len )</a>
                            </li>
                              <li>
                                <a href="https://www.facebook.com/">Quần</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">Váy Liền</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">Combo ngẫu nhiên</a>
                            </li>
                        </ul>
                        {/* <!-- category submenu end--> */}
                    </li>
                    <li className="has-sub">
                        <a href="https://www.facebook.com/">Dịch Vụ</a>
                        <ul className="category-sub">
                            <li>
                                <a href="https://www.facebook.com/">Cho Vay Trả Góp</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">Đăng Kí Bảo Hiểm Xe</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">Làm Biển Số Theo Tỉnh</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">:D</a>
                            </li>
                        </ul>
                        {/* <!-- category submenu end--> */}
                    </li>
                    <li className="has-sub">
                        <a href="https://www.facebook.com/">Tin Tức Khuyến Mãi</a>
                        <ul className="category-sub">
                            <li>
                                <a href="https://www.facebook.com/">Khuyến Mãi Mới Nhất</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">Sale 30% Nhân Sự Kiện 20/10</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">Giờ Vàng, Mua Xe trả góp Giá 0d</a>
                            </li>
                            <li>
                                <a href="https://www.facebook.com/">:D</a>
                            </li>
                        </ul>
                        {/* <!-- category submenu end--> */}
                    </li>
                </ul>
            </div>
        </div>
    );
}
