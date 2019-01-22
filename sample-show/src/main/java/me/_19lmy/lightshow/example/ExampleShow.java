package me._19lmy.lightshow.example;

import me._19lmy.lightshow.api.Show;
import me._19lmy.lightshow.api.controller.LightController;
import me._19lmy.lightshow.api.controller.MusicController;
import me._19lmy.lightshow.example.trick.ExIterativeTrick;
import me._19lmy.lightshow.example.trick.ExPeriodicTrick;
import me._19lmy.lightshow.example.trick.ExTrick;

import java.io.File;

/**
 * A simple show implemented using the Trick class.
 */
public class ExampleShow implements Show {

    @Override
    public int run(LightController lights, MusicController music) {
        File musicFile = new File(getClass().getClassLoader().getResource("music/BNT.wav").getFile());
        music.initialize(musicFile);
        music.start();

        new ExTrick(lights).schedule();
        new ExPeriodicTrick(lights).schedule();
        new ExIterativeTrick(lights).schedule();

        return 7500;
    }
}
