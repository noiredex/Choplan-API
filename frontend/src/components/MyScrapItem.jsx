import React from 'react'

export default function MyScrapItem({ scrap }){
    return (
        <div style={{borderBottom:'1px solid #e5e7eb', padding:'12px 0'}}>
            <div style={{fontWeight:700}}>{scrap.storeName}</div>
            <div className="subtle" style={{marginTop:4}}>스크랩한 날짜: {scrap.scrapedAt}</div>
        </div>
    )
}