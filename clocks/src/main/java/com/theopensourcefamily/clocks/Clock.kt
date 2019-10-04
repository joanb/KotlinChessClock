package com.theopensourcefamily.clocks

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class Clock {

  companion object {

    var whites = true

    fun getWhites() = Observable
      .interval(1, TimeUnit.MILLISECONDS)
      .filter { whites }
      .filter { it.toLong() % 1000 == 0L }
      .doOnNext { print("WHITES: $it") }

    fun getBlacks() = Observable
      .interval(1, TimeUnit.MILLISECONDS)
      .filter { !whites }
      .filter { it.toLong() % 1000 == 0L }
      .doOnNext { print("BLACKS: $it") }
  }

  fun pause() {}
  fun start() {}
}