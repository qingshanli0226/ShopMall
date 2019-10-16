package com.bw.dianshang

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Retrofits {

    @GET
    abstract fun getBean(@Url url: String): Call<Bean>

}