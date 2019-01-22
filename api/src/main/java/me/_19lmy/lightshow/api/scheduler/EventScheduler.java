package me._19lmy.lightshow.api.scheduler;

import me._19lmy.lightshow.api.Provider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class EventScheduler {

    private Timer timer;

    private Logger log;

    public EventScheduler() {
        timer = Provider.getTimer();

        log = Logger.getLogger(getClass().getName());
    }

    public Event withDelay(int delay) {
        return new InstantaneousEvent(delay);
    }

    public Event withPeriod(int delay, int period) {
        return new PeriodicEvent(delay, period);
    }

    private class InstantaneousEvent implements Event {

        private int delay;

        private TimerTask task;

        private InstantaneousEvent(int delay) {
            this.delay = delay;
        }

        @Override
        public InstantaneousEvent run(Runnable function) {
            task = new TimerTask() {
                @Override
                public void run() {
                    log.info("Running function with delay " + delay);
                    function.run();
                }
            };

            log.info("Scheduling function with delay " + delay);
            timer.schedule(task, delay);
            return this;
        }

        @Override
        public InstantaneousEvent cancelAfter(int delay) {
            TimerTask cancelTask = new TimerTask() {
                @Override
                public void run() {
                    InstantaneousEvent.this.cancel();
                }
            };

            timer.schedule(cancelTask, delay);
            return this;
        }

        @Override
        public void cancel() {
            log.info("Cancelling function with delay " + delay);
            task.cancel();
        }
    }

    private class PeriodicEvent implements Event {

        private int delay;
        private int period;

        private TimerTask task;

        private PeriodicEvent(int delay, int period) {
            this.delay = delay;
            this.period = period;
        }

        @Override
        public PeriodicEvent run(Runnable function) {
            task = new TimerTask() {
                @Override
                public void run() {
                    log.info("Running function with delay " + delay + " and period " + period);
                    function.run();
                }
            };

            log.info("Scheduling function with delay " + delay + " and period " + period);
            timer.schedule(task, delay, period);
            return this;
        }

        @Override
        public PeriodicEvent cancelAfter(int delay) {
            TimerTask cancelTask = new TimerTask() {
                @Override
                public void run() {
                    PeriodicEvent.this.cancel();
                }
            };

            timer.schedule(cancelTask, delay);
            return this;
        }

        @Override
        public void cancel() {
            log.info("Cancelling function with period " + period);
            task.cancel();
        }
    }
}
