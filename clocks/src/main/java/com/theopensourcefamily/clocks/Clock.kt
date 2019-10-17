package com.theopensourcefamily.clocks

import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit

class Clock(private val scheduler: Scheduler) {

  fun getClockObservable(): Observable<Long> = Observable
    .interval(INIT_DELAY, PERIOD, TimeUnit.MILLISECONDS, scheduler)
    .filter { it % WINDOW == ZERO }
}

const val INIT_DELAY = 10L
const val PERIOD = 1L
const val WINDOW = 10L
const val ZERO = 0L
