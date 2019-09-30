package com.theopensourcefamily.chessclock

sealed class ClockState
object Stopped : ClockState()
object WhiteRunning : ClockState()
object BlackRunning : ClockState()
