import React from 'react';
import { Outlet } from 'react-router-dom';

import Navbar from '../Navbar/Navbar';
import SlideBar from '../SlideBar/SlideBar';

export default function LayoutAdmin() {
    // console.log('children ne', children);
    return (
        <div className="container-scroller">
            <SlideBar />
            <div className="container-fluid page-body-wrapper">
                <Navbar />
                <div className="main-panel">
                    <Outlet />
                </div>
            </div>
        </div>
    );
}
