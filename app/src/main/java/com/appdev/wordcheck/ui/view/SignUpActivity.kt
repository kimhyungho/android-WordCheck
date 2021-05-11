package com.appdev.wordcheck.ui.view

import android.text.Editable
import android.text.TextWatcher
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivitySignUpBinding
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.viewmodel.UserViewModel
import com.appdev.wordcheck.util.EventObserver
import com.appdev.wordcheck.util.setupToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, UserViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_sign_up
    override val viewModel: UserViewModel by viewModel()

    override fun initStartView() {
        initClickEvent()
        initChangeEvent()
        setupToast(this, viewModel.toastMessage)
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
        observerNicknameCheckResult()
        observerSignUpResult()
    }

    private fun observerNicknameCheckResult() {
        viewModel.nicknameCheckTaskEvent.observe(this, EventObserver { success ->
            if (success) {
                viewDataBinding.txtNicknameCheck.text = "사용 가능한 닉네임"
                viewDataBinding.btnSignup2.isEnabled = true
            } else {
                viewDataBinding.txtNicknameCheck.text = "이미 사용중인 닉네임"
                viewDataBinding.btnSignup2.isEnabled = false
            }
        })
    }

    private fun observerSignUpResult() {
        viewModel.signUpTaskEvent.observe(this, EventObserver { success ->
            if (success) {
                // 회원가입 성공시
            } else {
                // 회원가입 실패시
            }
        })
    }


    private fun initClickEvent() {
        viewDataBinding.btnCheckNickname.setOnClickListener { onNicknameCheckButtonClick() }
        viewDataBinding.btnSignup2.setOnClickListener { onSignUpButtonClick() }
    }

    private fun initChangeEvent() {
        onNicknameEditTextChange()

    }

    private fun onNicknameEditTextChange() {
        viewDataBinding.etNickname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewDataBinding.btnCheckNickname.isEnabled = p0!!.isNotEmpty()
                viewDataBinding.btnSignup2.isEnabled = false
            }
        })

    }

    private fun onSignUpButtonClick() {
        val nickname = viewDataBinding.etNickname.text.toString()
        val password = viewDataBinding.etPassword.text.toString()
        val secret_code = viewDataBinding.etSecretCode.text.toString()

        viewModel.signUp(nickname, password, secret_code)

    }

    private fun onNicknameCheckButtonClick() {
        val nickname = viewDataBinding.etNickname.text.toString()

        viewModel.nicknameCheck(nickname)

    }


}