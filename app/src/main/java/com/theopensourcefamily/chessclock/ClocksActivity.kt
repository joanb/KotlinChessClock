package com.theopensourcefamily.chessclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_clocks.*

class ClocksActivity : AppCompatActivity(), GameView {

  val presenter = GamePresenter() //maybe DI here, with koin or wathever

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_clocks)

    presenter.bindView(this)
  }

  fun render() {
    // Just to put something in the clocks right now
    whiteClock.text = "5:00"
    blackClock.text = "5:00"
  }
}
