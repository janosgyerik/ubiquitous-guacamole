package com.janosgyerik.counters;

import javax.annotation.concurrent.Immutable;

/**
 * Use CounterFactory.builder to create a Counter with its CounterDescriptor.
 */
@Immutable
public class CounterDescriptor {

  private final String name;
  private final Period period;
  private final int start;
  private final Action manualAction;
  private final Action timeoutAction;
  private final Action periodicAction;

  CounterDescriptor(String name, Period period, int start, Action manualAction, Action timeoutAction, Action periodicAction) {
    this.name = name;
    this.period = period;
    this.start = start;
    this.manualAction = manualAction;
    this.timeoutAction = timeoutAction;
    this.periodicAction = periodicAction;
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

  public Action periodicAction() {
    return periodicAction;
  }

}
