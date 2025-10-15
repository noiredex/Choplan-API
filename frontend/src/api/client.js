import http from "./http";

export const api = {
    mypage: {
        summary: () => http.get('/mypage/summary').then(r => r.data),
        reservations: () => http.get('/reservations/me').then(r => r.data),
        reviews: () => http.get('/mypage/reviews').then(r => r.data),
        scraps: () => http.get('/mypage/scraps').then(r => r.data),
    },
    reservation: {
        cancel: (reservationId) => http.post(`/reservations/${reservationId}/cancel`),
        cancelDeposit: (reservationId) => http.post(`/reservations/${reservationId}/deposit/cancel`),
    },
    profile: {
        get: () => http.get('/profile/me').then(r => r.data),
        update: (payload) => http.put('/profile/me', payload).then(r => r.data)
    }
}