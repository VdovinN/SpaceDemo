package com.vdovin.spacedemo.framework.database

import com.vdovin.spacedemo.framework.database.dao.SpaceDao
import com.vdovin.spacedemo.data.source.DatabaseDataSource
import com.vdovin.spacedemo.data.util.functional.Either
import com.vdovin.spacedemo.data.util.functional.map
import com.vdovin.spacedemo.domain.Space
import com.vdovin.spacedemo.framework.util.mapper.toSpace
import com.vdovin.spacedemo.framework.util.mapper.toSpaceEntity

class DatabaseDataSourceImpl(
    private val spaceDao: SpaceDao
) : DatabaseDataSource {

    override fun saveAll(spaces: List<Space>) =
        Either.Right(spaceDao.insert(spaces.map { space -> space.toSpaceEntity() }))

    override fun getAll() =
        Either.Right(spaceDao.selectAllLaunches().map { spaceEntity -> spaceEntity.toSpace() })

    override fun getSpaceByFlightNumber(flightNumber: Long) =
        Either.Right(spaceDao.selectLaunchByFlightNumber(flightNumber)).map { it.toSpace() }


}