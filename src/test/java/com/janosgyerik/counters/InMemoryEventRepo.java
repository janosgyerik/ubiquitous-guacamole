package com.janosgyerik.counters;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class InMemoryEventRepo implements EventRepo {
  private final Set<String> events = new HashSet<>();

  private String key(User user, Counter counter, Date date) {
    String periodId = counter.descriptor().period().computeId(date, user.utcOffset());
    return String.join(":", user.id(), counter.descriptor().name(), periodId);
  }

  @Override
  public boolean exists(User user, Counter counter, Date date) {
    return events.contains(key(user, counter, date));
  }

  @Override
  public void addManual(User user, Counter counter, Date date, int valueBefore) {
    events.add(key(user, counter, date));
  }

  @Override
  public void addTimeout(User user, Counter counter, Date date, int valueBefore) {
    events.add(key(user, counter, date));
  }

  @Override
  public void addPeriodic(User user, Counter counter, Date date, int valueBefore) {
    events.add(key(user, counter, date));
  }

  @Override
  public int size() {
    return events.size();
  }
}
