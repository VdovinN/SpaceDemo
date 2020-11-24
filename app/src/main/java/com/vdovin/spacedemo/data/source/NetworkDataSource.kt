package com.vdovin.spacedemo.data.source

import com.vdovin.spacedemo.data.util.exception.Failure
import com.vdovin.spacedemo.data.util.functional.Either
import com.vdovin.spacedemo.domain.Space

interface NetworkDataSource {

    fun getSpaces() : Either<Failure, List<Space>>

}