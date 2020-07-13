package com.bankwithmint.repository

import com.bankwithmint.network.RetrofitClient
import com.bankwithmint.network.SafeApiRequest

import com.bankwithmint.response.CardDetails

class CardRepository : SafeApiRequest(){

    val call = RetrofitClient

    suspend fun cardNumber(cardNumber: String): CardDetails {
        return apiRequest {call.instance().getCardDetails(cardNumber)}
    }

}
