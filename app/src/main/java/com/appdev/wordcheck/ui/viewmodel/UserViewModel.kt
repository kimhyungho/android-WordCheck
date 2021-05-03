package com.appdev.wordcheck.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdev.wordcheck.data.model.domain.User
import com.appdev.wordcheck.data.repository.UserRepo
import com.appdev.wordcheck.ui.base.BaseViewModel
import com.appdev.wordcheck.util.Event

class UserViewModel(private val repo: UserRepo) : BaseViewModel() {
    private val _loginTaskEvent = MutableLiveData<Event<User>>()
    val loginTaskEvent: LiveData<Event<User>> = _loginTaskEvent

    private val _signUpTaskEvent = MutableLiveData<Event<Boolean>>()
    val signUpTaskEvent: LiveData<Event<Boolean>> = _signUpTaskEvent

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage
}