package com.janosgyerik.counters;

public interface CounterState {
  CounterDescriptor descriptor();

  int getValue();

  void setValue(int value);
}
