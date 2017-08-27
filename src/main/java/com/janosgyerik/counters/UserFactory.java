package com.janosgyerik.counters;

public class UserFactory {
  private static class UserImpl implements User {
    private final String name;
    private final int utcOffset;

    private UserImpl(String name, int utcOffset) {
      this.name = name;
      this.utcOffset = utcOffset;
    }

    @Override
    public String id() {
      return name;
    }

    @Override
    public int utcOffset() {
      return utcOffset;
    }
  }

  public static User create() {
    return create("dummy", 0);
  }

  public static User create(String name) {
    return create(name, 0);
  }

  public static User create(String name, int utcOffset) {
    return new UserImpl(name, utcOffset);
  }
}
