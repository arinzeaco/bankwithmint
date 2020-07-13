package com.bankwithmint.util

import com.bankwithmint.response.CardDetails

interface AuthListener {
    fun onStarted()
    fun onSuccess(cardDetails: CardDetails)
    fun onFailure(message: String)
}