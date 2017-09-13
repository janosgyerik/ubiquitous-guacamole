package com.janosgyerik.counters;

import org.junit.*;

import static com.janosgyerik.counters.Actions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ActionsTest {
  private Counter newCounter() {
    return CounterFactory.builder("my counter").build();
  }

  @Test
  public void inc_should_increment_counter() {
    Counter counter = newCounter();
    counter.setValue(0);

    Action action = inc();

    action.apply(counter);
    assertThat(counter.getValue()).isEqualTo(1);

    action.apply(counter);
    assertThat(counter.getValue()).isEqualTo(2);

    counter.setValue(10);
    action.apply(counter);
    assertThat(counter.getValue()).isEqualTo(11);
  }

  @Test
  public void none_should_not_change_counter_value() {
    Counter counter = newCounter();
    int origValue = counter.getValue();

    Action action = none();

    action.apply(counter);
    assertThat(counter.getValue()).isEqualTo(origValue);

    action.apply(counter);
    assertThat(counter.getValue()).isEqualTo(origValue);
  }

  @Test
  public void reset_should_reset_to_start_value() {
    int start = 10;
    Counter counter = CounterFactory.builder("my counter")
        .start(start)
        .build();

    Action action = inc();
    action.apply(counter);
    action.apply(counter);
    action.apply(counter);
    assertThat(counter.getValue()).isEqualTo(start + 3);

    Action reset = reset();
    reset.apply(counter);
    assertThat(counter.getValue()).isEqualTo(start);
  }
}
