package com.bankwithmint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bankwithmint.response.CardDetails
import com.google.gson.Gson
import kotlinx.android.synthetic.main.card_detail.*

class CardDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_detail)
        val mydata = Gson().fromJson(intent?.extras?.getString("MY_DATA"), CardDetails::class.java)
//          if(mydata.ha)
        //Set details to textview
        network_data.setText(mydata.scheme)
        type_data.setText(mydata.brand)
        bank_data.setText(mydata.bank!!.name)
        brand_data.setText(mydata.brand)
        prepaid_data.setText(mydata.prepaid)
        country_data.setText(mydata.country!!.name)
        phone_data.setText(mydata.bank.phone)
        city_data.setText(mydata.bank.city)



    }
}