package com.janosgyerik.counters;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionManagerTest {

  private final User user = UserFactory.create();
  private final CounterFactory.Builder builder = CounterFactory.builder("dummy");

  private EventRepo eventRepo;
  private ActionManager actionManager;

  @Before
  public void before() {
    eventRepo = new InMemoryEventRepo();
    actionManager = new ActionManagerImpl(eventRepo);
  }

  @Test
  public void should_apply_manual_action_always() {
    Counter counter = builder
        .manualAction(Actions.inc())
        .timeoutAction(Actions.reset())
        .build();

    LocalDateTime date = LocalDateTime.now();
    actionManager.performManual(user, counter, date);
    assertThat(counter.getValue()).isEqualTo(1);
    actionManager.performManual(user, counter, date);
    assertThat(counter.getValue()).isEqualTo(2);
    actionManager.performManual(user, counter, date);
    assertThat(counter.getValue()).isEqualTo(3);

    // TODO
    //assertThat(eventRepo.size()).isEqualTo(3);
  }

  @Test
  public void should_apply_timeout_action_once_per_period() {
    Counter counter = builder
        .manualAction(Actions.reset())
        .timeoutAction(Actions.inc())
        .build();

    LocalDateTime date = LocalDateTime.now();
    actionManager.performTimeout(user, counter, date);
    assertThat(counter.getValue()).isEqualTo(1);
    actionManager.performTimeout(user, counter, date);
    assertThat(counter.getValue()).isEqualTo(1);

    assertThat(eventRepo.size()).isEqualTo(1);
  }
}
