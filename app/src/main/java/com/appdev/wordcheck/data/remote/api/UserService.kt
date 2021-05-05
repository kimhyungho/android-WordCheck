package com.appdev.wordcheck.data.remote.api

import com.appdev.wordcheck.data.model.network.response.ResponseNicknameCheck
import com.appdev.wordcheck.data.model.network.response.ResponseNormalLogin
import com.appdev.wordcheck.data.model.network.response.ResponseNormalSignUp
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    @POST("accounts/nickname_check/")
    @FormUrlEncoded
    fun nicknameCheck(
        @Field("nickname") nickname: String
    ): Single<ResponseNicknameCheck>

    @POST("accounts/normal_login/")
    @FormUrlEncoded
    fun normalLogin(
        @Field("nickname") nickname: String,
        @Field("password") password: String
    ): Single<ResponseNormalLogin>

    @POST("accounts/normal_signup/")
    @FormUrlEncoded
    fun normalSignUp(
        @Field("nickname") nickname: String,
        @Field("password") password: String,
        @Field("secret_code") secret_code: String
    ): Single<ResponseNormalSignUp>
}

