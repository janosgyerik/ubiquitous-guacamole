package com.janosgyerik.counters;

public class Periods {
  public static Period daily() {
    return new Daily();
  }

  private static class Daily implements Period {

  }
}
