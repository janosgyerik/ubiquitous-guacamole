package com.janosgyerik.counters;

public class CounterFactory {

  public static Builder builder(String name, Period period) {
    return new Builder(name, period);
  }

  public static class Builder {
    private final String name;
    private final Period period;

    private int start = 0;
    private Action manualAction = Actions.none();
    private Action timeoutAction = Actions.none();
    private Action periodicAction = Actions.none();

    private Builder(String name, Period period) {
      this.name = name;
      this.period = period;
    }

    public Counter build() {
      CounterDescriptor descriptor = new CounterDescriptor(name, period, start, manualAction, timeoutAction, periodicAction);
      return new CounterImpl(descriptor, start);
    }

    Builder start(int start) {
      this.start = start;
      return this;
    }

    public Builder manualAction(Action action) {
      this.manualAction = action;
      return this;
    }

    public Builder timeoutAction(Action action) {
      this.timeoutAction = action;
      return this;
    }

    public Builder periodicAction(Action action) {
      this.periodicAction = action;
      return this;
    }
  }
}
