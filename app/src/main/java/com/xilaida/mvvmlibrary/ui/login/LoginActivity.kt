package com.xilaida.mvvmlibrary.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xilaida.mvvmlibrary.ui.MainActivity
import com.xilaida.mvvmlibrary.R
import com.xilaida.mvvmlibrary.data.db.entity.LoginEntity
import com.xilaida.mvvmlibrary.databinding.ActivityLoginBinding
import com.xilaida.mvvmlibrary.ext.enable
import com.xilaida.mvvmlibrary.ext.snackbar
import com.xilaida.mvvmlibrary.listener.AuthListener
import com.xilaida.mvvmlibrary.viewmodel.AuthViewFactory
import com.xilaida.mvvmlibrary.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.include_progress_bar.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel =
            ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.mAuthListener = this

        viewModel.getLoginedInUser().observe(this, Observer {
            if (it != null) {
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }
        })


        mLoginBtn.enable(mUserNameEtn) { isEnable() }
        mLoginBtn.enable(mPwdEtn) { isEnable() }

        initClick()
    }

    private fun initClick() {
        mTitleBarHb.onRightClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun isEnable(): Boolean {
        return mUserNameEtn.text.toString().isNotEmpty() && mPwdEtn.text.toString().isNotEmpty()
    }

    override fun onStarted() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun onSuccess(loginEntity: LoginEntity) {
        progress_bar.visibility = View.GONE
        root_view.snackbar("登录成功")
    }

    override fun onFailure(error: String) {
        progress_bar.visibility = View.GONE
        root_view.snackbar("onFailure:$error")
    }

}
