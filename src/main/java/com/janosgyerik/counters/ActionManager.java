package com.janosgyerik.counters;

import java.util.Date;

public interface ActionManager {
  /**
   * Perform the manual action on the counter of a user.
   * The period is determined based on the date and the user's calendar settings.
   */
  void performManual(User user, Counter counter, Date date);

  /**
   * Perform the timeout action on the counter of a user, if it was not yet performed for the period.
   * The period is determined based on the date and the user's calendar settings.
   *
   * @return true if the action was performed
   */
  boolean performTimeout(User user, Counter counter, Date date);
}
