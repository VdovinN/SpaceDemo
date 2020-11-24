package com.vdovin.spacedemo.data.repository

import com.vdovin.spacedemo.data.connection.InternetConnection
import com.vdovin.spacedemo.data.source.DatabaseDataSource
import com.vdovin.spacedemo.data.source.NetworkDataSource
import com.vdovin.spacedemo.data.util.exception.Failure
import com.vdovin.spacedemo.data.util.functional.Either
import com.vdovin.spacedemo.data.util.functional.flatMap
import com.vdovin.spacedemo.domain.Space

class SpaceRepository(
    private val networkDataSource: NetworkDataSource,
    private val databaseDataSource: DatabaseDataSource,
    private val internetConnection: InternetConnection
) {

    fun getLaunchByFlightNumber(flightNumber: Long) =
        databaseDataSource.getSpaceByFlightNumber(flightNumber)

    fun getLatestLaunches(): Either<Failure, List<Space>> =
        if (internetConnection.isConnected()) {
            networkDataSource.getSpaces()
                .flatMap { spaceList ->
                    databaseDataSource.saveAll(spaceList)
                    getSpacesOrFailure(Failure.ListNotAvailableError)
                }
        } else {
            getSpacesOrFailure(Failure.NetworkConnectionError)
        }

    private fun getSpacesOrFailure(failure: Failure): Either<Failure, List<Space>> {
        return databaseDataSource.getAll().flatMap { spaceList ->
            if (spaceList.isNotEmpty()) {
                Either.Right(spaceList)
            } else {
                Either.Left(failure)
            }
        }
    }
}