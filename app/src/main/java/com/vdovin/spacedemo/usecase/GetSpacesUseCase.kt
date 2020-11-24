package com.vdovin.spacedemo.usecase

import com.vdovin.spacedemo.data.repository.SpaceRepository
import com.vdovin.spacedemo.data.util.exception.Failure
import com.vdovin.spacedemo.data.util.functional.Either
import com.vdovin.spacedemo.domain.Space
import com.vdovin.spacedemo.usecase.base.UseCase


class GetSpacesUseCase(
    private val spaceRepository: SpaceRepository
) : UseCase<List<Space>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<Space>> =
        spaceRepository.getLatestLaunches()

}
