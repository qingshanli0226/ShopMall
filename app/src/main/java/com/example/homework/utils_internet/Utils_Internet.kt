package com.example.homework.utils_internet

import com.example.homework.home.bean.ResultBean
import com.example.homework.utils.Constants
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Utils_Internet {
    private lateinit var retrofit:Retrofit
    private lateinit var api:Api

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE + "/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getHomeData(url:String, callBackData: CallBackData){
        api.getHomeData(url)
            .subscribeOn(Schedulers.io()) //订阅 调整程序
            .observeOn(AndroidSchedulers.mainThread()) //观察 主线程
            .subscribe(object : Observer<ResultBean>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                //获取数据
                override fun onNext(t: ResultBean) {
                    callBackData.onSuccess(t)
                }

                //失败
                override fun onError(e: Throwable) {
                    callBackData.onError(e)
                }
            })
    }

    interface CallBackData{
        fun onSuccess(t: ResultBean) //成功
        fun onError(e: Throwable) //失败
    }
}