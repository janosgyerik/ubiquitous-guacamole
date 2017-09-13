package com.janosgyerik.counters;

public class CounterFactory {

  public static Builder builder(String name) {
    return new Builder(name);
  }

  public static class Builder {
    final String name;

    int start = 0;
    Action manualAction = Actions.none();
    Action timeoutAction = Actions.none();
    Action periodicAction = Actions.none();

    private Builder(String name) {
      this.name = name;
    }

    public Counter build() {
      CounterDescriptor descriptor = new CounterDescriptor(this);
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
