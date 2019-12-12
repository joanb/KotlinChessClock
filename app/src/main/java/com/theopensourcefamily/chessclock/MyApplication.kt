package com.theopensourcefamily.chessclock

import android.app.Application
import com.theopensourcefamily.chessclock.modules.presenter
import com.theopensourcefamily.chessclock.modules.schedulers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {

      androidContext(this@MyApplication)

      modules(listOf(presenter, schedulers))

    }
  }

}
