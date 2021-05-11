package com.appdev.wordcheck.data.model.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}