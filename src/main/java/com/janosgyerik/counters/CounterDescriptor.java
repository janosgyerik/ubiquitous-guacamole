package com.janosgyerik.counters;

public class CounterDescriptor {

  private final String name;
  private final Period period;
  private final int start;
  private final Action manualAction;
  private final Action timeoutAction;

  private CounterDescriptor(Builder builder) {
    this.name = builder.name;
    this.period = builder.period;
    this.start = builder.start;
    this.manualAction = builder.manualAction;
    this.timeoutAction = builder.timeoutAction;
  }

  public String name() {
    return name;
  }

  public Period period() {
    return period;
  }

  public int start() {
    return start;
  }

  public Action manualAction() {
    return manualAction;
  }

  public Action timeoutAction() {
    return timeoutAction;
  }

  public static Builder builder(String name, Period period) {
    return new Builder(name, period);
  }

  private static class Builder {
    private final String name;
    private final Period period;
    private int start = 0;
    private Action manualAction = Actions.none();
    private Action timeoutAction = Actions.none();

    private Builder(String name, Period period) {
      this.name = name;
      this.period = period;
    }
  }
}
