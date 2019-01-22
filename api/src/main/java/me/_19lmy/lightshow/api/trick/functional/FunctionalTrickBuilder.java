package me._19lmy.lightshow.api.trick.functional;

import java.util.TimerTask;

/**
 * TRICK
 *
 * - delay
 * - period per cycle
 * - number of cycles
 * - Function for each cycle
 *
 * - schedule()
 * - cancel()
 * - cancelAfter() ?
 */
public class FunctionalTrickBuilder {

    private int beatLength;
    private int startingDelay = 0;

    private Runnable[] stages;

    public FunctionalTrickBuilder(int beatLength, int numBeats) {
        this.beatLength = beatLength;

        stages = new TimerTask[numBeats];
    }

    public FunctionalTrickBuilder(int beatLength, int numBeats, int startingDelay) {
        this.beatLength = beatLength;
        this.startingDelay = startingDelay;

        stages = new TimerTask[numBeats];
    }

    public FunctionalTrickBuilder onBeat(int beat, Runnable function) {
        stages[beat] = new TimerTask() {
            @Override
            public void run() {
                function.run();
            }
        };
        return this;
    }

    public FunctionalTrick build() {
        return new FunctionalTrick(beatLength, startingDelay, stages);
    }
}
