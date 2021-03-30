package com.example.myapplicationlist.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkResponseModel <out T:Any> (

    @SerializedName("data") @Expose val data: T?
)

