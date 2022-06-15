import React, { useState } from 'react';
import Introduce from '~/components/Banner/Introduce';
import './Order.scss';
export default function Order() {
    const [country, setCountry] = useState('VietNam');
    const [ho, setHo] = useState('');
    const [ten, setTen] = useState('');
    const [diachi, setDiaChi] = useState('');
    const [city, setCity] = useState('');
    const [phone, setPhone] = useState('');
    const handleSubmit = (e) => {
        console.log(e);
        e.preventDefault();
    };
    return (
        <div>
            <Introduce body="Trang chủ" title="Trang chủ / Đặt hàng" />
            <div className="container">
                <form onSubmit={handleSubmit}>
                    <div className="row">
                        <div className="col-lg-6 col-md-6">
                            <div className="checkbox-form mb-sm-40">
                                <h3>Chi tiết đặt hàng</h3>
                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="country-select clearfix mb-30">
                                            <label>
                                                Quốc gia <span className="required">*</span>
                                            </label>
                                            <select className="wide">
                                                <option defaultValue={country} value="VietNam">
                                                    Việt nam
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="checkout-form-list mb-sm-30">
                                            <label>
                                                Họ <span className="required">*</span>
                                            </label>
                                            <input
                                                onChange={(e) => {
                                                    setHo(e.target.value);
                                                }}
                                                type="text"
                                                placeholder=""
                                            />
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="checkout-form-list mb-30">
                                            <label>
                                                Tên <span className="required">*</span>
                                            </label>
                                            <input
                                                onChange={(e) => {
                                                    setTen(e.target.value);
                                                }}
                                                type="text"
                                                placeholder=""
                                            />
                                        </div>
                                    </div>
                                    <div className="col-md-12">
                                        <div className="checkout-form-list mb-30">
                                            <label>Tên công ti ( Nếu có ) </label>
                                            <input type="text" placeholder="" />
                                        </div>
                                    </div>
                                    <div className="col-md-12">
                                        <div className="checkout-form-list">
                                            <label>
                                                Địa chỉ nhận hàng <span className="required">*</span>
                                            </label>
                                            <input
                                                type="text"
                                                onChange={(e) => {
                                                    setDiaChi(e.target.value);
                                                }}
                                                placeholder="Street address"
                                            />
                                        </div>
                                    </div>
                                    <div className="col-md-12"></div>
                                    <div className="col-md-12">
                                        <div className="checkout-form-list mb-30">
                                            <label>
                                                Thành Phố <span className="required">*</span>
                                            </label>
                                            <input
                                                type="text"
                                                onChange={(e) => {
                                                    setCity(e.target.value);
                                                }}
                                                placeholder="Town / City"
                                            />
                                        </div>
                                    </div>

                                    <div className="col-md-6">
                                        <div className="checkout-form-list mb-30">
                                            <label>
                                                Địa chỉ Email (Không bắt buộc) <span className="required"></span>
                                            </label>
                                            <input type="email" placeholder="" />
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="checkout-form-list mb-30">
                                            <label>
                                                Số điện thoại <span className="required">*</span>
                                            </label>
                                            <input
                                                onChange={(e) => {
                                                    setPhone(e.target.value);
                                                }}
                                                type="phone"
                                                placeholder="Phone Number"
                                            />
                                        </div>
                                    </div>
                                    <div className="col-md-12"></div>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6 col-md-6">
                            <div className="your-order">
                                <h3>Đơn hàng của bạn</h3>
                                <div className="your-order-table table-responsive">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th className="product-name">Sản phẩm</th>
                                                <th className="product-total">Tổng tiền</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr className="cart_item">
                                                <td className="product-name">
                                                    Vestibulum suscipit <span className="product-quantity"> × 1</span>
                                                </td>
                                                <td className="product-total">
                                                    <span className="amount">£165.00</span>
                                                </td>
                                            </tr>
                                            <tr className="cart_item">
                                                <td className="product-name">
                                                    Vestibulum dictum magna{' '}
                                                    <span className="product-quantity"> × 1</span>
                                                </td>
                                                <td className="product-total">
                                                    <span className="amount">£50.00</span>
                                                </td>
                                            </tr>
                                        </tbody>
                                        <tfoot>
                                            <tr className="cart-subtotal">
                                                <th>Giảm giá</th>
                                                <td>
                                                    <span className="amount">£215.00</span>
                                                </td>
                                            </tr>
                                            <tr className="order-total">
                                                <th>Giá cuối</th>
                                                <td>
                                                    <span className=" total amount">£215.00</span>
                                                </td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <div className="payment-method">
                                    <div id="accordion">
                                        <div className="card">
                                            <div className="card-header" id="headingone">
                                                <h5 className="mb-0">
                                                    <button
                                                        className="btn btn-link"
                                                        data-toggle="collapse"
                                                        data-target="#collapseOne"
                                                        aria-expanded="true"
                                                        aria-controls="collapseOne"
                                                    >
                                                        Thanh toán khi nhận hàng ( Mặc định)
                                                    </button>
                                                </h5>
                                            </div>

                                            <div
                                                id="collapseOne"
                                                className="collapse show"
                                                aria-labelledby="headingone"
                                                data-parent="#accordion"
                                            >
                                                <div className="card-body">
                                                    <p>
                                                        Là hình thức khi bạn nhận hàng sẽ thanh toán tiền cho shipper .
                                                        Đây là hình thức thanh toán an toàn và hiệu quả , được mọi người
                                                        trên thế giới yêu thích và tin cậy
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="card">
                                            <div className="card-header" id="headingtwo">
                                                <h5 className="mb-0">
                                                    <button
                                                        className="btn btn-link collapsed"
                                                        data-toggle="collapse"
                                                        data-target="#collapseTwo"
                                                        aria-expanded="false"
                                                        aria-controls="collapseTwo"
                                                    >
                                                        Momo / Chuyển khoản
                                                    </button>
                                                </h5>
                                            </div>
                                            <div
                                                id="collapseTwo"
                                                className="collapse"
                                                aria-labelledby="headingtwo"
                                                data-parent="#accordion"
                                            >
                                                <div className="card-body">
                                                    <p>
                                                        VUi lòng chuyển khoản vào stk VCB 1053253415 AN LUU VAN và liên
                                                        hệ Facebook ở phần liên hệ để mình lên đơn tay
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="wc-proceed-to-checkout">
                                    <button type="submit">Tiến hành đặt hàng</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}
