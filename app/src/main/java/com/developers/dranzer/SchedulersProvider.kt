package com.developers.dranzer

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

interface SchedulersProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler
}

class SchedulersProviderImpl : SchedulersProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()

    override fun computation(): Scheduler = Schedulers.computation()

}