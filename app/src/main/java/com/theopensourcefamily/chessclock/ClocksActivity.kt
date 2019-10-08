package com.theopensourcefamily.chessclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ClocksActivity : AppCompatActivity(), GameView {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun render() {
    // Just to put something in the clocks right now
    whiteClock.text = "5:00"
    blackClock.text = "5:00"
  }
}
