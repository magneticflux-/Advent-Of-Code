package com.github.magneticflux.aoc.year2018.day4

import java.time.LocalDate

import com.github.magneticflux.aoc.AssumptionViolationException
import com.github.magneticflux.aoc.Helpers._

import scala.collection.{BitSet, mutable}

/**
  * Created by Mitchell Skaggs on 12/2/2018.
  */
object Problem1 {
  def main(args: Array[String]): Unit = {
    val records = this.input()
      .getLines()
      .toSeq
      .sorted
      .map {
        case r"""\[(\d+)$year-(\d+)$month-(\d+)$day (\d{2})$hour:(\d{2})$minute\] Guard #(\d+)$guard begins shift""" =>
          ShiftBegin(year.toInt, month.toInt, day.toInt, hour.toInt, minute.toInt, guard.toInt)
        case r"""\[(\d+)$year-(\d+)$month-(\d+)$day (\d{2})$hour:(\d{2})$minute\] falls asleep""" =>
          FallAsleep(year.toInt, month.toInt, day.toInt, hour.toInt, minute.toInt)
        case r"""\[(\d+)$year-(\d+)$month-(\d+)$day (\d{2})$hour:(\d{2})$minute\] wakes up""" =>
          WakeUp(year.toInt, month.toInt, day.toInt, hour.toInt, minute.toInt)
      }

    val guardHours = records.groupBy(_.roundedDate)
      .map(it => {
        val (a: ShiftBegin) +: theRest = it._2

        val asleep = mutable.BitSet()

        theRest.grouped(2)
          .foreach(two => {
            val Seq(first, second) = two
            (first, second) match {
              case (first: FallAsleep, second: WakeUp) => asleep ++= first.minute.until(second.minute)
              case _ => throw AssumptionViolationException
            }
          })

        GuardHours(a.guardNum, asleep)
      })

    val (mostAsleepGuard, _) = guardHours.groupBy(_.guard).mapValues(_.map(_.hoursAsleep).sum).maxBy(_._2)

    val mostAsleepGuardHours = guardHours.filter(_.guard == mostAsleepGuard)
      .flatMap(_.asleep)
      .toSeq
      .groupBy(identity)
      .mapValues(_.size)

    val (mostAsleepMinute, _) = mostAsleepGuardHours.maxBy(_._2)

    println(mostAsleepGuard * mostAsleepMinute)
  }

  sealed trait Event {
    val year: Int
    val month: Int
    val day: Int
    val hour: Int
    val minute: Int

    /**
      * Bumps up dates that started just before midnight (23:59, etc) to the next day for grouping.
      */
    def roundedDate: (Int, Int, Int) = {
      var date = LocalDate.of(year, month, day)
      if (hour == 23)
        date = date.plusDays(1)
      (date.getYear, date.getMonth.getValue, date.getDayOfMonth)
    }
  }

  case class GuardHours(guard: Int, asleep: BitSet) {
    lazy val hoursAsleep: Int = asleep.size
  }

  case class ShiftBegin(override val year: Int,
                        override val month: Int,
                        override val day: Int,
                        override val hour: Int,
                        override val minute: Int,
                        guardNum: Int) extends Event

  case class WakeUp(override val year: Int,
                    override val month: Int,
                    override val day: Int,
                    override val hour: Int,
                    override val minute: Int) extends Event

  case class FallAsleep(override val year: Int,
                        override val month: Int,
                        override val day: Int,
                        override val hour: Int,
                        override val minute: Int) extends Event

}
