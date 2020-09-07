package com.xilaida.mvvmlibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xilaida.mvvmlibrary.data.repository.UserRepository

/**
@author cjq
@Date 6/9/2020
@Time 2:46 PM
@Describe:
 */
@Suppress("UNCHECKED_CAST")
class AuthViewFactory(
    private val userRepository: UserRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepository) as T
    }
}