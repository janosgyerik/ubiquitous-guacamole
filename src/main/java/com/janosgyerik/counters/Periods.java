package com.janosgyerik.counters;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;

public class Periods {

  private Periods() {
    // utility class, forbidden constructor
  }

  private static LocalDateTime dayStart(LocalDateTime ldt) {
    return ldt.truncatedTo(ChronoUnit.DAYS);
  }

  private static LocalDateTime weekStart(LocalDateTime ldt) {
    return ldt.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).truncatedTo(ChronoUnit.DAYS);
  }

  private static long days(LocalDateTime start, LocalDateTime end) {
    return TimeUnit.DAYS.convert(end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC), TimeUnit.SECONDS);
  }

  public static Period daily() {
    LocalDateTime start = dayStart(LocalDateTime.now());
    return (date, utcOffset) -> days(start, dayStart(date));
  }

  public static Period weekly() {
    LocalDateTime start = weekStart(LocalDateTime.now());
    return (date, utcOffset) -> days(start, dayStart(date)) / 7;
  }
}
