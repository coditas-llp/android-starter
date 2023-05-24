package com.coditas.datamodule.dao

import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("avatar")
    var avatar: String,

    var email: String,

    @SerializedName("first_name")
    var firstName: String,

    var id: Int,

    @SerializedName("last_name")
    var lastName: String,

    var support:Support
)