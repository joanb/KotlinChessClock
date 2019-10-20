package com.theopensourcefamily.chessclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import dagger.Component
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_clocks.*

@Component
interface ClockPresenterFactory {
  fun clockPresenter(): ClocksPresenter
}

class ClocksActivity : AppCompatActivity(), ClocksView {

  private val presenter = DaggerClockPresenterFactory.create().clockPresenter()

  override val userInteractions: Observable<ClocksView.Interaction>
    get() = Observable.merge(
      blackClock.clicks().map { ClocksView.Interaction.BlackPressed },
      whiteClock.clicks().map { ClocksView.Interaction.WhitePressed }
    )

  override val currentState: ClockState
    get() = TODO("not implemented")

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_clocks)

    presenter.bindView(this)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.unbind()
  }
  override fun render() {
    // Just to put something in the clocks right now
    when(currentState) {
      Stopped -> {}
      WhiteRunning -> {}
      BlackRunning -> {}
    }
  }
}
