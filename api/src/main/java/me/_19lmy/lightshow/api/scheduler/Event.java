package me._19lmy.lightshow.api.scheduler;

/**
 *
 */
public interface Event {
    Event run(Runnable function);
    Event cancelAfter(int delay);
    void cancel();
}
