package com.janosgyerik.counters;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScenarioTest {
  @Test
  public void daily_count_up_with_manual_reset() {
    Counter counter = CounterFactory.builder("dummy", Periods.daily())
        .manualAction(Actions.reset())
        .timeoutAction(Actions.inc())
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
    Counter counter = CounterFactory.builder("dummy", Periods.weekly())
        .manualAction(Actions.inc())
        .periodicAction(Actions.reset())
        .build();

    CounterTester tester = new CounterTester(counter);

    assertThat(tester.getValue()).isEqualTo(0);

    tester.performManual();
    assertThat(tester.getValue()).isEqualTo(1);

    tester.performManual();
    assertThat(tester.getValue()).isEqualTo(2);

    tester.nextWeek();
    assertThat(tester.getValue()).isEqualTo(0);
  }
}
