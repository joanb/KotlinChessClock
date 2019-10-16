package com.theopensourcefamily.chessclock

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ClocksPresenter @Inject constructor() {

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
  }

  fun unbind() {
    disposables.dispose()
  }
}
