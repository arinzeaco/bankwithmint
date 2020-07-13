package com.bankwithmint.network

import com.bankwithmint.response.CardDetails
import retrofit2.Response
import retrofit2.http.PATCH
import retrofit2.http.Path


interface Api {

    @PATCH("{id}")
    suspend fun getCardDetails(@Path("id") id: String?): Response<CardDetails>

}

