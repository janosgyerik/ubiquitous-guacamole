package com.janosgyerik.counters;

public interface EventRepo {
  /**
   * Check if an event was already logged for the user's counter, period, action type.
   */
  boolean exists(User user, String counterId, long periodId, ActionType... actionTypes);

  void add(User user, String counterId, long periodId, ActionType actionType, int valueBefore);

  int size();
}
