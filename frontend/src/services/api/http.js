const baseUrl = import.meta.env.VITE_API_BASE_URL || '';

export async function http(path, { method = 'GET', headers = {}, body } = {}) {
    
    const url = path.startsWith('/') ? `${baseUrl}${path}` : path;
    const res = await fetch(url, {
        method,
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...(headers || {}) 
            },
        credentials: 'include',
        body: body ? JSON.stringify(body) : undefined,
    })

    const text = await res.text()
    const data = text ? JSON.parse(text) : null
    
    if (!res.ok) {
        console.error('API Error:', {
            url,
            status: res.status,
            statusText: res.statusText,
            body: data
        });
        const msg = data?.message || 
                        data?.error || 
                        res.statusText;
        throw new Error(msg)
    }
    return data    
}