package com.theopensourcefamily.chessclock.modules

import com.theopensourcefamily.chessclock.ClocksPresenter
import com.theopensourcefamily.clocks.Clock
import org.koin.dsl.module

val presenter = module {

  factory { (clock: Clock) -> ClocksPresenter(clock, get()) }

}
