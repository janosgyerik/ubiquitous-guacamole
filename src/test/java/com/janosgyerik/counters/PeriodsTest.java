package com.janosgyerik.counters;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// TODO this is almost certainly all wrong
public class PeriodsTest {
  @Test
  public void should_get_periodId_for_correct_zone() {
    Period daily = Periods.daily();

    LocalDateTime localDateTime = LocalDateTime.of(2017, 8, 27, 21, 30);
    int utcOffset = 1;
    Date date = date(utcOffset, localDateTime);
    assertThat(daily.computeId(date, utcOffset)).isEqualTo("2017-239");
  }

  private Date date(int utcOffset, LocalDateTime localDateTime) {
    return Date.from(localDateTime.atOffset(ZoneOffset.ofHours(utcOffset)).toInstant());
  }
}
