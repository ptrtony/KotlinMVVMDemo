package com.xilaida.mvvmlibrary.viewmodel

import androidx.lifecycle.ViewModel
import com.xilaida.mvvmlibrary.data.repository.UserRepository

/**
@author cjq
@Date 6/9/2020
@Time 2:47 PM
@Describe:
 */
open class BaseViewModel(private val userRepository: UserRepository):ViewModel() {
}