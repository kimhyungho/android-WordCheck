package com.appdev.wordcheck.data.repository

import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.data.model.domain.Word
import io.reactivex.Completable
import io.reactivex.Single

interface WordRepo {
    fun getContentList(): Single<List<Content>>
    fun getContentWordList(content: String): Single<List<Word>>
    fun addWord(contents: String, spelling: String, category: String, meaning: String): Completable
    fun deleteWord(id: Int): Completable
    fun searchWord(target: String): Single<List<Word>>
    fun scoreWord(id: Int, state: String): Completable
    fun getWrongWord(wrong_count: Int): Single<List<Word>>
}