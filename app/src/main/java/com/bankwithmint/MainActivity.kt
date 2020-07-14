package com.bankwithmint


import android.content.Intent
import android.util.Log
import android.view.View
import com.bankwithmint.constant.Constant
import com.bankwithmint.util.ApiException
import com.bankwithmint.util.Coroutines
import com.google.gson.Gson
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.topbar_item.*
import java.io.IOException

class MainActivity : BaseActivity() {

    lateinit var cardnum:String
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
    override fun initView() {
//        cardNumber.setText("45717360")
        topbarTitle.setText(this.getString(R.string.app_name))
        backBtn.visibility= View.GONE
        ic_less_than.visibility= View.GONE
        get_details.setOnClickListener {
            onLoginButtonClick()
        }
    }


    fun onLoginButtonClick(){
        //check if card edittext is empty field is empty
        cardnum=  cardNumber.text.toString().replace(" ","")
        if(!validatefield(cardnum) || cardnum.length<7){
            FancyToast.makeText(this, this.getString(R.string.enter_a_card_number),
                FancyToast.LENGTH_SHORT,  FancyToast.ERROR, false).show()
            return
        }

        //may retrofit call
        Coroutines.main {
            showProgressDialog(true)
            try {
                val authResponse = processService.getcardDetails(cardnum.trim())
                authResponse.let {

                    val intent= Intent(this, CardDetail::class.java).apply {
                        putExtra(Constant.MY_DATA, Gson().toJson(it))
                    }
                    showProgressDialog(false)
                    startActivity(intent)
                }
            }catch(e: ApiException){
                showProgressDialog(false)
               checkError(e.message!!)
            }
        }

    }


}
