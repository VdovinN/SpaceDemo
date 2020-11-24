package com.vdovin.spacedemo.presentation.util.mapper

import com.vdovin.spacedemo.presentation.screens.detail.model.SpaceDetailsView
import com.vdovin.spacedemo.presentation.screens.list.model.SpacesView
import com.vdovin.spacedemo.domain.Space
import com.vdovin.spacedemo.framework.util.EMPTY

fun Space.toSpacesView() = SpacesView(
    flightNumber = flightNumber,
    missionName = missionName,
    launchDate = launchDate,
    youtubeVideoId = videoId
)

fun Space.toSpaceDetailsView() = SpaceDetailsView(
    flightNumber = flightNumber,
    missionName = missionName,
    launchDate = launchDate,
    details = details,
    rocketName = rocketName,
    payloadMass = if (payloadMass.isNaN()) EMPTY else payloadMass.toInt().toString(),
    wikiLink = infoLink,
    youtubeVideoId = videoId
)