package com.janosgyerik.counters;

import java.util.Date;

public class ActionManagerImpl implements ActionManager {
  private final EventRepo events;

  public ActionManagerImpl(EventRepo eventRepo) {
    this.events = eventRepo;
  }

  @Override
  public void performManual(User user, Counter counter, Date date) {
    int valueBefore = counter.getValue();
    counter.descriptor().manualAction().apply(counter);
    events.addManual(user, counter, date, valueBefore);
  }

  @Override
  public boolean performTimeout(User user, Counter counter, Date date) {
    if (!events.exists(user, counter, date)) {
      int valueBefore = counter.getValue();
      counter.descriptor().timeoutAction().apply(counter);
      events.addTimeout(user, counter, date, valueBefore);
      return true;
    }
    return false;
  }

  @Override
  public void performPeriodic(User user, Counter counter, Date date) {
    int valueBefore = counter.getValue();
    counter.descriptor().periodicAction().apply(counter);
    events.addPeriodic(user, counter, date, valueBefore);
  }
}
