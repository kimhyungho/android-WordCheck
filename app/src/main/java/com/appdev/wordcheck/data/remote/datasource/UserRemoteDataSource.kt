package com.appdev.wordcheck.data.remote.datasource

import com.appdev.wordcheck.data.model.network.response.ResponseNicknameCheck
import com.appdev.wordcheck.data.model.network.response.ResponseNormalLogin
import com.appdev.wordcheck.data.model.network.response.ResponseNormalSignUp
import retrofit2.Call

interface UserRemoteDataSource {
    fun nicknameCheck(
        nickname: String
    ): Call<ResponseNicknameCheck>

    fun normalSignUp(
        nickname: String,
        password: String,
        secret_code: String
    ): Call<ResponseNormalSignUp>

    fun normalLogin(
        nickname: String, password: String
    ): Call<ResponseNormalLogin>
}