package com.vdovin.spacedemo.data.source

import com.vdovin.spacedemo.data.util.exception.Failure
import com.vdovin.spacedemo.data.util.functional.Either
import com.vdovin.spacedemo.domain.Space

interface DatabaseDataSource {

    fun saveAll(spaces: List<Space>) : Either<Failure, Unit>

    fun getAll() : Either<Failure, List<Space>>

    fun getSpaceByFlightNumber(flightNumber: Long): Either<Failure, Space>

}