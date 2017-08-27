package com.janosgyerik.counters;

public class CounterImpl implements Counter {

  private final CounterDescriptor descriptor;
  private int value;

  CounterImpl(CounterDescriptor descriptor, int start) {
    this.descriptor = descriptor;
    this.value = start;
  }

  @Override
  public CounterDescriptor descriptor() {
    return descriptor;
  }

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public void setValue(int value) {
    this.value = value;
  }
}
