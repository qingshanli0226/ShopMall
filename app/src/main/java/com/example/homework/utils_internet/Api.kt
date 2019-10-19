package com.example.homework.utils_internet

import com.example.homework.home.bean.ResultBean
import com.example.homework.type.beantag.TagBean
import com.example.homework.type.bean.TypeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {
    @GET
    fun getHomeData(@Url url: String): Observable<ResultBean>

    @GET
    fun getTypeData(@Url url: String): Observable<TypeBean>

    @GET
    fun getTagData(@Url url: String): Observable<TagBean>


}