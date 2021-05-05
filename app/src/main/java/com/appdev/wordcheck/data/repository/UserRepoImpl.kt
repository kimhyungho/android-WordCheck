package com.appdev.wordcheck.data.repository

import android.annotation.SuppressLint
import com.appdev.wordcheck.data.remote.datasource.UserRemoteDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepoImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepo {
    @SuppressLint("CheckResult")
    override fun nicknameCheck(nickname: String): Completable {
        return Completable.fromSingle(
            userRemoteDataSource.nicknameCheck(nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    override fun normalLogin(nickname: String, password: String): Completable {
        return Completable.fromSingle(
            userRemoteDataSource.normalLogin(nickname, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    override fun normalSignUp(nickname: String, password: String, secret_code: String): Completable {
        return Completable.fromSingle(
            userRemoteDataSource.normalSignUp(nickname, password, secret_code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }
}