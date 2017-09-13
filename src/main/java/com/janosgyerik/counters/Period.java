package com.janosgyerik.counters;

import java.time.LocalDateTime;

public interface Period {
  long computeId(LocalDateTime date, int utcOffset);
}
