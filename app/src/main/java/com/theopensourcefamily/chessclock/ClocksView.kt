package com.theopensourcefamily.chessclock

interface ClocksView {

  sealed class Interaction {
    object BlackPressed: Interaction()
    object WhitePressed: Interaction()
  }
}
