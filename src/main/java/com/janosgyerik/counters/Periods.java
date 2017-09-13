package com.janosgyerik.counters;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Periods {

  private Periods() {
    // utility class, forbidden constructor
  }

  private static long dayStart(Date date, int utcOffset) {
    // TODO
    return date.getTime();
  }

  private static long weekStart(Date date, int utcOffset) {
    // TODO
    return date.getTime();
  }

  private static long days(long start, long end) {
    return TimeUnit.DAYS.convert(end - start, TimeUnit.MILLISECONDS);
  }

  public static Period daily() {
    return daily(0);
  }

  public static Period daily(int utcOffset) {
    long start = dayStart(new Date(), utcOffset);
    return (date, utcOffset2) -> days(start, dayStart(date, utcOffset));
  }

  public static Period weekly() {
    return weekly(0);
  }

  public static Period weekly(int utcOffset) {
    long start = weekStart(new Date(), utcOffset);
    return (date, utcOffset2) -> days(start, dayStart(date, utcOffset)) / 7;
  }

  public static long periodId(User user, Period period, Date date) {
    return period.computeId(date, user.utcOffset());
  }
}
