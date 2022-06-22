import React from 'react'
import './Avatar.scss'
export default function Avatar({src}) {
  return (
    <div className='avatar'>
        <img src={src} alt='Kiểm tra kết nối mạng'/>
    </div>
  )
}
