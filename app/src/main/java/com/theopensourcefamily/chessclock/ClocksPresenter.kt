package com.theopensourcefamily.chessclock

class ClocksPresenter {

  private lateinit var view: ClocksView

  fun bindView(view: ClocksView) {
    this.view = view
  }
}
