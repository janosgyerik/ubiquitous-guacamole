package com.janosgyerik.counters;

import java.util.Date;

public interface Period {
  long computeId(Date date, int utcOffset);
}
