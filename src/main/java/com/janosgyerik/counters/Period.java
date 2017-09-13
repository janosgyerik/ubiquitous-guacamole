package com.janosgyerik.counters;

import java.util.Date;

public interface Period {
  int computeId(Date date, int utcOffset);
}
