import React from 'react';

export default function Sale() {
    return (
        <div className="laptop">
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        <div className="titlepage">
                            <p>Tưng bừng khai trương</p>
                            <h2>Giảm giá cực sốc!</h2>
                            <a className="read_more" href="javascript:void(0)">
                                Mua ngay
                            </a>
                        </div>
                    </div>
                    <div className="col-md-6">
                        <div className="laptop_box">
                            <figure>
                                <img src="images/pc.png" alt="#" />
                            </figure>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
