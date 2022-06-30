import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './SlideBar.scss';
export default function SlideBar({ setChangeUrl, pageIndex }) {
    const navigate = useNavigate();
    const [category, setCategory] = useState({
        selectedFirst: 'ao',
        listcategory: [
            {
                key: 'Áo',
                value: '&category=Áo nữ&category=Áo nam',
            },
            {
                key: 'Quần',
                value: '&category=Quần nữ&category=Quần nam',
            },
            {
                key: 'Mũ',
                value: '&category=Mũ nữ&category=Mũ nam',
            },
            {
                key: 'Giày',
                value: '&category=Giày nữ&category=Giày nam',
            },
            {
                key: 'Khác',
                value: '&category=khac',
            },
        ],
    });

    const onSelectCategogy = (key) => {
        console.log('key chon', key);
        setCategory({
            ...category,
            selectedFirst: key,
        });
        const Urlnew = `${pageIndex?.urlOriginal}${key}`;
        console.log('url new ở slidebar', Urlnew);
        setChangeUrl({
            ...pageIndex,
            urlMain: Urlnew,
        });
        navigate(Urlnew);
    };

    return (
        <div className="slidebar">
            <h2 className="slidebar-title">Tổng quan</h2>
            <div id="shop-cate-toggle" className="category-menu sidebar-menu sidbar-style">
                <ul>
                    <li className="has-sub">
                        <a href="#">Thể loại</a>
                        <ul className="category-sub">
                            {category &&
                                category?.listcategory?.map((cat, index) => (
                                    <li style={{ padding: '15px' }}>
                                        <div
                                            key={`cat-${index}`}
                                            style={category.selectedFirst === cat.key ? { background: 'red' } : {}}
                                            href="https://www.facebook.com/"
                                            onClick={(e) => onSelectCategogy(cat.value)}
                                        >
                                            {cat.key}
                                        </div>
                                    </li>
                                ))}
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    );
}
