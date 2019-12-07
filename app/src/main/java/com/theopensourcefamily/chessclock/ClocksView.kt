package com.theopensourcefamily.chessclock

import io.reactivex.Observable

interface ClocksView {

  val userInteractions: Observable<Interaction>

  fun render(state: ClockState)

  sealed class Interaction {
    object BlackPressed : Interaction()
    object WhitePressed : Interaction()
    object StopPressed : Interaction()
    object ResetPressed : Interaction()
  }
}
