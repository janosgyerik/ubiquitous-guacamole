package com.janosgyerik.counters;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class InMemoryEventRepo implements EventRepo {
  private final Set<String> events = new HashSet<>();

  private String key(User user, Counter counter, Date date) {
    long periodId = counter.descriptor().period().computeId(date, user.utcOffset());
    return String.join(":", user.id(), counter.descriptor().name(), Long.toString(periodId));
  }

  private String key(User user, String counterId, long periodId, ActionType actionType) {
    return String.join(":", user.id(), counterId, Long.toString(periodId), actionType.toString());
  }

  @Override
  public boolean exists(User user, String counterId, long periodId, ActionType... actionTypes) {
    return Arrays.stream(actionTypes).anyMatch(a -> events.contains(key(user, counterId, periodId, a)));
  }

  @Override
  public void add(User user, String counterId, long periodId, ActionType actionType, int valueBefore) {
    events.add(key(user, counterId, periodId, actionType));
  }

  @Override
  public int size() {
    return events.size();
  }
}
