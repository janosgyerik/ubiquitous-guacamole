package com.janosgyerik.counters;

public class CounterStateImpl implements CounterState {

  private int value;

  @Override
  public CounterDescriptor descriptor() {
    return null;
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
