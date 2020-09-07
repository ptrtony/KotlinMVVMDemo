package com.xilaida.mvvmlibrary.ui.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.xilaida.mvvmlibrary.R
import com.xilaida.mvvmlibrary.data.db.entity.LoginEntity
import com.xilaida.mvvmlibrary.databinding.ActivityRegisterBinding
import com.xilaida.mvvmlibrary.ext.enable
import com.xilaida.mvvmlibrary.ext.toast
import com.xilaida.mvvmlibrary.listener.AuthListener
import com.xilaida.mvvmlibrary.viewmodel.AuthViewFactory
import com.xilaida.mvvmlibrary.viewmodel.RegisterModelFactory
import com.xilaida.mvvmlibrary.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.include_progress_bar.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

/**
@author cjq
@Date 6/9/2020
@Time 10:18 AM
@Describe:
 */
class RegisterActivity : AppCompatActivity(), AuthListener,KodeinAware{

    override val kodein by kodein()
    private val factory : AuthViewFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRegisterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_register)
        val viewModel = ViewModelProviders.of(this,factory).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.mAuthListener = this
        mTitleBarHb.onBackClickListener {
            finish()
        }
        mRegisterBtn.enable(mUserNameEtn) { isEnable() }
        mRegisterBtn.enable(mPwdEtn) { isEnable() }
        mRegisterBtn.enable(mRePwdEtn) { isEnable() }
    }


    private fun isEnable(): Boolean {
        return mUserNameEtn.text.toString().isNotEmpty() &&
                mPwdEtn.text.toString().isNotEmpty() &&
                mRePwdEtn.text.toString().isNotEmpty()
    }

    override fun onStarted() {
        progress_bar.visibility = View.VISIBLE

    }

    override fun onSuccess(loginEntity: LoginEntity) {
        progress_bar.visibility = View.GONE
        toast("注册成功")
    }

    override fun onFailure(error: String) {
        progress_bar.visibility = View.GONE
    }


}