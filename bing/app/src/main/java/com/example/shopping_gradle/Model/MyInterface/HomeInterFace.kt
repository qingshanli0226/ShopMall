package com.example.shopping_gradle.Model.MyInterface

import com.example.shopping_gradle.Model.Entity.ResultBean
import retrofit2.http.GET
import retrofit2.http.Url

interface HomeInterFace{

    @GET
    fun homeDataCall(@Url url: String): retrofit2.Call<ResultBean>
}
