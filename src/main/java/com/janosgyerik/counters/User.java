package com.janosgyerik.counters;

public interface User {
  /**
   * Unique identifier.
   */
  String id();

  /**
   * Hours from UTC. For example 1 in Paris.
   */
  int utcOffset();
}
