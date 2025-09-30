export function loadPortOne() {
    if (window.IMP) return window.IMP
        return new Promise((resolve, reject) => {
            const s = document.createElement('script')
            s.src = 'https://cdn.iamport.kr/v1/iamport.js'
            s.async = true
            s.onload = () => resolve(window.IMP)
            s.onerror = () => reject(new Error('PortOne SDK load failed'))
            document.head.appendChild(s)
        })
}

export async function initPortOne() {
    const IMP = await loadPortOne()
    const code = import.meta.env.VITE_PORTONE_MERCHANT_CODE
    if (!code) throw new Error('VITE_PORTONE_MERCHANT_CODE is missing')
    IMP.init(code)
    return IMP
}