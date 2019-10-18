package com.example.kotlinshopping.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinshopping.Constants
import com.example.kotlinshopping.R
import com.example.kotlinshopping.adapter.home.HomeRecyclerAdapter
import com.example.kotlinshopping.bean.HomeBean
import com.example.kotlinshopping.net.RetrofitService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//首页Fragment
class HomeFragment : Fragment() {
    var retrofit:Retrofit? = null
    var retrofitService:RetrofitService? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.fragment_home,container,false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var okclient = OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder()
            .client(okclient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.BASE)
            .build()
        retrofitService = retrofit?.create(RetrofitService::class.java)
        retrofitService!!.getData(Constants.HOME_URL)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<HomeBean>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                                                                                                                                   }

                override fun onNext(t: HomeBean) {
                    rv_home.layoutManager = LinearLayoutManager(context)
                    rv_home.adapter = HomeRecyclerAdapter(
                        context!!,
                        t.result
                    )
                }

                override fun onError(e: Throwable) {
                    Log.d("LQS", "error" + e.printStackTrace())
                }

            })
    }
}