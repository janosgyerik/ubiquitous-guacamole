package com.janosgyerik.counters;

import org.junit.*;

import static com.janosgyerik.counters.Actions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ActionsTest {
  @Test
  public void inc_should_increment_counter() {
    Counter counter = new CounterImpl();
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
    Counter counter = new CounterImpl();
    int origValue = counter.getValue();

    Action action = none();

    action.apply(counter);
    assertThat(counter.getValue()).isEqualTo(origValue);

    action.apply(counter);
    assertThat(counter.getValue()).isEqualTo(origValue);
  }

}
