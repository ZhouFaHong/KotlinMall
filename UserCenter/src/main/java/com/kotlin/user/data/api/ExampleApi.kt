package com.kotlin.user.data.api

import retrofit2.http.GET
import rx.Observable

interface ExampleApi {

    @GET("/example")
    fun example(): String
}