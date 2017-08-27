package com.janosgyerik.counters;

public class Actions {
  private Actions() {
    // utility class, forbidden constructor
  }

  public static Action none() {
    return new Action() {
      @Override
      public String name() {
        return "none";
      }

      @Override
      public void apply(CounterState counterState) {
        // do nothing
      }
    };
  }

  public static Action inc() {
    return new Action() {
      @Override
      public String name() {
        return "inc1";
      }

      @Override
      public void apply(CounterState counter) {
        counter.setValue(counter.getValue() + 1);
      }
    };
  }
}
