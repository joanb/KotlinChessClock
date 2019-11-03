package com.theopensourcefamily.chessclock

import com.theopensourcefamily.clocks.Clock
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ClocksPresenter @Inject constructor(
  private val clock: Clock
) {

  private lateinit var view: ClocksView
  private val disposables = CompositeDisposable()

  fun bindView(view: ClocksView) {
    this.view = view

    disposables.add(
      clock.getClockObservable()
        .withLatestFrom(
          view.userInteractions,
          BiFunction { clock: Long, interaction: ClocksView.Interaction ->
            interaction
          })
        .map { interaction: ClocksView.Interaction ->
          when (interaction) {
            ClocksView.Interaction.WhitePressed -> WhiteRunning
            ClocksView.Interaction.BlackPressed -> BlackRunning
          }
        }
        .startWith(Stopped)
        .subscribe(
          view::render,
          { throw RuntimeException(it) },
          { throw RuntimeException() }
        )
    )
  }

  fun unbind() {
    disposables.dispose()
  }
}
