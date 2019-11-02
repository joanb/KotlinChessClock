package com.theopensourcefamily.chessclock

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import com.nhaarman.mockitokotlin2.*
import com.theopensourcefamily.clocks.Clock
import io.reactivex.Observable
import org.junit.After
import org.junit.Test

class ClocksPresenterTest {
  private val interactions: Relay<ClocksView.Interaction> =
    PublishRelay.create<ClocksView.Interaction>()
  private val view: ClocksView = mock {
    on { userInteractions } doReturn (interactions)
  }

  private val clock: Clock = mock {
    on { getClockObservable(any()) } doReturn Observable.just(1L)
  }

  private val presenter = ClocksPresenter(clock)

  @After
  fun tearDown() {
    verifyNoMoreInteractions(view)
  }
  @Test
  fun startsWithStoppedState() {
    presenter.bindView(view)

    verify(view).userInteractions
    verify(view).render(Stopped)
  }
}
