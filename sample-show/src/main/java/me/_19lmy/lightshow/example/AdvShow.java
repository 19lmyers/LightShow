package me._19lmy.lightshow.example;

import me._19lmy.lightshow.api.Show;
import me._19lmy.lightshow.api.controller.LightController;
import me._19lmy.lightshow.api.controller.MusicController;
import me._19lmy.lightshow.api.scheduler.Event;
import me._19lmy.lightshow.api.scheduler.EventScheduler;
import me._19lmy.lightshow.api.trick.functional.FunctionalTrick;
import me._19lmy.lightshow.api.trick.functional.FunctionalTrickBuilder;

import java.io.File;

/**
 * Demonstrates the advanced capabilities of the EventScheduler and FunctionalTrick classes.
 */
public class AdvShow implements Show {

    private static final int msPerBeat = 326;

    @Override
    public int run(LightController lights, MusicController music) {
        File musicFile = new File(getClass().getClassLoader().getResource("music/BNT.wav").getFile());
        music.initialize(musicFile);
        music.start();

        EventScheduler scheduler = new EventScheduler();

        // T=0: Do nothing

        // T=1: Toggle pin 0
        scheduler.withDelay(msPerBeat).run(() -> lights.toggle(0));

        // T=2: Turn on 1 and 3; turn off 2 and 4
        scheduler.withDelay(2 * msPerBeat).run(() -> {
            lights.set(true, 1, 3);
            lights.set(false, 0, 2);
        });

        // T=3: Toggle pins 1-3 every T
        scheduler.withPeriod(3 * msPerBeat, msPerBeat).run(() -> lights.toggle(1, 2, 3));

        // T=4: Toggle pin 5 every T, keep reference
        Event eventToCancel = scheduler.withPeriod(4 * msPerBeat, msPerBeat)
                .run(() -> lights.toggle(5));

        // T=6: Toggle pin 6 every T, cancel at T=10
        scheduler.withPeriod(4 * msPerBeat, msPerBeat)
                .cancelAfter(10 * msPerBeat)
                .run(() -> lights.toggle(6));

        // T=8: Stop toggling pin 5 via reference
        scheduler.withDelay(8 * msPerBeat).run(eventToCancel::cancel);

        // T=10: Use FunctionalTrickBuilder to add a full com._19lmy.lightshow.example.trick
        new FunctionalTrickBuilder(msPerBeat, 4, 10 * msPerBeat)
                .onBeat(0, () -> {
                    lights.set(true, 8, 9);
                    lights.set(false, 10, 11);
                })
                .onBeat(1, () -> {
                    lights.set(true, 9, 10);
                    lights.set(false, 11, 8);
                })
                .onBeat(2, () -> {
                    lights.set(true, 10, 11);
                    lights.set(false, 8, 9);
                })
                .onBeat(3, () -> {
                    lights.set(true, 11, 8);
                    lights.set(false, 9, 10);
                })
                .build().schedule();

        // T=12: Build a second FunctionalTrick and save reference
        FunctionalTrick trick = new FunctionalTrickBuilder(msPerBeat, 2, 12 * msPerBeat)
                .onBeat(0, () -> lights.toggle(18))
                .onBeat(1, () -> lights.toggle(19))
                .build();
        trick.schedule();

        // T=15: Build a third FunctionalTrick, cancel at T=30
        new FunctionalTrickBuilder(msPerBeat, 5, 15 * msPerBeat)
                .onBeat(0, () -> lights.toggle(21))
                .onBeat(1, () -> lights.toggle(22))
                .onBeat(2, () -> lights.toggle(23))
                .onBeat(3, () -> lights.toggle(24))
                .onBeat(4, () -> lights.toggle(25))
                .build().cancelAfter(30 * msPerBeat)
                .schedule();

        // T=20: Cancel FunctionalTrick via reference
        scheduler.withDelay(20 * msPerBeat).run(trick::cancel);

        return 32 * msPerBeat;
    }
}
