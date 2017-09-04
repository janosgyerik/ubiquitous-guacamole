package com.janosgyerik.counters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.mockito.Mockito.mock;

public class CounterTester {
  private final EventRepo eventRepo = new InMemoryEventRepo();
  private final ActionManager actionManager = new ActionManagerImpl(eventRepo);
  private final User user = mock(User.class);
  private final Counter counter;

  private Date date = new Date();

  public CounterTester(Counter counter) {
    this.counter = counter;
  }

  public int getValue() {
    return counter.getValue();
  }

  public void performManual() {
    actionManager.performManual(user, counter, date);
  }

  public void performTimeout() {
    actionManager.performTimeout(user, counter, date);
  }

  public void nextDay() {
    actionManager.performTimeout(user, counter, date);
    date.setTime(date.toInstant().plus(1, ChronoUnit.DAYS).toEpochMilli());
  }
}