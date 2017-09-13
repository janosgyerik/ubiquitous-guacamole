package com.janosgyerik.counters;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;

public class CounterTester {
  private final EventRepo eventRepo = new InMemoryEventRepo();
  private final ActionManager actionManager = new ActionManagerImpl(eventRepo);
  private final User user = mock(User.class);
  private final Counter counter;

  // TODO should set to the start of a week, otherwise weekly actions will not work reliably
  private LocalDateTime date = LocalDateTime.now();

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

  public void performPeriodic() {
    actionManager.performPeriodic(user, counter, date);
  }

  public void nextDay() {
    actionManager.performTimeout(user, counter, date);
    date = date.plusDays(1);
    actionManager.performPeriodic(user, counter, date);
  }

  public void nextWeek() {
    actionManager.performTimeout(user, counter, date);
    date.plusDays(7);
    // TODO why does this have to be after? smelly, need to rethink this
    actionManager.performPeriodic(user, counter, date);
  }
}
