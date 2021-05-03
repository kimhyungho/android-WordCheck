package com.appdev.wordcheck.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.appdev.wordcheck.R
import com.appdev.wordcheck.data.model.network.response.ResponseNicknameCheck
import com.appdev.wordcheck.data.remote.api.UserService
import com.appdev.wordcheck.data.remote.datasource.UserRemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_nickname
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val userService = Retrofit.Builder()
            .baseUrl("http://sulrae.com:9900/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        et_nickname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn_check_nickname.isEnabled = p0!!.isNotEmpty()
                txt_email_check.text = ""
                btn_signup2.isEnabled = false
            }
        })

        btn_check_nickname.setOnClickListener {
            UserRemoteDataSourceImpl(userService.create(UserService::class.java)).nicknameCheck(
                et_nickname.text.toString()
            ).enqueue(object : Callback<ResponseNicknameCheck> {
                override fun onFailure(call: Call<ResponseNicknameCheck>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity, "이메일 체크 실패", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseNicknameCheck>,
                    response: Response<ResponseNicknameCheck>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.msg == "success") {
                            txt_email_check.text = "사용 가능한 닉네임 입니다."
                            btn_signup2.isEnabled = true
                        } else {
                            txt_email_check.text = "이미 존재하는 닉네임 입니다."
                        }
                    } else {
                        txt_email_check.text = "이미 존재하는 닉네임 입니다."
                    }
                }
            })

        }


    }

}