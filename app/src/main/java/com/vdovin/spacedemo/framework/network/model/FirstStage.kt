package com.vdovin.spacedemo.framework.network.model

import com.google.gson.annotations.SerializedName

data class FirstStage(@SerializedName("cores") val cores: List<Core>?)