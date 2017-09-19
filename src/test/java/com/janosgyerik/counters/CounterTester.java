package com.janosgyerik.counters;

import java.time.LocalDateTime;
import java.util.stream.IntStream;
import sun.awt.image.IntegerComponentRaster;

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
    actionManager.performPeriodic(user, counter, date);

    // effectively, perform the timeout and periodic actions for previous period by default
    date = date.plusDays(1);
  }

  public void nextWeek() {
    IntStream.range(0, 7).forEach(i -> nextDay());
  }
}
