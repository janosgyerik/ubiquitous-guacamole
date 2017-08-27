package com.janosgyerik.counters;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class Periods {

  private Periods() {
    // utility class, forbidden constructor
  }

  public static Period daily() {
    return (date, utcOffset) -> {
      OffsetDateTime time = Instant.ofEpochMilli(date.getTime()).atOffset(ZoneOffset.ofHours(utcOffset));
      return time.getYear() + "-" + time.getDayOfYear();
    };
  }

  public static String periodId(User user, Period period, Date date) {
    return period.computeId(date, user.utcOffset());
  }
}
