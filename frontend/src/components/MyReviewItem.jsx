import React from 'react'

export default function MyReviewItem({ review }){
    return (
        <div style={{borderBottom:'1px solid #e5e7eb', padding:'12px 0'}}>
            <div style={{fontWeight:700}}>{review.storeName}</div>
            <div className="subtle" style={{marginTop:4}}>{review.content}</div>
        </div>
    )
}
