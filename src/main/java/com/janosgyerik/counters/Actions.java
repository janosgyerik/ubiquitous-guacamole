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
      public void apply(Counter counter) {
        // do nothing
      }

      @Override
      public Period period() {
        return Periods.none();
      }
    };
  }

  public static Action inc() {
    return inc(Periods.none());
  }

  public static Action inc(Period period) {
    return new Action() {
      @Override
      public String name() {
        return "inc1";
      }

      @Override
      public void apply(Counter counter) {
        counter.setValue(counter.getValue() + 1);
      }

      @Override
      public Period period() {
        return period;
      }
    };
  }

  public static Action reset() {
    return reset(Periods.none());
  }

  public static Action reset(Period period) {
    return new Action() {
      @Override
      public String name() {
        return "reset";
      }

      @Override
      public void apply(Counter counter) {
        counter.setValue(counter.descriptor().start());
      }

      @Override
      public Period period() {
        return period;
      }
    };
  }
}
