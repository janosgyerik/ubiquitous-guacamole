package com.janosgyerik.counters;

import java.time.LocalDateTime;

/**
 * Perform actions on the counter of a user for a period.
 * The period is determined based on the date and the user's calendar settings.
 */
public interface ActionManager {
  /**
   * Perform the manual action on the counter of a user.
   */
  void performManual(User user, Counter counter, LocalDateTime date);

  /**
   * Perform the timeout action on the counter of a user,
   * if the manual action was not performed for the period.
   * The timeout action must be performed only once per period.
   */
  void performTimeout(User user, Counter counter, LocalDateTime date);

  /**
   * Perform the periodic action on the counter of a user.
   * The period action must be performed only once per period.
   */
  void performPeriodic(User user, Counter counter, LocalDateTime date);
}
