package com.appdev.wordcheck.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdev.wordcheck.data.model.domain.User
import com.appdev.wordcheck.data.repository.UserRepo
import com.appdev.wordcheck.ui.base.BaseViewModel
import com.appdev.wordcheck.util.Event

class UserViewModel(private val repo: UserRepo) : BaseViewModel() {
    private val _nicknameCheckTaskEvent = MutableLiveData<Event<Boolean>>()
    val nicknameCheckTaskEvent: LiveData<Event<Boolean>> = _nicknameCheckTaskEvent

    private val _loginTaskEvent = MutableLiveData<Event<Boolean>>()
    val loginTaskEvent: LiveData<Event<Boolean>> = _loginTaskEvent

    private val _signUpTaskEvent = MutableLiveData<Event<Boolean>>()
    val signUpTaskEvent: LiveData<Event<Boolean>> = _signUpTaskEvent

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    fun nicknameCheck(nickname: String) {
        addDisposable(
            repo.nicknameCheck(nickname)
                .subscribe({
                    // 닉네임 체크 성공
                    _nicknameCheckTaskEvent.postValue(Event(true))
                    _toastMessage.postValue(Event("사용 가능한 닉네임"))

                }, {
                    // 닉네임 체크 실패
                    _toastMessage.postValue(Event("이미 존재하는 닉네임"))
                })
        )
    }

    fun normalLogin(nickname: String, password: String) {
        addDisposable(
            repo.normalLogin(nickname, password)
                .subscribe({
                    _loginTaskEvent.postValue(Event(true))
                    _toastMessage.postValue(Event("로그인 성공"))
                }, {
                    Log.d("kkkk", it.toString())
                    _toastMessage.postValue(Event("로그인 실패"))
                })
        )
    }

    companion object {
        private val TAG = UserViewModel::class.java.simpleName
    }
}