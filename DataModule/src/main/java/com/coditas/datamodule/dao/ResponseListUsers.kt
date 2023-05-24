package com.coditas.datamodule.dao

import com.google.gson.annotations.SerializedName

data class ResponseListUsers(

    var data: List<Data>,

    var page: Int,

    @SerializedName("per_page")
    var perPage: Int,

    var support: Support,

    var total: Int,

    @SerializedName("total_pages")
    var totalPages: Int
)