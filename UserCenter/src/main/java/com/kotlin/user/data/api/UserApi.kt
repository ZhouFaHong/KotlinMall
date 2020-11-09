package com.kotlin.user.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.protocol.LoginReq
import com.kotlin.user.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

interface UserApi {
    @POST("/user/register")
    fun register(@Body req: RegisterReq) : Observable<BaseResp<String>>

    @POST("/user/login")
    fun login(@Body req: LoginReq) : Observable<BaseResp<String>>

    @GET("topics")
    fun example() : Observable<BaseResp<String>>
}