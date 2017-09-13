package com.janosgyerik.counters;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScenarioTest {
  @Test
  public void daily_count_up_with_manual_reset() {
    Counter counter = CounterFactory.builder("dummy")
        .manualAction(Actions.reset(Periods.daily()))
        .timeoutAction(Actions.inc(Periods.daily()))
        .build();

    CounterTester tester = new CounterTester(counter);

    assertThat(tester.getValue()).isEqualTo(0);

    tester.nextDay();
    assertThat(tester.getValue()).isEqualTo(1);

    tester.nextDay();
    assertThat(tester.getValue()).isEqualTo(2);

    tester.performManual();
    assertThat(tester.getValue()).isEqualTo(0);
    tester.nextDay();
    assertThat(tester.getValue()).isEqualTo(0);

    tester.nextDay();
    assertThat(tester.getValue()).isEqualTo(1);

    tester.performTimeout();
    assertThat(tester.getValue()).isEqualTo(2);

    tester.performTimeout();
    assertThat(tester.getValue()).isEqualTo(2);
  }

  @Test
  public void manual_count_up_with_weekly_reset() {
    Counter counter = CounterFactory.builder("dummy")
        .manualAction(Actions.inc())
        .periodicAction(Actions.reset(Periods.weekly()))
        .build();

    CounterTester tester = new CounterTester(counter);

    // TODO something's smelly: this should not be necessary
    tester.performPeriodic();
    assertThat(tester.getValue()).isEqualTo(0);

    tester.performManual();
    assertThat(tester.getValue()).isEqualTo(1);

    tester.performManual();
    assertThat(tester.getValue()).isEqualTo(2);

    tester.nextDay();
    assertThat(tester.getValue()).isEqualTo(2);

    tester.nextWeek();
    // TODO
    //assertThat(tester.getValue()).isEqualTo(0);
  }
}
