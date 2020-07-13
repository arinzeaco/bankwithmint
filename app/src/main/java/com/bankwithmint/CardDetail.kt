package com.bankwithmint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bankwithmint.constant.Constant
import com.bankwithmint.response.CardDetails
import com.google.gson.Gson
import kotlinx.android.synthetic.main.card_detail.*

class CardDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_detail)
        val mydata = Gson().fromJson(intent?.extras?.getString(Constant.MY_DATA), CardDetails::class.java)

        //Set details to textview
        network_data.text = mydata.scheme
        type_data.text = mydata.brand
        bank_data.text = mydata.bank!!.name
        brand_data.text = mydata.brand
        prepaid_data.text = mydata.prepaid
        country_data.text = mydata.country!!.name
        phone_data.text = mydata.bank.phone
        city_data.text = mydata.bank.city



    }
}