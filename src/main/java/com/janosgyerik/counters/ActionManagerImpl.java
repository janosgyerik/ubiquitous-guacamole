package com.janosgyerik.counters;

import java.time.LocalDateTime;

public class ActionManagerImpl implements ActionManager {
  private final EventRepo events;

  public ActionManagerImpl(EventRepo eventRepo) {
    this.events = eventRepo;
  }

  private String counterId(Counter counter) {
    return counter.descriptor().name();
  }

  private long periodId(User user, Period period, LocalDateTime date) {
    return period.computeId(date, user.utcOffset());
  }

  @Override
  public void performManual(User user, Counter counter, LocalDateTime date) {
    int valueBefore = counter.getValue();
    counter.descriptor().manualAction().apply(counter);
    events.add(user, counterId(counter), periodId(user, counter.descriptor().manualAction().period(), date), ActionType.MANUAL, valueBefore);
  }

  @Override
  public void performTimeout(User user, Counter counter, LocalDateTime date) {
    String counterId = counter.descriptor().name();
    long periodId = counter.descriptor().timeoutAction().period().computeId(date, user.utcOffset());
    if (!events.exists(user, counterId, periodId, ActionType.MANUAL, ActionType.TIMEOUT)) {
      int valueBefore = counter.getValue();
      counter.descriptor().timeoutAction().apply(counter);
      events.add(user, counterId(counter), periodId(user, counter.descriptor().timeoutAction().period(), date), ActionType.TIMEOUT, valueBefore);
    }
  }

  @Override
  public void performPeriodic(User user, Counter counter, LocalDateTime date) {
    String counterId = counter.descriptor().name();
    long periodId = counter.descriptor().periodicAction().period().computeId(date, user.utcOffset());
    if (!events.exists(user, counterId, periodId, ActionType.PERIODIC)) {
      int valueBefore = counter.getValue();
      counter.descriptor().periodicAction().apply(counter);
      events.add(user, counterId(counter), periodId(user, counter.descriptor().periodicAction().period(), date), ActionType.PERIODIC, valueBefore);
    }
  }
}
