import React, { useState } from 'react';
import Introduce from '~/components/Banner/Introduce';
import { Link } from 'react-router-dom';
import './MyOrder.scss';
export default function MyOrder() {
    const [quantity, setQuantity] = useState(1);

    return (
        <div>
            <Introduce title="Trang chủ" body={'Trang chủ / Giỏ hàng của tôi'} />
            <div className="container">
                <div className="row">
                    <div className="col-md-12 col-sm-12">
                        {/* <!-- Form Start --> */}
                        <form action="#">
                            {/* <!-- Table Content Start --> */}
                            <div className="table-content table-responsive mb-45">
                                <table>
                                    <thead>
                                        <tr>
                                            <th className="product-thumbnail">Đơn hàng Id</th>
                                            <th className="product-name">Tên sản phẩm</th>
                                            <th className="product-price">Giá</th>
                                            <th className="product-quantity">Thời gian mua</th>
                                            <th className="product-subtotal">Tổng tiền</th>
                                            <th className="product-remove">Tình trạng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td className="product-thumbnail">
                                                <a href='"/#"'>
                                                    <img src="images/about.jpg" alt="cart-image" />
                                                </a>
                                            </td>
                                            <td className="product-name">
                                                <a href='"/#"'>dictum idrisus</a>
                                            </td>
                                            <td className="product-price">
                                                <span className="amount">£165.00</span>
                                            </td>
                                            <td className="product-quantity">
                                                <input
                                                    type="number"
                                                    value={quantity}
                                                    onChange={(e) => {
                                                        setQuantity(e.target.value);
                                                    }}
                                                />
                                            </td>
                                            <td className="product-subtotal">£165.00</td>
                                            <td className="product-remove">
                                                <a href='"/#"'>
                                                    <i className="fa fa-check" aria-hidden="true"></i>
                                                </a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td className="product-thumbnail">
                                                <a href="#">
                                                    <img src="images/about.jpg" alt="cart-image" />
                                                </a>
                                            </td>
                                            <td className="product-name">
                                                <a href='"/#"'>Carte Postal Clock</a>
                                            </td>
                                            <td className="product-price">
                                                <span className="amount">£50.00</span>
                                            </td>
                                            <td className="product-quantity">
                                                <input
                                                    type="number"
                                                    value={quantity}
                                                    onChange={(e) => {
                                                        setQuantity(e.target.value);
                                                    }}
                                                />
                                            </td>
                                            <td className="product-subtotal">£50.00</td>
                                            <td className="product-remove">
                                                <a href='"/#"'>
                                                    <i
                                                        className="fa fa-check"
                                                        style={{ color: 'green' }}
                                                        aria-hidden="true"
                                                    ></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            {/* <!-- Table Content Start --> */}
                            <div className="row">
                                {/* <!-- Cart Button Start --> */}
                                <div className="col-md-8 col-sm-12">
                                    <div className="buttons-cart">
                                        <Link to="/product">Tiếp tục mua sắm</Link>
                                    </div>
                                </div>
                                {/* <!-- Cart Button Start --> */}
                                {/* <!-- Cart Totals Start --> */}
                                <div className="col-md-4 col-sm-12"></div>
                                {/* <!-- Cart Totals End --> */}
                            </div>
                            {/* <!-- Row End --> */}
                        </form>
                        {/* <!-- Form End --> */}
                    </div>
                </div>
            </div>
        </div>
    );
}
