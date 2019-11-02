package com.theopensourcefamily.chessclock

import com.theopensourcefamily.clocks.Clock
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ClocksPresenter @Inject constructor(
  private val clock: Clock
) {

  private lateinit var view: ClocksView
  private val disposables = CompositeDisposable()

  fun bindView(view: ClocksView) {
    this.view = view
    disposables.add(
      view.userInteractions
        .subscribe(
          { /* TODO */ },
          { throw Exception() }
        )
    )

    disposables.add(
      Observable.just(Stopped)
        .subscribe(
          view::render,
          { /* TODO */},
          { /* TODO */}
        )
    )
  }

  fun unbind() {
    disposables.dispose()
  }
}
