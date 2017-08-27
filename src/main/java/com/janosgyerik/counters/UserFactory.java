package com.janosgyerik.counters;

public class UserFactory {
  public static User create(String name, int utcOffset) {
    return new User() {
      @Override
      public String id() {
        return name;
      }

      @Override
      public int utcOffset() {
        return utcOffset;
      }
    };
  }
}
