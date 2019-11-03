package com.theopensourcefamily.chessclock

import com.theopensourcefamily.clocks.Clock
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject
import javax.inject.Named

class ClocksPresenter @Inject constructor(
  private val clock: Clock,
  @Named("mainScheduler") private val scheduler: Scheduler = AndroidSchedulers.mainThread()
) {

  private lateinit var view: ClocksView
  private val disposables = CompositeDisposable()

  fun bindView(view: ClocksView) {
    this.view = view

    disposables.add(
      clock.getClockObservable()
        .withLatestFrom(
          view.userInteractions,
          BiFunction { _: Long, interaction: ClocksView.Interaction ->
            interaction
          })
        .scan<ClockState>(
          ClockState.Stopped(30000, 30000),
          { previousState: ClockState, lastInteraction: ClocksView.Interaction ->
            when (lastInteraction) {
              ClocksView.Interaction.WhitePressed ->
                ClockState.BlackRunning(previousState.whitesTime, previousState.blacksTime - 1)
              ClocksView.Interaction.BlackPressed ->
                ClockState.WhiteRunning(previousState.whitesTime - 1, previousState.blacksTime)
              ClocksView.Interaction.StopPressed ->
                ClockState.Stopped(previousState.whitesTime, previousState.blacksTime)
            }
          })
        .distinctUntilChanged()
        .observeOn(scheduler)
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
