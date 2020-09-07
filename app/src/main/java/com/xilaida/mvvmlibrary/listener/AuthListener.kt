package com.xilaida.mvvmlibrary.listener

import com.xilaida.mvvmlibrary.data.db.entity.LoginEntity

/**
@author cjq
@Date 5/9/2020
@Time 8:17 PM
@Describe:
 */
interface AuthListener {
    fun onStarted()
    fun onSuccess(loginEntity: LoginEntity)
    fun onFailure(error:String)
}