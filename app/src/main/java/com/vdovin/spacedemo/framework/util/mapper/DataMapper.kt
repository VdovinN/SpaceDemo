package com.vdovin.spacedemo.framework.util.mapper

import com.vdovin.spacedemo.framework.database.model.SpaceEntity
import com.vdovin.spacedemo.framework.network.model.SpaceResponse
import com.vdovin.spacedemo.domain.Space
import com.vdovin.spacedemo.framework.util.EMPTY
import com.vdovin.spacedemo.framework.util.extension.convertTimestampToFormattedDate

fun Space.toSpaceEntity() = SpaceEntity(
    flightNumber = flightNumber,
    missionName = missionName,
    details = details,
    launchDate = launchDate,
    rocketName = rocketName,
    payloadMass = if (!payloadMass.isNaN()) payloadMass else null,
    infoLink = infoLink,
    youtubeVideoId = videoId
)

fun SpaceEntity.toSpace() = Space(
    flightNumber = flightNumber,
    missionName = missionName ?: EMPTY,
    details = details ?: EMPTY,
    launchDate = launchDate ?: EMPTY,
    rocketName = rocketName ?: EMPTY,
    payloadMass = payloadMass ?: Double.NaN,
    infoLink = infoLink ?: EMPTY,
    videoId = youtubeVideoId ?: EMPTY
)

fun SpaceResponse.toSpace() = Space(
    flightNumber = flightNumber,
    missionName = missionName ?: EMPTY,
    details = details ?: EMPTY,
    launchDate = launchDateUnix?.convertTimestampToFormattedDate() ?: EMPTY,
    payloadMass = rocket?.secondStage?.payloads?.get(0)?.payloadMassKg ?: Double.NaN,
    rocketName = rocket?.rocketName ?: EMPTY,
    infoLink = links?.wikipedia ?: EMPTY,
    videoId = links?.youtubeVideoId ?: EMPTY
)