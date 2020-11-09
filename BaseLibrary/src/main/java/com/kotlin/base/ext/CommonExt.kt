package com.kotlin.base.ext

import com.kotlin.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable

fun <T> Observable<T>.execute(subscriber:BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>){
    this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
        .subscribeOn(rx.schedulers.Schedulers.io())
        .compose(lifecycleProvider.bindToLifecycle())
        .subscribe(subscriber)
}
