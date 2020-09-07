package com.xilaida.mvvmlibrary.data.api

import com.xilaida.mvvmlibrary.data.protocal.base.BaseResp
import com.xilaida.mvvmlibrary.utils.ApiException
import retrofit2.Response
import java.lang.StringBuilder

/**
@author cjq
@Date 6/9/2020
@Time 2:05 PM
@Describe:
 */
abstract class SafeApiRequest {
    suspend fun <T> apiRequest(call: suspend () -> Response<BaseResp<T>>): T {
        val response = call.invoke()
        val stringBuilder = StringBuilder()
        if (response.isSuccessful && response.body()!!.errorCode == 0) {
            return response.body()!!.data
        }
        if (response.isSuccessful && response.body()!!.errorCode != 0) {
            stringBuilder.append(response.body()!!.errorMsg)
        }else{
            stringBuilder.append(response.errorBody()!!.string())
        }
        throw ApiException(stringBuilder.toString())
    }
}