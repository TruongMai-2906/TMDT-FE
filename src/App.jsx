// import './scss/style.scss';
import 'antd/dist/antd.min.css';
import Home from './pages/Home/Home';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import NotFound from './pages/NotFound/NotFound';
import About from './pages/About/About';
import Login from './pages/Login/Login';
import Contact from './pages/Contact/Contact';
import Header from './components/Header/Header';
import Mgtop from './components/MgTop/Mgtop';
import SingnUp from './pages/SignUp/SingnUp';
import Product from './pages/Product/Product';
import ProductDetail from './pages/ProductDetail/ProductDetail';
import React from 'react';
import Search from './pages/Search/Search';
import AccountInfo from './pages/AccountInfo/AccountInfo';
import InfoShop from './pages/InfoShop/InfoShop';
import CartProduct from './pages/CartProduct/CartProduct';
import Order from './pages/Order/Order';
import MyOrder from './pages/MyOrder/MyOrder';
import SlideBar from './components/Admin/SlideBar/SlideBar';
import LayoutAdmin from './components/Admin/LayoutAdmin/LayoutAdmin';
import HomeAdmin from './pages/Admin/HomeAdmin/HomeAdmin';
import RecoveryPass from './pages/RecoveryPass/RecoveryPass';

export default function App() {
    const checkRole = localStorage.getItem('role');
    console.log(checkRole);
    console.log('abcccccccccccccc');
    return (
        <BrowserRouter>
            <div className="app">
                <div className="main-layout">
                    <div className="loader_bg">
                        <div className="loader">
                            <img src="images/loading.gif" alt="#" />
                        </div>
                    </div>
                    <Header />
                    <Mgtop />
                    <Routes>
                        <Route exact path="/" element={<Home />} />
                        <Route path="/admin" element={<LayoutAdmin />}>
                            <Route path="home" element={<HomeAdmin />} />
                            <Route path="*" element={<NotFound />} />
                        </Route>
                        <Route path="/login" element={<Login />} />
                        <Route path="/signUp" element={<SingnUp />} />
                        <Route path="/cart" element={<CartProduct />} />
                        <Route path="/order" element={<Order />} />
                        <Route path="/my-order" element={<MyOrder />} />
                        <Route path="/search/:keywork" element={<Search />} />
                        <Route path="/account-info" element={<AccountInfo />} />
                        <Route path="/product-detail/:id" element={<ProductDetail />} />
                        <Route path="/product/*" element={<Product />} />
                        <Route path="/about" element={<About />} />
                        <Route path="/contact" element={<Contact />} />
                        <Route path="/info-shop" element={<InfoShop />} />
                        <Route path="/recovery" element={<RecoveryPass />} />
                        <Route path="*" element={<NotFound />} />
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
    );
}
