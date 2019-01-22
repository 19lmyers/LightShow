package me._19lmy.lightshow.api;

import java.util.Timer;

public class Provider {

    private static Timer timer;

    public static Timer getTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        return timer;
    }

    public static void resetTimer() { //Yes, this works. I don't like it, but it does. - TODO solution
        timer.cancel();
        timer = new Timer();
    }
}
