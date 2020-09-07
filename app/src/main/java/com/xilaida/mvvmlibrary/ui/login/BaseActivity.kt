package com.xilaida.mvvmlibrary.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.xilaida.mvvmlibrary.listener.AuthListener
import org.kodein.di.KodeinAware

/**
@author cjq
@Date 6/9/2020
@Time 10:25 AM
@Describe:
 */

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity(),AuthListener,KodeinAware {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding: T =
//            DataBindingUtil.setContentView(this, layoutId())


    }

    abstract fun layoutId(): Int
}