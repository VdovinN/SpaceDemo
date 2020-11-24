package com.vdovin.spacedemo.usecase

import com.vdovin.spacedemo.data.repository.SpaceRepository
import com.vdovin.spacedemo.data.util.exception.Failure
import com.vdovin.spacedemo.data.util.functional.Either
import com.vdovin.spacedemo.domain.Space
import com.vdovin.spacedemo.usecase.base.UseCase

class GetSpaceDetailsUseCase(
    private val spaceRepository: SpaceRepository
) : UseCase<Space, GetSpaceDetailsUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Space> =
        spaceRepository.getLaunchByFlightNumber(params.flightNumber)

    data class Params(val flightNumber: Long)

}