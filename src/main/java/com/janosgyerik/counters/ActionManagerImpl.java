package com.janosgyerik.counters;

import java.util.Date;

public class ActionManagerImpl implements ActionManager {
  private final EventRepo events;

  public ActionManagerImpl(EventRepo eventRepo) {
    this.events = eventRepo;
  }

  private String counterId(Counter counter) {
    return counter.descriptor().name();
  }

  private int periodId(User user, Counter counter, Date date) {
    return counter.descriptor().period().computeId(date, user.utcOffset());
  }

  @Override
  public void performManual(User user, Counter counter, Date date) {
    int valueBefore = counter.getValue();
    counter.descriptor().manualAction().apply(counter);
    events.add(user, counterId(counter), periodId(user, counter, date), ActionType.MANUAL, valueBefore);
  }

  @Override
  public boolean performTimeout(User user, Counter counter, Date date) {
    String counterId = counter.descriptor().name();
    int periodId = counter.descriptor().period().computeId(date, user.utcOffset());
    if (!events.exists(user, counterId, periodId, ActionType.MANUAL, ActionType.TIMEOUT)) {
      int valueBefore = counter.getValue();
      counter.descriptor().timeoutAction().apply(counter);
      events.add(user, counterId(counter), periodId(user, counter, date), ActionType.TIMEOUT, valueBefore);
      return true;
    }
    return false;
  }

  @Override
  public void performPeriodic(User user, Counter counter, Date date) {
    String counterId = counter.descriptor().name();
    int periodId = counter.descriptor().period().computeId(date, user.utcOffset());
    if (!events.exists(user, counterId, periodId, ActionType.PERIODIC)) {
      int valueBefore = counter.getValue();
      counter.descriptor().periodicAction().apply(counter);
      events.add(user, counterId(counter), periodId(user, counter, date), ActionType.PERIODIC, valueBefore);
    }
  }
}
