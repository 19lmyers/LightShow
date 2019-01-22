package me._19lmy.lightshow.api.trick.functional;

import me._19lmy.lightshow.api.Provider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class FunctionalTrick {

    private int beatLength;
    private int startingDelay;

    private Timer timer;
    private TimerTask[] stages;

    private Logger log;

    FunctionalTrick(int beatLength, int startingDelay, Runnable[] functions) {
        this.beatLength = beatLength;
        this.startingDelay = startingDelay;

        timer = Provider.getTimer();
        stages = new TimerTask[functions.length];
        log = Logger.getLogger(getClass().getName());

        IntStream.range(0, stages.length).forEach(i -> {
            stages[i] = new TimerTask() {
                @Override
                public void run() {
                    if (functions[i] != null) {
                        log.info("Running com._19lmy.lightshow.example.trick at beat " + i);
                        functions[i].run();
                    } else {
                        log.info("No function for beat " + i);
                    }
                }
            };
        });
    }

    public FunctionalTrick schedule() {
        IntStream.range(0, stages.length).forEach(i -> {
            timer.schedule(stages[i], startingDelay + (i * beatLength), stages.length * beatLength);
        });
        return this;
    }

    public FunctionalTrick cancelAfter(int delay) {
        TimerTask cancelTask = new TimerTask() {
            @Override
            public void run() {
                FunctionalTrick.this.cancel();
            }
        };

        timer.schedule(cancelTask, delay);
        return this;
    }

    public void cancel() {
        log.info("Cancelling com._19lmy.lightshow.example.trick");
        for (TimerTask task : stages) {
            task.cancel();
        }
    }
}
