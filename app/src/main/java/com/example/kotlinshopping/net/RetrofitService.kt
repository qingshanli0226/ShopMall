package com.example.kotlinshopping.net

import com.example.kotlinshopping.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.*

interface RetrofitService {
@GET
 fun getData(@Url url: String):Observable<HomeBean>

}