package com.appdev.wordcheck.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.data.model.domain.Word
import com.appdev.wordcheck.data.repository.WordRepo
import com.appdev.wordcheck.ui.base.BaseViewModel
import com.appdev.wordcheck.util.Event

class WordViewModel(private val repo: WordRepo) : BaseViewModel() {
    private val _getContentListTaskEvent = MutableLiveData<Event<List<Content>>>()
    val getContentListTaskEvent: LiveData<Event<List<Content>>> = _getContentListTaskEvent

    private val _getContentWordTaskEvent = MutableLiveData<Event<List<Word>>>()
    val getContentWordTaskEvent: LiveData<Event<List<Word>>> = _getContentWordTaskEvent

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

    fun getContentWordList() {
        addDisposable(
            repo.getContentWordList()
                .subscribe({
                    _getContentWordTaskEvent.postValue(Event(it))
                }, {
                    _toastMessage.postValue(Event("불러오기 실패"))
                })
        )
    }


}