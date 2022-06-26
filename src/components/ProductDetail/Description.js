import React from 'react';
import './Description.scss';

export default function Description({ description }) {
    const arr = description?.split('|');

    return (
        <div className="more-info-description">
            {arr &&
                arr?.map((item, index) => {
                    return <h5>{item}</h5>;
                })}
        </div>
    );
}
