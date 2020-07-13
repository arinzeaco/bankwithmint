package com.bankwithmint.ViewModel


import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.bankwithmint.CardDetail
import com.bankwithmint.repository.CardRepository
import com.bankwithmint.util.AuthListener
import com.bankwithmint.util.Coroutines

import com.google.gson.Gson
import com.shashank.sony.fancytoastlib.FancyToast
import java.io.IOException


class CardViewModel : ViewModel() {
    private var cardRepository: CardRepository? = null
    init {
        cardRepository = CardRepository()
    }
    var authListener: AuthListener? = null

    fun onLoginButtonClick(cardNumber: String, context: Context){
        authListener?.onStarted()
        if(cardNumber.isEmpty() ){

            FancyToast.makeText(context, "Enter a card Number",
                FancyToast.LENGTH_SHORT,  FancyToast.ERROR, false).show()

            return
        }
        Coroutines.main {
            try {
                val authResponse = cardRepository!!.cardNumber("45717360")

                authResponse.let {
                    authListener?.onSuccess(it)
                    // if successfull more to carddetails page
                   val intent=Intent(context, CardDetail::class.java).apply {
                        putExtra("MY_DATA", Gson().toJson(it))
                    }
                    context.startActivity(intent)
                    return@main
                }


            }catch(e: IOException){
                authListener?.onFailure(e.message!!)
                FancyToast.makeText(context, e.message,
                    FancyToast.LENGTH_SHORT,  FancyToast.ERROR, false).show()

            }
        }

    }





}