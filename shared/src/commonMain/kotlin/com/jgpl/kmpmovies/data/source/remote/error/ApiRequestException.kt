package com.jgpl.kmpmovies.data.source.remote.error

class ApiRequestException(val code: Int, val description: String) : Exception()