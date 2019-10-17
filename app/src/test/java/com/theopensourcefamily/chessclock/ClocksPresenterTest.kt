package com.theopensourcefamily.chessclock

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.After
import org.junit.Test

class ClocksPresenterTest {
  private val interactions: Relay<ClocksView.Interaction> = PublishRelay.create<ClocksView.Interaction>()
  private val view: ClocksView = mock {
    on { userInteractions } doReturn (interactions)
  }

  private val presenter = ClocksPresenter()

  @After
  fun tearDown() {
    verifyNoMoreInteractions(view)
  }

  @Test
  fun bindView() {
    presenter.bindView(view)

    verify(view).userInteractions
    interactions.test().assertSubscribed()
  }
}
