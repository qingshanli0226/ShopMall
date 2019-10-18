package com.example.shopping_gradle.Model.MyInterface

import com.example.shopping_gradle.Model.Entity.MyleftTypeBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface TypeInteface {

    @GET
    fun getLeftBaen(@Url url:String):Call<MyleftTypeBean>
}