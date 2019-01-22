package me._19lmy.lightshow.api.trick;

import me._19lmy.lightshow.api.Provider;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A Trick is an event that can occur once or multiple times in the future.
 * Override this class to implement a Trick.
 */
public abstract class Trick {

    private final Timer timer = Provider.getTimer();

    private static final int MIN_TIME = 50;

    private int delay;

    private boolean isRepeating = false;
    private int period;

    private boolean willCancelAfter = false;
    private int cancelAfter;

    private final TimerTask task;
    private int count = 0;

    /**
     * Creates a new Trick instance that will run once when scheduled.
     * @param delay Delay in milliseconds
     */
    public Trick(int delay) {
        task = new TimerTask() {
            @Override
            public void run() {
                if (willCancelAfter && cancelAfter == count) {
                    cancel();
                }
                count++;
                runTrick(count);
            }
        };

        this.delay = delay;

        if (delay != 0 && delay < MIN_TIME) {
            throw new IllegalArgumentException("Error instantiating " + getClass().getSimpleName()
                    + ": Delay must be at least 50 milliseconds");
        }
    }

    /**
     * Creates a new Trick instance that will run forever until cancelled.
     * @param delay Delay in milliseconds
     * @param period Period between executions, in milliseconds
     */
    public Trick(int delay, int period) {
        this(delay);

        isRepeating = true;
        this.period = period;

        if (period < MIN_TIME) {
            throw new IllegalArgumentException("Error instantiating " + getClass().getSimpleName()
                    + ": Period must be at least 50 milliseconds");
        }
    }

    /**
     * Creates a new Trick instance that will run a specified number of times.
     * @param delay Delay in milliseconds
     * @param period Period between executions, in milliseconds
     * @param counts The number of times the trick should run
     */
    public Trick(int delay, int period, int counts) {
        this(delay, period);

        willCancelAfter = true;
        cancelAfter = counts;

        if (counts <= 0) {
            throw new IllegalArgumentException("Error instantiating " + getClass().getSimpleName()
                    + ": Number of counts must be positive");
        }
    }

    /**
     * Schedules the trick to run in the future.
     */
    public final void schedule() {
        if (isRepeating) {
            timer.schedule(task, delay, period);
        } else {
            timer.schedule(task, delay);
        }
    }

    /**
     * Cancels the trick, preventing it from executing again.
     */
    public final void cancel() {
        task.cancel();
    }

    /**
     * Override this to specify what the trick does.
     * @param count The number of times this method has run (starts at 1)
     */
    protected abstract void runTrick(int count);
}

