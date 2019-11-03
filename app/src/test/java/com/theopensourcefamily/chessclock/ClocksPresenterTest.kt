package com.theopensourcefamily.chessclock

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import com.nhaarman.mockitokotlin2.*
import com.theopensourcefamily.clocks.Clock
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

  private val presenter = ClocksPresenter(clock)

  @After
  fun tearDown() {
    verifyNoMoreInteractions(view, clock)
  }
  @Before
  fun startsWithStoppedState() {
    presenter.bindView(view)

    verify(view).userInteractions
    verify(clock).getClockObservable()
    verify(view).render(Stopped)
  }

  @Test
  fun renderBlacksRunningIfBlackPressed() {
    interactions.accept(ClocksView.Interaction.BlackPressed)
    clockObservable.accept(1L)

    verify(view).render(BlackRunning)
  }

  @Test
  fun renderWhitesRunningIfWhitePressed() {
    interactions.accept(ClocksView.Interaction.WhitePressed)
    clockObservable.accept(1L)

    verify(view).render(WhiteRunning)
  }

  @Test
  fun renderStopWhenStopPressed() {
    interactions.accept(ClocksView.Interaction.StopPressed)
    clockObservable.accept(1L)

    verify(view, times(2)).render(Stopped)
  }
}
