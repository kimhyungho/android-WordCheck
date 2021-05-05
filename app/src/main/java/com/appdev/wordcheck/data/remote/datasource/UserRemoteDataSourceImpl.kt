package com.appdev.wordcheck.data.remote.datasource

import com.appdev.wordcheck.data.model.network.response.ResponseNicknameCheck
import com.appdev.wordcheck.data.model.network.response.ResponseNormalLogin
import com.appdev.wordcheck.data.model.network.response.ResponseNormalSignUp
import com.appdev.wordcheck.data.remote.api.UserService
import io.reactivex.Single

class UserRemoteDataSourceImpl(private val service: UserService) : UserRemoteDataSource {
    override fun nicknameCheck(
        nickname: String
    ): Single<ResponseNicknameCheck> {
        return service.nicknameCheck(nickname)
    }

    override fun normalSignUp(
        nickname: String,
        password: String,
        secret_code: String
    ): Single<ResponseNormalSignUp> {
        return service.normalSignUp(nickname, password, secret_code)
    }

    override fun normalLogin(
        nickname: String, password: String
    ): Single<ResponseNormalLogin> {
        return service.normalLogin(nickname, password)
    }
}