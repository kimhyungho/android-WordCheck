package com.appdev.wordcheck.data.repository

import com.appdev.wordcheck.data.model.domain.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepo {
    fun nicknameCheck(nickname: String): Completable
    fun normalLogin(nickname: String, password: String): Completable
}