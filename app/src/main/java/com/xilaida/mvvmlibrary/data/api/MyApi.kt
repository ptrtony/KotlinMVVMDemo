package com.xilaida.mvvmlibrary.data.api

import com.xilaida.mvvmlibrary.app.App
import com.xilaida.mvvmlibrary.common.Constants
import com.xilaida.mvvmlibrary.data.db.entity.LoginEntity
import com.xilaida.mvvmlibrary.data.protocal.base.BaseResp
import com.xilaida.mvvmlibrary.utils.SPUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

/**
@author cjq
@Date 5/9/2020
@Time 10:42 PM
@Describe:
 */
interface MyApi {

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun userRegister(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Response<BaseResp<LoginEntity>>

    /**
     * 登陆
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<BaseResp<LoginEntity>>









    companion object{
        operator fun invoke():MyApi{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.wanandroid.com/")
                .client(initOkClient())
                .build()
                .create(MyApi::class.java)
        }

        private fun initOkClient():OkHttpClient{

            return OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
                .addInterceptor(initParamsInterceptor())
                .addInterceptor(NetworkConnectionInterceptor(App.context))
                .readTimeout(Constants.REQUEST_TIME, TimeUnit.SECONDS)
                .connectTimeout(Constants.REQUEST_TIME,TimeUnit.SECONDS)
                .build()
        }


        private fun initLogInterceptor(): Interceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        private fun initParamsInterceptor():Interceptor{
            return Interceptor {
                    chain ->
                val loginUsername = SPUtil.getString(Constants.LOGINUSERNAME,"")
                val loginUserPassword = SPUtil.getString(Constants.LOGINUSERPASSWORD,"")
                val request: Request
                request = if (loginUserPassword.isNullOrEmpty() && loginUsername.isNullOrEmpty()){
                    chain.request().newBuilder()
                        .addHeader("Content-Type","application/json")
                        .addHeader("charset","utf-8")
                        .build()
                }else{
                    chain.request().newBuilder()
                        .addHeader("Content-Type","application/json")
                        .addHeader("charset","utf-8")
                        .addHeader("Cookie","loginUserName=${loginUsername}")
                        .addHeader("Cookie","loginUserPassword=${loginUserPassword}")
                        .build()
                }

                chain.proceed(request)
            }
        }
    }
}