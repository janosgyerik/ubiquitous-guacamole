package com.janosgyerik.counters;

public interface Counter {
  CounterDescriptor descriptor();

  int getValue();

  void setValue(int value);
}
