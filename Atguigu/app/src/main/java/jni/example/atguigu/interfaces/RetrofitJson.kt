package jni.example.atguigu.interfaces

import io.reactivex.Observable
import jni.example.atguigu.home.Bean.ResultBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import java.util.*

interface RetrofitJson {
//    http://169.254.172.7:8080/atguigu/json/HOME_URL.json

    //请求主页面数据
    @GET()
    fun getResult(@Path("") url:String): Observable<ResultBean>
}