package com.janosgyerik.counters;

public interface Action {
  String name();

  void apply(Counter counter);
}
