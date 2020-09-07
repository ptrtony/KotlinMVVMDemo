package com.xilaida.mvvmlibrary.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.xilaida.mvvmlibrary.data.repository.UserRepository
import com.xilaida.mvvmlibrary.ext.toast
import com.xilaida.mvvmlibrary.listener.AuthListener
import com.xilaida.mvvmlibrary.utils.ApiException
import com.xilaida.mvvmlibrary.utils.Coroutines
import com.xilaida.mvvmlibrary.utils.NoInternetException

/**
@author cjq
@Date 6/9/2020
@Time 12:06 PM
@Describe:
 */
class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    var username: String? = null
    var password: String? = null
    var repassowrd: String? = null
    var mAuthListener: AuthListener? = null
    fun onRegisterClick(view: View) {
        if (username.isNullOrEmpty() || password.isNullOrEmpty() || repassowrd.isNullOrEmpty()) {
            view.context.toast("用户名和密码不能为空")
            return
        }

        if (password != repassowrd) {
            view.context.toast("两次输入的密码不同")
            return
        }
        mAuthListener?.onStarted()
        Coroutines.main {
            val response = userRepository.userRegister(username!!, password!!, repassowrd!!)
            try {
                userRepository.saveUser(response)
                mAuthListener?.onSuccess(response)
            }catch (e:ApiException){
                mAuthListener?.onFailure(e.message?:"")
            }catch (e: NoInternetException){
                mAuthListener?.onFailure(e.message?:"")
            }
        }
    }
}