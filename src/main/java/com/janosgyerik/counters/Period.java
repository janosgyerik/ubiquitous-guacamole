package com.janosgyerik.counters;

import java.util.Date;

public interface Period {
  String computeId(Date date, int utcOffset);
}
