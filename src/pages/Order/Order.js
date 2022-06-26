import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Introduce from '~/components/Banner/Introduce';
import { NotifyError, NotifySuccess } from '~/Utils/Notice';
import { methodGet, methodPost } from '~/Utils/Request';
import './Order.scss';
export default function Order() {
    const navigate = useNavigate();
    const [country, setCountry] = useState('VietNam');
    const [ho, setHo] = useState('');
    const [ten, setTen] = useState('');
    const [diachi, setDiaChi] = useState('');
    const [city, setCity] = useState('');
    const [phone, setPhone] = useState('');

    const [listProductOrder, SetListProductOrder] = useState({});
    useEffect(() => {
        const getListProductOder = async () => {
            const result = await methodGet('/cart/getListCardItem');
            SetListProductOrder(result.data);
        };
        getListProductOder();
    }, []);
    const handleSubmit = async (e) => {
        console.log(e);
        const adress = diachi + '-' + city + '-' + country;
        // name: ho + ' ' + ten,
        const data = {
            // idProducts: [1, 2],
            feeTotal: 30000,
            address: adress,
            phoneNumber: phone,
        };
        console.log(data);
        const rs = await methodPost('/order/checkoutOrder', data).catch((e) => {
            NotifyError('Đặt hàng thất bại , vui lòng thử lại');
        });
        console.log({ rs });
        if (rs?.data?.success) {
            NotifySuccess('Đặt hàng thành công =>>Chuyển hướng đến trang đơn hàng của tôi');
            setTimeout(() => {
                navigate('/my-order');
            }, 500);
        } else {
            NotifyError('Đặt hàng thất bại');
        }
    };
    return (
        <div>
            <Introduce body="Trang chủ" title="Trang chủ / Đặt hàng" />
            <div className="container">
                <form onSubmit={(e) => console.log('onSubmit:', e)}>
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
                                            {listProductOrder &&
                                                listProductOrder?.cartItemList?.map((item, index) => {
                                                    return (
                                                        <tr className="cart_item" key={index}>
                                                            <td className="product-name">
                                                                {item?.product?.name}
                                                                <span className="product-quantity">
                                                                    {' '}
                                                                    × {item?.quantity}
                                                                </span>
                                                            </td>
                                                            <td className="product-total">
                                                                <span className="amount">{item?.totalPrice} vnđ</span>
                                                            </td>
                                                        </tr>
                                                    );
                                                })}
                                        </tbody>
                                        <tfoot>
                                            <tr className="cart-subtotal">
                                                <th>Phí ship</th>
                                                <td>
                                                    <span className="amount">30.000 </span>
                                                </td>
                                            </tr>
                                            <tr className="order-total">
                                                <th>Giá cuối</th>
                                                <td>
                                                    <span className=" total amount">{listProductOrder?.totalCart}</span>
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
                                    <div onClick={handleSubmit}>Tiến hành đặt hàng</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}
