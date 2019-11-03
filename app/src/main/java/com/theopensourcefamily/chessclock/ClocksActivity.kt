package com.theopensourcefamily.chessclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_clocks.*
import javax.inject.Named

@Component(modules = [ClockPresenterFactory.MainModule::class])
interface ClockPresenterFactory {
  fun clockPresenter(): ClocksPresenter

  @Module
  class MainModule {
    @Provides
    @Named("mainScheduler")
    fun providesMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
  }
}

class ClocksActivity : AppCompatActivity(), ClocksView {

  val presenter = DaggerClockPresenterFactory.create().clockPresenter()

  override val userInteractions: Observable<ClocksView.Interaction>
    get() = Observable.merge(
      blackClock.clicks().map { ClocksView.Interaction.BlackPressed },
      whiteClock.clicks().map { ClocksView.Interaction.WhitePressed }
    )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_clocks)

    presenter.bindView(this)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.unbind()
  }

  override fun render(state: ClockState) {
    whiteClock.text = state.whitesTime.toString()
    blackClock.text = state.blacksTime.toString()
  }
}
