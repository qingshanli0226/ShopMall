package com.example.homework.utils_internet

import com.example.homework.home.bean.ResultBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {
    @GET
    fun getHomeData(@Url url: String):Observable<ResultBean>

}