package com.janosgyerik.counters;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScenarioTest {
  @Test
  public void days_without_pushups_counter() {
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
}
