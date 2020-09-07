package com.xilaida.mvvmlibrary.data.repository

import androidx.lifecycle.LiveData
import com.xilaida.mvvmlibrary.data.api.MyApi
import com.xilaida.mvvmlibrary.data.api.SafeApiRequest
import com.xilaida.mvvmlibrary.data.db.AppDatabase
import com.xilaida.mvvmlibrary.data.db.entity.LoginEntity

/**
@author cjq
@Date 5/9/2020
@Time 10:52 PM
@Describe:
 */
class UserRepository(
    private val appDatabase: AppDatabase
) : SafeApiRequest() {

    //登录
    suspend fun userLogin(username: String, password: String): LoginEntity {
        return apiRequest { MyApi.invoke().userLogin(username, password) }
    }

    suspend fun saveUser(loginEntity: LoginEntity){
        appDatabase.getUserDao().upInsert(loginEntity)
    }

    fun getUser():LiveData<LoginEntity>{
        return appDatabase.getUserDao().update()
    }
    suspend fun userRegister(username: String, password: String, repassword: String): LoginEntity {
        return apiRequest { MyApi.invoke().userRegister(username, password, repassword) }
    }
}