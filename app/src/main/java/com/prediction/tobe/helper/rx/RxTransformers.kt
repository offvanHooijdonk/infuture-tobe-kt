package com.prediction.tobe.helper.rx

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> schedulers(): Observable.Transformer<T, T>? {
    return Observable.Transformer { source -> source.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread()) }
}