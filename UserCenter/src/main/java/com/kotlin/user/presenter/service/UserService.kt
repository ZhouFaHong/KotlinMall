package com.kotlin.user.presenter.service

import rx.Observable

interface UserService {

    fun register(mobile:String,verifyCode:String,pwd:String) : Observable<Boolean>
    fun login(mobile:String,pwd:String) : Observable<String>
    fun example() : Observable<Boolean>
}