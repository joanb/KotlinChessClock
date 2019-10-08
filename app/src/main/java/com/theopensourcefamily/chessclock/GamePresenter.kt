package com.theopensourcefamily.chessclock

class GamePresenter {

  private lateinit var view: GameView

  fun bindView(view: GameView) {
    this.view = view
  }
}
