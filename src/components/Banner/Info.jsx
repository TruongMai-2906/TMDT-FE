import React from 'react'
import { Link } from 'react-router-dom';

export default function Info({title,body,img}) {
    return (
        <div className="laptop">
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        <div className="titlepage">
                            <Link to='/'>
                                <h2>{title}</h2>
                            </Link>
                           
                            <h1>{body}</h1>
                           
                        </div>
                    </div>
                    <div className="col-md-6">
                        <div className="laptop_box">
                            <figure>
                                {/* <img src="images/pc.png" alt="#" /> */}
                                <img src={img} alt="#" />
                            </figure>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

