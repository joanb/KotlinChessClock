package com.theopensourcefamily.chessclock.extensions

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class HumanFriendlyTimeTest {

  @Test
  fun centisecondsToHumanFriendlyTime_without_hours() {
      val fiveMinutes = 30000L

      val humanFriendlyTime = fiveMinutes.centisecondsToHumanFriendlyTime()

      assertThat(humanFriendlyTime, equalTo("05:00"))
  }

  @Test
  fun centisecondsToHumanFriendlyTime_with_hours() {
      val oneHour = 360000L

      val humanFriendlyTime = oneHour.centisecondsToHumanFriendlyTime()

      assertThat(humanFriendlyTime, equalTo("01:00:00"))
  }

  @Test
  fun centisecondsToHumanFriendlyTime_with_hours_with_minutes() {
      val oneHour = 372000L

      val humanFriendlyTime = oneHour.centisecondsToHumanFriendlyTime()

      assertThat(humanFriendlyTime, equalTo("01:02:00"))
  }

  @Test
  fun centisecondsToHumanFriendlyTime_with_hours_with_minutes_with_seconds() {
      val oneHour = 375500L

      val humanFriendlyTime = oneHour.centisecondsToHumanFriendlyTime()

      assertThat(humanFriendlyTime, equalTo("01:02:35"))
  }

  @Test
  fun twoDigitsMin() {
      val twoDigits = 34L
      val oneDigit = 3L
      val threeDigits = 500L

      assertThat(twoDigits.twoDigitsMin(), equalTo("34"))
      assertThat(oneDigit.twoDigitsMin(), equalTo("03"))
      assertThat(threeDigits.twoDigitsMin(), equalTo("500"))
  }
}
