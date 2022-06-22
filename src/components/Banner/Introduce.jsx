import React from 'react'

export default function Introduce({title,body}) {
  return (
    <div className='banner-introduce' style={{marginBottom:'3%'}}>
        <div className="row">
            <div className="container">
            <h2>{title}</h2>
            <p>{body}</p>
            </div>
        </div>
    </div>
  )
}
