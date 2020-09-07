package com.xilaida.mvvmlibrary.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xilaida.mvvmlibrary.data.db.entity.LoginEntity
import com.xilaida.mvvmlibrary.data.repository.UserRepository
import com.xilaida.mvvmlibrary.ext.toast
import com.xilaida.mvvmlibrary.listener.AuthListener
import com.xilaida.mvvmlibrary.utils.ApiException
import com.xilaida.mvvmlibrary.utils.Coroutines
import com.xilaida.mvvmlibrary.utils.NoInternetException

/**
@author cjq
@Date 5/9/2020
@Time 4:18 PM
@Describe:
 */
class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    var username: String? = null
    var password: String? = null
    var mAuthListener: AuthListener? = null

    fun getLoginedInUser():LiveData<LoginEntity>{
        return userRepository.getUser()
    }


    fun onLoginClick(view: View) {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            view.context.toast("用户名或密码不能为空")
            return
        }
        mAuthListener?.onStarted()
        Coroutines.main {
            try {
                val response =  userRepository.userLogin(username!!,password!!)
                userRepository.saveUser(response)
                mAuthListener?.onSuccess(response)

            }catch (e:ApiException){
                mAuthListener?.onFailure(e.message?:"")
            }catch (e:NoInternetException){
                mAuthListener?.onFailure(e.message?:"")
            }

        }


    }
}