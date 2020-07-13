package com.bankwithmint.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardNumber(
    @SerializedName("length") val length: Int?,
    @SerializedName("luhn") val luhn: Boolean?
) : Parcelable