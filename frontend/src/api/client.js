import http from "./http";

export const api = {
    mypage: {
        summary: () => http.get('api/mypage/summary').then(r => r.data),
        reservations: () => http.get('api/mypage/reservations').then(r => r.data),
        reviews: () => http.get('api/mypage/reviews').then(r => r.data),
        scraps: () => http.get('api/mypage/scraps').then(r => r.data),
    },

    reservation: {
        cancel: (reservationId) => http.post(`/reservations/${reservationId}/cancel`),
        cancelDeposit: (reservationId) => http.post(`/reservations/${reservationId}/deposit/cancel`),
        createDraft: (payload) => http.post('/reservations/draft', payload)
    },
    
    profile: {
        get: () => http.get('api/profile/me').then(r => r.data),
        update: (payload) => http.put('api/profile/me', payload).then(r => r.data)
    }
}