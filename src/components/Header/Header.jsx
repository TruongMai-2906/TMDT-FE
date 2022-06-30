import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useCheckMobileScreen } from '~/Utils/CustomHook';
import { methodGet } from '~/Utils/Request';
import './Header.scss';
export default function Header() {
    const navigate = useNavigate();
    const [keywork, setKeyWork] = useState('');
    const [checkuser, SetCheckUser] = useState(localStorage.getItem('accessToken'));
    const [currentUser, SetCurrentUser] = useState(null);

    const handleLogout = () => {
        localStorage.removeItem('User');
        localStorage.removeItem('tokenType');
        localStorage.removeItem('accessToken');
        SetCurrentUser(null);
    };
    useEffect(() => {
        if (!keywork) {
            navigate('/');
        } else {
            keywork && navigate(`/search/${keywork}`);
        }
    }, [keywork]);

    useEffect(() => {
        if (checkuser) {
            // có user thì mới call API ,
            const testGet = async () => {
                const user = await methodGet('/api/user/me');
                // const user = await methodGet('/cart/getAllListOder');
                SetCurrentUser(user.data);
                console.log('check user header', user);
            };
            testGet();
            console.log('test get');
        }
    }, [checkuser]);

    const mobile = useCheckMobileScreen(768);

    return (
        // <header>

        <div className="header">
            <div className="container-fluid">
                <div className="row">
                    <div className="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
                        <div className="full">
                            <div className="center-desk">
                                <div className="logo">
                                    <Link to="/" className={mobile ? 'header-mobile__logo--hidden' : ''}>
                                        <img src="images/logo.png" alt="#" />
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-xl-9 col-lg-9 col-md-9 col-sm-9 header__navigation-right">
                        <nav className="navigation navbar navbar-expand-md navbar-dark ">
                            <button
                                className="navbar-toggler"
                                type="button"
                                data-toggle="collapse"
                                data-target="#navbarsExample04"
                                aria-controls="navbarsExample04"
                                aria-expanded="false"
                                aria-label="Toggle navigation"
                            >
                                <span className="navbar-toggler-icon"></span>
                            </button>
                            <div className="collapse navbar-collapse" id="navbarsExample04">
                                <ul className="navbar-nav mr-auto">
                                    <li className="nav-item active">
                                        <Link to="/" className="nav-link">
                                            Trang chủ
                                        </Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/product">
                                            Cửa Hàng
                                        </Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link to="/order" className="nav-link">
                                            Liên Hệ
                                        </Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/info-shop">
                                            Thông tin cửa hàng
                                        </Link>
                                    </li>

                                    <li className="nav-item d_none haha">
                                        <input
                                            value={keywork}
                                            onChange={(e) => {
                                                setKeyWork(e.target.value);
                                            }}
                                            className="search-input"
                                        />
                                        <div className="nav-link nav-search">
                                            <i className="fa fa-search" aria-hidden="true"></i>
                                        </div>
                                    </li>
                                    <li className="nav-item d_none">
                                        {currentUser ? (
                                            <div className="account_header">
                                                <div className="account_header_intro">
                                                    <img src="images/about.jpg" alt="" />
                                                    <p className="account_header_intro_user">{currentUser.username}</p>
                                                </div>
                                                <ul className="account_header_more">
                                                    <Link to="/account-info">
                                                        <li className="account_header_more_item">Tài khoản của tôi</li>
                                                    </Link>
                                                    <Link to="/cart">
                                                        <li className="account_header_more_item">Giỏ hàng</li>
                                                    </Link>
                                                    <Link to="/my-order">
                                                        <li className="account_header_more_item">Đơn hàng đã mua</li>
                                                    </Link>
                                                    <Link to="/login">
                                                        <li onClick={handleLogout} className="account_header_more_item">
                                                            Đăng xuất
                                                        </li>
                                                    </Link>
                                                    <Link to="/admin/home">
                                                        <li className="account_header_more_item">Đến trang Admin</li>
                                                    </Link>
                                                </ul>
                                            </div>
                                        ) : (
                                            <Link className="nav-link" to="/login">
                                                Đăng Nhập
                                            </Link>
                                        )}
                                    </li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
                <Link to="/" className={mobile ? 'header-mobile__logo' : 'header-mobile__logo--disable'}>
                    <img src="images/logo.png" alt="#" />
                </Link>
            </div>
        </div>
        // </header>
    );
}
