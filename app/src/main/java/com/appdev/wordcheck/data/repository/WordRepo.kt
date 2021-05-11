package com.appdev.wordcheck.data.repository

import com.appdev.wordcheck.data.model.domain.Content
import io.reactivex.Single

interface WordRepo {
    fun getContentList(): Single<List<Content>>
    fun addWord(word: String)
}