package com.vdovin.spacedemo.data.util.exception

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
    object ListNotAvailableError : Failure()
}
