package com.theopensourcefamily.chessclock.modules

import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.dsl.module

val schedulers = module {
  single { AndroidSchedulers.mainThread() }
}
