package com.bankwithmint.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CardDetails(
    @SerializedName("number") val number: CardNumber?,
    @SerializedName("scheme") val scheme: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("brand") val brand: String?,
    @SerializedName("prepaid") val prepaid: String?,
    @SerializedName("country") val country: Country?,
    @SerializedName("bank") val bank: Bank?
) : Parcelable