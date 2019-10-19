package com.example.homework.utils_internet

import com.example.homework.home.bean.ResultBean
import com.example.homework.type.bean.TypeBean
import com.example.homework.type.beantag.TagBean
import com.example.homework.utils.Constants
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Utils_Internet {
    private var retrofit: Retrofit
    private var api: Api

    init {
//        val okHttpClient = OkHttpClient.Builder()
//            .connectTimeout(5000, TimeUnit.SECONDS)
//            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE + "/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(okHttpClient)
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getHomeData(url: String, callBackData: CallBackData) {
        api.getHomeData(url)
            .subscribeOn(Schedulers.io()) //订阅 调整程序
            .observeOn(AndroidSchedulers.mainThread()) //观察 主线程
            .subscribe(object : Observer<ResultBean> {
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

    fun getTagData(url: String, callBackTagTypeData: CallBackTagTypeData) {
        api.getTagData(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<TagBean>{
                override fun onComplete() {


                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: TagBean) {
                    callBackTagTypeData.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    callBackTagTypeData.onError(e)
                }
            })
    }

    fun getTypeData(url: String, callBackTypeData: CallBackTypeData) {
        api.getTypeData(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TypeBean> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: TypeBean) {
                    callBackTypeData.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    callBackTypeData.onError(e)
                }
            })
    }


    interface CallBackData {
        fun onSuccess(t: ResultBean) //成功
        fun onError(e: Throwable) //失败
    }

    interface CallBackTypeData {
        fun onSuccess(typeBean: TypeBean) //成功
        fun onError(e: Throwable) //失败
    }

    interface CallBackTagTypeData {
        fun onSuccess(tagBean: TagBean) //成功
        fun onError(e: Throwable) //失败
    }

}