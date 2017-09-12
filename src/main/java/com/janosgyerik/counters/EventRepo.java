package com.janosgyerik.counters;

import java.util.Date;

public interface EventRepo {
  /**
   * Check if an event was already logged for the user's counter, period, action type.
   */
  boolean exists(User user, String counterId, String periodId, ActionType... actionTypes);

  void add(User user, String counterId, String periodId, ActionType actionType, int valueBefore);

  int size();
}
