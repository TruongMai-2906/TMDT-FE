import React from 'react';

export default function Contact() {
    return (
        <div className="contact">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <div className="titlepage">
                            <h2>Liên hệ với chúng tôi</h2>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-10 offset-md-1">
                        <form id="request" className="main_form">
                            <div className="row">
                                <div className="col-md-12 ">
                                    <input className="contactus" placeholder="Tên" type="type" name="Name" />
                                </div>
                                <div className="col-md-12">
                                    <input className="contactus" placeholder="Email" type="type" name="Email" />
                                </div>
                                <div className="col-md-12">
                                    <input
                                        className="contactus"
                                        placeholder="Số điện thoại"
                                        type="type"
                                        name="Phone Number"
                                    />
                                </div>
                                <div className="col-md-12">
                                    <textarea className="textarea" placeholder="Lời nhắn" type="type" message="Name">
                                        {/* message{' '} */}
                                    </textarea>
                                </div>
                                <div className="col-md-12">
                                    <button className="send_btn">Gửi</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}
