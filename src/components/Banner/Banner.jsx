import React from 'react';
import { Link } from 'react-router-dom';

export default function Banner() {
    return (
        <section className="banner_main">
            <div id="banner1" className="carousel slide" data-ride="carousel">
                <ol className="carousel-indicators">
                    <li data-target="#banner1" data-slide-to="0" className="active"></li>
                    <li data-target="#banner1" data-slide-to="1"></li>
                    <li data-target="#banner1" data-slide-to="2"></li>
                    <li data-target="#banner1" data-slide-to="3"></li>
                    <li data-target="#banner1" data-slide-to="4"></li>
                </ol>
                <div className="carousel-inner">
                    <div className="carousel-item active">
                        <div className="container">
                            <div className="carousel-caption">
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="text-bg">
                                            <span>Secondhand Town</span>
                                            <h1>Clothing Secondhand</h1>
                                            <p>
                                                There are many variations of passages of Lorem Ipsum available, but the
                                                majority have suffered alteration in some form, by injected humour, or{' '}
                                            </p>
                                            <Link to="/product">Shopping Now </Link> <Link to="/contact">Contact</Link>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="text_img">
                                            <figure>
                                                <img  src="images/bannerW.png" alt="#" style={{width:"670px",height:"400px"}}/>
                                            </figure>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <div className="container">
                            <div className="carousel-caption">
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="text-bg">
                                            <span>Secondhand Town</span>
                                            <h1>Clothing Secondhand</h1>
                                            <p>
                                                There are many variations of passages of Lorem Ipsum available, but the
                                                majority have suffered alteration in some form, by injected humour, or{' '}
                                            </p>
                                            <Link to="/product">Shopping Now </Link> <Link to="/contact">Contact</Link>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="text_img">
                                            <figure>
                                                 <img  src="images/bannerW2.png" alt="#" style={{width:"670px",height:"400px"}}/>
                                            </figure>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <div className="container">
                            <div className="carousel-caption">
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="text-bg">
                                            <span>Secondhand Town</span>
                                            <h1>Clothing Secondhand</h1>
                                            <p>
                                                There are many variations of passages of Lorem Ipsum available, but the
                                                majority have suffered alteration in some form, by injected humour, or{' '}
                                            </p>
                                            <Link to="/product">Shopping Now </Link> <Link to="/contact">Contact</Link>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="text_img">
                                            <figure>
                                            <img  src="images/bannerW3.png" alt="#" style={{width:"670px",height:"400px"}}/>
                                            </figure>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <div className="container">
                            <div className="carousel-caption">
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="text-bg">
                                            <span>Secondhand Town</span>
                                            <h1>Clothing Secondhand</h1>
                                            <p>
                                                There are many variations of passages of Lorem Ipsum available, but the
                                                majority have suffered alteration in some form, by injected humour, or{' '}
                                            </p>
                                            <Link to="/product">Shopping Now </Link> <Link to="/contact">Contact</Link>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="text_img">
                                            <figure>
                                            <img  src="images/bannerW4.png" alt="#" style={{width:"670px",height:"400px"}}/>
                                            </figure>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <div className="container">
                            <div className="carousel-caption">
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="text-bg">
                                            <span>Secondhand Town</span>
                                            <h1>Clothing Secondhand</h1>
                                            <p>
                                                There are many variations of passages of Lorem Ipsum available, but the
                                                majority have suffered alteration in some form, by injected humour, or{' '}
                                            </p>
                                            <Link to="/product">Shopping Now </Link> <Link to="/contact">Contact</Link>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="text_img">
                                            <figure>
                                            <img  src="images/bannerW5.png" alt="#" style={{width:"670px",height:"400px"}}/>
                                            </figure>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <a className="carousel-control-prev" href="/#" role="button" data-slide="prev">
                    <i className="fa fa-chevron-left" aria-hidden="true"></i>
                </a>
                <a className="carousel-control-next" href="/#" role="button" data-slide="next">
                    <i className="fa fa-chevron-right" aria-hidden="true"></i>
                </a>
            </div>
        </section>
    );
}
