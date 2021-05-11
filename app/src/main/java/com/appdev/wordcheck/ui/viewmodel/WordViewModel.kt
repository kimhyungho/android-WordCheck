package com.appdev.wordcheck.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.data.repository.WordRepo
import com.appdev.wordcheck.ui.base.BaseViewModel
import com.appdev.wordcheck.util.Event

class WordViewModel(private val repo: WordRepo) : BaseViewModel() {
    private val _getContentListTaskEvent = MutableLiveData<Event<List<Content>>>()
    val getContentListTaskEvent: LiveData<Event<List<Content>>> = _getContentListTaskEvent

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage


    fun getContentList() {
        addDisposable(
            repo.getContentList()
                .subscribe({
                    _getContentListTaskEvent.postValue(Event(it))

                }, {
                    _toastMessage.postValue(Event("불러오기 실패"))
                })
        )
    }


}