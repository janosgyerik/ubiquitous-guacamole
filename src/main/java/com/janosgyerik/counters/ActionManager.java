package com.janosgyerik.counters;

import java.util.Date;

public interface ActionManager {
  /**
   * Perform an action on the counter of a user, if it was not yet performed for the period.
   * The period is determined based on the date and the user's calendar settings.
   *
   * @return true if the action was performed
   */
  boolean perform(User user, Counter counter, Action action, Date date);
}
