package com.appdev.wordcheck.data.remote.datasource

import com.appdev.wordcheck.data.model.network.response.ResponseNicknameCheck
import com.appdev.wordcheck.data.model.network.response.ResponseNormalLogin
import com.appdev.wordcheck.data.model.network.response.ResponseNormalSignUp
import io.reactivex.Single
import retrofit2.Call

interface UserRemoteDataSource {
    fun nicknameCheck(
        nickname: String
    ): Single<ResponseNicknameCheck>

    fun normalSignUp(
        nickname: String,
        password: String,
        secret_code: String
    ): Single<ResponseNormalSignUp>

    fun normalLogin(
        nickname: String, password: String
    ): Single<ResponseNormalLogin>
}