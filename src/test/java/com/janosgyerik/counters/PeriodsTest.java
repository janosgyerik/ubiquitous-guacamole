package com.janosgyerik.counters;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PeriodsTest {
  @Test
  public void should_increase_daily_period_id() {
    LocalDateTime date = LocalDateTime.now();
    int utcOffset = 1;
    Period daily = Periods.daily(utcOffset);
    assertThat(computeId(daily, date, utcOffset)).isEqualTo(0);
    assertThat(computeId(daily, date.plusDays(1), utcOffset)).isEqualTo(1);
    assertThat(computeId(daily, date.plusDays(2), utcOffset)).isEqualTo(2);
  }

  private long computeId(Period period, LocalDateTime date, int utcOffset) {
    return period.computeId(date(utcOffset, date), utcOffset);
  }

  // TODO this is almost certainly all wrong
  //@Test
  public void should_get_periodId_for_correct_zone() {
    Period daily = Periods.daily();

    LocalDateTime localDateTime = LocalDateTime.now();
    int utcOffset = 1;
    Date date = date(utcOffset, localDateTime);
    assertThat(daily.computeId(date, utcOffset)).isEqualTo(0);
  }

  private Date date(int utcOffset, LocalDateTime localDateTime) {
    return Date.from(localDateTime.atOffset(ZoneOffset.ofHours(utcOffset)).toInstant());
  }
}
