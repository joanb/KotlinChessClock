package com.theopensourcefamily.chessclock.extensions

fun Long.centisecondsToHumanFriendlyTime(): String {
  val oneSecond = 100
  val oneMinute = 60
  val hour = 3600

  val seconds = (this / oneSecond) % oneMinute
  val minutes = (this / (oneSecond * oneMinute)) % oneMinute
  val hours = (this / (oneSecond * hour)) % oneMinute

  return """
    ${if (hours > 0) hours.twoDigitsMin().plus(":") else ""}${minutes.twoDigitsMin()}:${seconds.twoDigitsMin()}
  """.trimIndent()
}

/*
  This function returns two digits at least.
  Return more than two digits if needed
*/
fun Long.twoDigitsMin(): String = String.format("%02d", this)
