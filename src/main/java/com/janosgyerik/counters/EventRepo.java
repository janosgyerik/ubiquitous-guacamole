package com.janosgyerik.counters;

import java.util.Date;

public interface EventRepo {
  /**
   * Check if an event was already logged for the user's counter, period, date.
   */
  boolean exists(User user, Counter counter, Date date);

  void addManual(User user, Counter counter, Date date, int valueBefore);

  void addTimeout(User user, Counter counter, Date date, int valueBefore);

  int size();
}
