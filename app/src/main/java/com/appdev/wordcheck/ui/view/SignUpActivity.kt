package com.appdev.wordcheck.ui.view

import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivitySignUpBinding
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.viewmodel.UserViewModel
import com.appdev.wordcheck.util.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, UserViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_sign_up
    override val viewModel: UserViewModel by viewModel()

    override fun initStartView() {
        initClickEvent()
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
        observerNicknameCheckResult()
    }

    private fun observerNicknameCheckResult() {
        viewModel.nicknameCheckTaskEvent.observe(this, EventObserver { success ->
            if (success) {

            }
        })
    }


    private fun initClickEvent() {
        viewDataBinding.btnCheckNickname.setOnClickListener {

        }
    }

    private fun onNicknameCheckButtonClick() {
        val nickname = viewDataBinding.etNickname.text.toString()


    }

//        btn_check_nickname.setOnClickListener {


//            UserRemoteDataSourceImpl(userService.create(UserService::class.java)).nicknameCheck(
//                et_nickname.text.toString()
//            ).enqueue(object : Callback<ResponseNicknameCheck> {
//                override fun onFailure(call: Call<ResponseNicknameCheck>, t: Throwable) {
//                    Toast.makeText(this@SignUpActivity, "이메일 체크 실패", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseNicknameCheck>,
//                    response: Response<ResponseNicknameCheck>
//                ) {
//                    if (response.isSuccessful) {
//                        if (response.body()!!.msg == "success") {
//                            txt_email_check.text = "사용 가능한 닉네임 입니다."
//                            btn_signup2.isEnabled = true
//                        } else {
//                            txt_email_check.text = "이미 존재하는 닉네임 입니다."
//                        }
//                    } else {
//                        txt_email_check.text = "이미 존재하는 닉네임 입니다."
//                    }
//                }
//            })

//        }


}