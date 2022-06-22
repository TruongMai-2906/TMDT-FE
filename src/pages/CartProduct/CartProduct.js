import React, { useState, useEffect } from 'react';
import Introduce from '~/components/Banner/Introduce';
import { Link } from 'react-router-dom';
import './CartProduct.scss';
import { NotifyError, NotifySuccess } from '~/Utils/Notice';
import { methodGet, methodPost } from '~/Utils/Request';
import { faRss } from '@fortawesome/free-solid-svg-icons';
import InternalPreviewGroup from 'antd/lib/image/PreviewGroup';
export default function CartProduct() {
    const [quantity, setQuantity] = useState(1);
    const [listcardItem, SetListCardItem] = useState({});

    const handleAdd = async (e, id) => {
        console.log('id', e, id);
        const dataPost = {
            productid: id,
            quantity: Number(e),
        };
        const rs = await methodPost('cart/update', dataPost).catch((e) => {
            console.log('lỗi call api add cart');
        });
        console.log();
        if (rs?.data) {
            NotifySuccess('Thêm số lượng sản phẩm thành công');
        } else {
            NotifyError('Thêm số lượng sản phẩm thất bại');
        }
    };
    const handleRemove = async (id) => {
        const dataPost = {
            productid: id,
        };
        const rs = await methodPost('cart/update?action=remove', dataPost).catch((e) => {
            console.log('lỗi call api remove cart');
        });
        if (rs?.data) {
            NotifySuccess('Xóa sản phẩm thành công ');
            // const temp = listcardItem.cartItemList.map((item, index) => {
            //     if (item?.id == id) {
            //     } else {
            //         return item;
            //     }
            // });
            // console.log('temp', temp);
            SetListCardItem(rs.data);
        } else {
            NotifyError('Xóa thất bại');
        }
    };
    useEffect(() => {
        const getData = async () => {
            const rs = await methodGet('/cart/getListCardItem').catch((e) => {
                NotifyError('Load Giỏ hàng thất bại');
            });
            if (rs?.data) {
                console.log('data cart item ', rs?.data);
                SetListCardItem(rs.data);
            } else {
                NotifyError('Kiểm tra lại =>>Fail');
            }
        };
        getData();
    }, []);
    return (
        <div>
            <Introduce title="Trang chủ" body={'Trang chủ / Giỏ hàng của tôi'} />
            <div className="container">
                <div className="row">
                    <div className="col-md-12 col-sm-12">
                        {/* <!-- Form Start --> */}
                        {listcardItem ? (
                            <h1>Đây là giỏ hàng của bạn, Có {listcardItem.length} sản phẩm</h1>
                        ) : (
                            <h1>Bạn chưa thêm bất kì sản phẩm nào vào giỏ hàng, hãy tiếp tục mua sắm nhé</h1>
                        )}
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
                                        {listcardItem &&
                                            listcardItem?.cartItemList?.map((item, index) => {
                                                const id = item?.product?.id;
                                                // console.log(id);
                                                return (
                                                    <tr key={index}>
                                                        <td className="product-thumbnail">
                                                            <Link to={`/product-detail/${item?.product?.id}`}>
                                                                <img src={item?.product?.image} alt="cart-image" />
                                                            </Link>
                                                        </td>
                                                        <td className="product-name">
                                                            <Link to={`/product-detail/${item?.product?.id}`}>
                                                                {item?.product?.name}
                                                            </Link>
                                                        </td>
                                                        <td className="product-price">
                                                            <span className="amount">{item?.product?.price} VND</span>
                                                        </td>
                                                        <td className="product-quantity">
                                                            <input
                                                                type="number"
                                                                value={item?.quantity}
                                                                onChange={(e) => {
                                                                    if (e.target.value < 1) {
                                                                        NotifyError('Vui lòng chọn số lượng hợp lệ');
                                                                        setQuantity(1);
                                                                    } else {
                                                                        setQuantity(e.target.value);
                                                                    }
                                                                    handleAdd(e.target.value, id);
                                                                }}
                                                            />
                                                        </td>
                                                        <td className="product-subtotal">{item?.totalPrice} Vnd</td>
                                                        <td className="product-remove">
                                                            <a
                                                                onClick={(e) => {
                                                                    e.preventDefault();
                                                                    handleRemove(id);
                                                                }}
                                                            >
                                                                <i className="fa fa-times" aria-hidden="true"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                );
                                            })}
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
                                                        <span className="amount">{listcardItem?.totalCart} Vnd</span>
                                                    </td>
                                                </tr>
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
