package com.bankwithmint.network

import com.bankwithmint.response.CardDetails

class ProcessService : SafeApiRequest(){

    val call = RetrofitClient

    suspend fun cardNumber(cardNumber: String): CardDetails {
        return apiRequest {call.instance().getCardDetails(cardNumber)}
    }

}
