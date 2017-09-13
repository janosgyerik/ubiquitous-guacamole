package com.janosgyerik.counters;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PeriodsTest {
  @Test
  public void daily_period_id_should_be_increasing() {
    LocalDateTime date = LocalDateTime.now();
    int utcOffset = 1;
    Period daily = Periods.daily();
    assertThat(computeId(daily, date, utcOffset)).isEqualTo(0);
    assertThat(computeId(daily, date.plusDays(1), utcOffset)).isEqualTo(1);
    assertThat(computeId(daily, date.plusDays(2), utcOffset)).isEqualTo(2);
  }

  @Test
  public void weekly_period_id_should_be_increasing() {
    LocalDateTime date = LocalDateTime.now();
    int utcOffset = 1;
    Period weekly = Periods.weekly();
    assertThat(computeId(weekly, date, utcOffset)).isEqualTo(0);
    assertThat(computeId(weekly, date.plusDays(7), utcOffset)).isEqualTo(1);
    assertThat(computeId(weekly, date.plusDays(14), utcOffset)).isEqualTo(2);
  }

  @Test
  public void week_should_start_on_monday() {
    LocalDateTime date = LocalDateTime.now();
    long daysToNextWeek = date.until(date.with(TemporalAdjusters.next(DayOfWeek.MONDAY)), ChronoUnit.DAYS);
    int utcOffset = 0;
    Period weekly = Periods.weekly();
    assertThat(computeId(weekly, date.plusDays(daysToNextWeek - 1), utcOffset)).isEqualTo(0);
    assertThat(computeId(weekly, date.plusDays(daysToNextWeek), utcOffset)).isEqualTo(1);
    assertThat(computeId(weekly, date.plusDays(daysToNextWeek + 6), utcOffset)).isEqualTo(1);
    assertThat(computeId(weekly, date.plusDays(daysToNextWeek + 7), utcOffset)).isEqualTo(2);
  }

  private long computeId(Period period, LocalDateTime date, int utcOffset) {
    return period.computeId(date, utcOffset);
  }
}
