package com.theopensourcefamily.chessclock

import io.reactivex.Observable

interface ClocksView {

  val userInteractions: Observable<Interaction>

  sealed class Interaction {
    object BlackPressed: Interaction()
    object WhitePressed: Interaction()
  }
}
