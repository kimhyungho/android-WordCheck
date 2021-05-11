package com.appdev.wordcheck.data.model.mapper

import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.data.model.network.response.ResponseGetList

object ContentMapper : Mapper<List<ResponseGetList>, List<Content>> {
    override fun map(input: List<ResponseGetList>): List<Content> {
        val list = mutableListOf<Content>()
        for(it in input) {
            list.add(Content(it.contents))
        }

        return list.toList()
    }
}