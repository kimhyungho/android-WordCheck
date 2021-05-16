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

    private val _addWordTaskEvent = MutableLiveData<Event<Boolean>>()
    val addWordTaskEvent: LiveData<Event<Boolean>> = _addWordTaskEvent

    private val _deleteWordTaskEvent = MutableLiveData<Event<Boolean>>()
    val deleteWordTaskEvent: LiveData<Event<Boolean>> = _deleteWordTaskEvent

    private val _searchWordTaskEvent = MutableLiveData<Event<List<Word>>>()
    val searchWordTaskEvent: LiveData<Event<List<Word>>> = _searchWordTaskEvent

    private val _scoreWordTaskEvent = MutableLiveData<Event<Boolean>>()
    val scoreWordTaskEvent: LiveData<Event<Boolean>> = _scoreWordTaskEvent


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

    fun getContentWordList(content: String) {
        addDisposable(
            repo.getContentWordList(content)
                .subscribe({
                    _getContentWordTaskEvent.postValue(Event(it))
                }, {
                    _toastMessage.postValue(Event("불러오기 실패"))
                })
        )
    }

    fun addWord(contents: String, spelling: String, category: String, meaning: String) {

        addDisposable(
            repo.addWord(contents, spelling, category, meaning)
                .subscribe({
                    _addWordTaskEvent.postValue(Event(true))
                    _toastMessage.postValue(Event("단어등록 성공"))
                }, {
                    _addWordTaskEvent.postValue(Event(false))
                    _toastMessage.postValue(Event("단어등록 실패"))
                })
        )
    }

    fun deleteWord(id: Int, contents: String) {
        addDisposable(
            repo.deleteWord(id)
                .subscribe({
                    getContentWordList(contents)
                    _deleteWordTaskEvent.postValue(Event(true))
                    _toastMessage.postValue(Event("단어삭제 성공"))
                }, {
                    _deleteWordTaskEvent.postValue(Event(false))
                    _toastMessage.postValue(Event("단어삭제 실패"))
                })
        )
    }

    fun searchWord(target: String) {
        addDisposable(
            repo.searchWord(target)
                .subscribe({
                    _searchWordTaskEvent.postValue(Event(it))
                }, {
                    _toastMessage.postValue(Event("단어검색 실패"))
                })
        )
    }

    fun scoreWord(id: Int, state: String) {
        addDisposable(
            repo.scoreWord(id, state)
                .subscribe({
                    _scoreWordTaskEvent.postValue(Event(true))
                    _toastMessage.postValue(Event("채점 완료"))

                }, {
                    _scoreWordTaskEvent.postValue(Event(false))
                    _toastMessage.postValue(Event("채점 완료"))
                })
        )

    }

    fun getWrongWord(wrong_count: Int) {
        addDisposable(
            repo.getWrongWord(wrong_count)
                .subscribe({
                    _getContentWordTaskEvent.postValue(Event(it))
                }, {
                    _toastMessage.postValue(Event("단어 불러오기 실패"))
                })
        )
    }


}