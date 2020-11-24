package com.vdovin.spacedemo.framework.network.api

import com.vdovin.spacedemo.framework.network.model.SpaceResponse
import retrofit2.Call
import retrofit2.http.GET

interface SpaceApi {
    @GET("v2/launches")
    fun getAllPastLaunches(): Call<List<SpaceResponse>>
}