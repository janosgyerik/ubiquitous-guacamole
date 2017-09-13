package com.janosgyerik.counters;

import javax.annotation.concurrent.Immutable;

/**
 * Use CounterFactory.builder to create a Counter with its CounterDescriptor.
 */
@Immutable
public class CounterDescriptor {

  private final String name;
  private final int start;
  private final Action manualAction;
  private final Action timeoutAction;
  private final Action periodicAction;

  CounterDescriptor(CounterFactory.Builder builder) {
    this.name = builder.name;
    this.start = builder.start;
    this.manualAction = builder.manualAction;
    this.timeoutAction = builder.timeoutAction;
    this.periodicAction = builder.periodicAction;
  }

  public String name() {
    return name;
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
