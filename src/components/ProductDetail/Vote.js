import React from 'react'
import "./Vote.scss"

export default function Vote() {
  return (
    <div className="vote-container">
      <iframe
        title="comment"
        src="http://www.facebook.com/plugins/comments.php?href=http://localhost:3000"
        scrolling="no"
        frameBorder="0"
        width={"100%"}
        style={{
          border: "none",
          overflow: "hidden",
          width: "100%",
          height: "1000px",
        }}
      ></iframe>
    </div>
  )
}
