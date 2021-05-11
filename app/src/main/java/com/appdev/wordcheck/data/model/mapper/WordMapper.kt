package com.appdev.wordcheck.data.model.mapper

import com.appdev.wordcheck.data.model.domain.Word
import com.appdev.wordcheck.data.model.network.response.ResponseGetWord

object WordMapper : Mapper<List<ResponseGetWord>, List<Word>> {
    override fun map(input: List<ResponseGetWord>): List<Word> {
        val list = mutableListOf<Word>()
        for (it in input) {
            list.add(
                Word(
                    it.id,
                    it.contents,
                    it.spelling,
                    it.category,
                    it.meaning,
                    it.remember,
                    it.wrong_count,
                    it.account
                )
            )
        }

        return list.toList()
    }
}