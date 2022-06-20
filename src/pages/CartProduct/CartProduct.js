import React, { useState } from 'react';
import Introduce from '~/components/Banner/Introduce';
import { Link } from 'react-router-dom';
import './CartProduct.scss';
export default function CartProduct() {
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
                                            <th className="product-thumbnail">Hình ảnh</th>
                                            <th className="product-name">Tên sản phẩm</th>
                                            <th className="product-price">Giá</th>
                                            <th className="product-quantity">Số Lượng</th>
                                            <th className="product-subtotal">Tổng tiền</th>
                                            <th className="product-remove">Xóa</th>
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
                                                        console.log("zô input number ",e);
                                                        setQuantity(e.target.value);
                                                    }}
                                                />
                                            </td>
                                            <td className="product-subtotal">£165.00</td>
                                            <td className="product-remove">
                                                <a href='"/#"'>
                                                    <i className="fa fa-times" aria-hidden="true"></i>
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
                                                    <i className="fa fa-times" aria-hidden="true"></i>
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
                                        <input type="submit" value="Update Cart" />
                                        <Link to="/product">Tiếp tục mua sắm</Link>
                                    </div>
                                </div>
                                {/* <!-- Cart Button Start --> */}
                                {/* <!-- Cart Totals Start --> */}
                                <div className="col-md-4 col-sm-12">
                                    <div className="cart_totals float-md-right text-md-right">
                                        <h2>Đặt hàng để nhận ưu đãi</h2>
                                        <br />
                                        <table className="float-md-right">
                                            <tbody>
                                                <tr className="cart-subtotal">
                                                    <th>Tổng tiền</th>
                                                    <td>
                                                        <span className="amount">$215.00</span>
                                                    </td>
                                                </tr>
                                                {/* <tr className="order-total">
                                                    <th>Total</th>
                                                    <td>
                                                        <strong>
                                                            <span className="amount">$215.00</span>
                                                        </strong>
                                                    </td>
                                                </tr> */}
                                            </tbody>
                                        </table>
                                        <div className="wc-proceed-to-checkout">
                                            <Link to="/order">Tiến hành đặt hàng</Link>
                                        </div>
                                    </div>
                                </div>
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
