import { Pagination } from 'antd';
import React, { useState } from 'react';

export default function Paging({ size, current, handleChange }) {
    // const [current, setCurrent] = useState(1);
    console.log('curren ở trong paging', current);
    // const handleChange = (e) => {
    //     console.log(e);
    //     setCurrent(e);
    // };
    return (
        <div className="phan-trang" style={{ textAlign: 'center' }}>
            <Pagination
                current={current}
                total={size}
                onChange={handleChange}
                showLessItems
                showSizeChanger={false}
            ></Pagination>
        </div>
    );
}
