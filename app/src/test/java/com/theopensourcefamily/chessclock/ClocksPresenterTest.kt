package com.theopensourcefamily.chessclock

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import com.nhaarman.mockitokotlin2.*
import com.theopensourcefamily.clocks.Clock
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class ClocksPresenterTest {
  private val interactions: Relay<ClocksView.Interaction> =
    PublishRelay.create<ClocksView.Interaction>()

  private val clockObservable: Relay<Long> =
    PublishRelay.create<Long>()

  private val view: ClocksView = mock {
    on { userInteractions } doReturn (interactions)
  }

  private val clock: Clock = mock {
    on { getClockObservable(any()) } doReturn clockObservable
  }

  private val scheduler = Schedulers.trampoline()

  private val presenter = ClocksPresenter(clock, scheduler)

  @After
  fun tearDown() {
    verifyNoMoreInteractions(view, clock)
  }

  @Before
  fun startsWithStoppedState() {
    presenter.bindView(view)

    verify(view).userInteractions
    verify(clock).getClockObservable()
    verify(view).render(ClockState.Stopped(30000, 30000, false))
  }

  @Test
  fun renderBlacksRunningIfWhitePressed() {
    interactions.accept(ClocksView.Interaction.WhitePressed)
    clockObservable.accept(1L)

    verify(view).render(ClockState.BlackRunning(30000, 29999))
  }

  @Test
  fun renderWhitesRunningIfBlackPressed() {
    interactions.accept(ClocksView.Interaction.BlackPressed)
    clockObservable.accept(1L)

    verify(view).render(ClockState.WhiteRunning(29999, 30000))
  }

  @Test
  fun renderStopWhenStopPressed() {
    interactions.accept(ClocksView.Interaction.BlackPressed)
    clockObservable.accept(1L)
    interactions.accept(ClocksView.Interaction.StopPressed)
    clockObservable.accept(1L)

    verify(view).render(ClockState.WhiteRunning(29999, 30000))
    verify(view).render(
      ClockState.Stopped(
        29999, 30000, true
      )
    )
  }

  @Test
  fun shouldNotEmitSameState() {
    interactions.accept(ClocksView.Interaction.StopPressed)
    clockObservable.accept(1L)

    verify(view).render(
      ClockState.Stopped(
        30000, 30000, true
      )
    )
  }

  @Test
  fun shouldEmitGameOver() {
    interactions.accept(ClocksView.Interaction.BlackPressed)
    for (ignore in 1..30002) {
      clockObservable.accept(1L)
    }

    verify(view, times(30002)).render(any())
    verify(view).render(ClockState.GameOver(0L, 30000L))
  }

  @Test
  fun shouldEmitGameOverOnlyOnce() {
    interactions.accept(ClocksView.Interaction.BlackPressed)
    for (ignore in 1..30010) {
      clockObservable.accept(1L)
    }

    verify(view, times(30002)).render(any())
    verify(view).render(ClockState.GameOver(0L, 30000L))
  }
}
