package com.theopensourcefamily.chessclock.modules

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.dsl.module

val schedulers = module {

  single { provideMainScheduler() }

}

fun provideMainScheduler(): Scheduler? = AndroidSchedulers.mainThread()
