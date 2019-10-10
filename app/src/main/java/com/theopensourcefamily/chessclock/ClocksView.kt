package com.theopensourcefamily.chessclock

import io.reactivex.Observable

interface ClocksView {

  val userInteractions: Observable<Interaction>

  fun render()

  sealed class Interaction {
    object BlackPressed : Interaction()
    object WhitePressed : Interaction()
  }
}
