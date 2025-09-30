import { http } from './http' 

export function createDepositIntent(payload) {
    return http('/api/v1/payments/deposits/intents', { 
        method: 'POST', 
        body: payload,
    })
}

export function confirmDeposit(payload) {
    
    return http('/api/v1/payments/deposits/confirm', { 
        method: 'POST', 
        body: payload,
    })
}