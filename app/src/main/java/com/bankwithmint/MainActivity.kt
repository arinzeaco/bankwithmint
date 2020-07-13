package com.bankwithmint


import android.content.Intent
import com.bankwithmint.constant.Constant
import com.bankwithmint.util.Coroutines
import com.google.gson.Gson
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : BaseActivity() {

    lateinit var cardnum:String
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
    override fun initView() {
        cardNumber.setText("45717360")
        cardnum=cardNumber.getText().toString().trim()
        get_details.setOnClickListener {
            onLoginButtonClick()
        }
    }


    fun onLoginButtonClick(){
        //check if card edittext is empty field is empty
        if(!validatefield(cardnum)){
            FancyToast.makeText(this, this.getString(R.string.enter_a_card_number),
                FancyToast.LENGTH_SHORT,  FancyToast.ERROR, false).show()
            return
        }

        //may retrofit call
        Coroutines.main {
            showProgressDialog(true)
            try {
                val authResponse = processService.cardNumber(cardNumber.text.toString())
                authResponse.let {
                    showProgressDialog(false)
                    val intent= Intent(this, CardDetail::class.java).apply {
                        putExtra(Constant.MY_DATA, Gson().toJson(it))
                    }
                    startActivity(intent)
                }
            }catch(e: IOException){
                showProgressDialog(false)
                handleError(e.message)


            }
        }

    }


}
