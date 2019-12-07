package com.theopensourcefamily.chessclock

sealed class ClockState {
  abstract val whitesTime: Long
  abstract val blacksTime: Long

  data class Stopped(override val whitesTime: Long, override val blacksTime: Long, val canReset: Boolean) : ClockState()
  data class WhiteRunning(override val whitesTime: Long, override val blacksTime: Long) : ClockState()
  data class BlackRunning(override val whitesTime: Long, override val blacksTime: Long) : ClockState()
  data class GameOver(override val whitesTime: Long, override val blacksTime: Long) : ClockState()
}
