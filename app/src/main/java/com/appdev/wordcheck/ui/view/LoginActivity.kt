package com.appdev.wordcheck.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivityLoginBinding
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.viewmodel.UserViewModel
import com.appdev.wordcheck.util.EventObserver
import com.appdev.wordcheck.util.setupToast
import com.appdev.wordcheck.util.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<ActivityLoginBinding, UserViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_login
    override val viewModel: UserViewModel by viewModel()

    override fun initStartView() {
        initClickEvent()
        setupToast(this, viewModel.toastMessage)
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
        observeLoginResult()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }

    override fun onStart() {
        super.onStart()
        // 자동로그인
    }

    private fun observeLoginResult() {
        viewModel.loginTaskEvent.observe(this, EventObserver {
            startMainActivity()
        })
    }

    private fun initClickEvent() {
        viewDataBinding.btnLogin.setOnClickListener { onLoginButtonClick() }
        viewDataBinding.btnSignup.setOnClickListener { onSignUpButtonClick() }
    }


    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    private fun onLoginButtonClick() {
        val nickname = viewDataBinding.etNickname.text.toString()
        val password = viewDataBinding.etPassword.text.toString()

        if (nickname.isNotEmpty() && password.isNotEmpty()) {
            viewModel.normalLogin(nickname, password)

        } else {
            shortToast("빈 칸을 채워주세요")
        }
    }

    private fun onSignUpButtonClick() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }


}