package com.theopensourcefamily.clocks

import io.reactivex.rxjava3.schedulers.TestScheduler
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import java.util.concurrent.TimeUnit

internal class ClockTest {

  val testScheduler = TestScheduler()

  @Test
  fun clockShouldNotEmitAtStart() {
    val observer = Clock(testScheduler).getClockObservable().test()

    testScheduler.advanceTimeBy(9, TimeUnit.MILLISECONDS)

    observer.assertEmpty()
  }

  @Test
  fun clockEmitsEvery10Millis() {
    val observer = Clock(testScheduler).getClockObservable().test()

    testScheduler.advanceTimeBy(20, TimeUnit.MILLISECONDS)
    val values = observer.values()

    assertThat(values, hasSize(2))
  }
}
