package com.kotlin.user.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.service.impl.UserServiceImpl
import com.kotlin.user.presenter.view.RegisterView
import org.jetbrains.anko.Android
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService:UserServiceImpl
    fun register(mobile:String,verifyCode:String,pwd:String){

        /*
            业务逻辑
        * */
//        userService.register(mobile,verifyCode,pwd)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : BaseSubscriber<Boolean>(){
//                override fun onNext(t: Boolean) {
//                    mView.onRegisterResult(t)
//                }
//            })
        userService.register(mobile, verifyCode, pwd)
            .execute(object : BaseSubscriber<Boolean>(){
                override fun onNext(t: Boolean) {
                    mView.onRegisterResult(t)
                }
            },lifecycleProvider)
    }

    fun login(mobile:String,pwd:String){
        /*
            业务逻辑
        * */
        userService.login(mobile,pwd)
            .execute(object :BaseSubscriber<String>(){
                override fun onNext(t: String) {
                    super.onNext(t)
                    mView.onLoginResult(t)
                }
            },lifecycleProvider)

    }
    fun example(){
        userService.example()
            .execute(object :BaseSubscriber<Boolean>(){
                override fun onNext(t: Boolean) {
                    super.onNext(t)
                }
            },lifecycleProvider)

    }
}