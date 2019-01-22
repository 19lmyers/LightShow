package me._19lmy.lightshow.example;

import me._19lmy.lightshow.api.Provider;
import me._19lmy.lightshow.api.Show;
import me._19lmy.lightshow.api.controller.LightController;
import me._19lmy.lightshow.api.controller.MusicController;
import me._19lmy.lightshow.platform.desktop.DesktopLightController;

import java.util.Timer;
import java.util.TimerTask;

public class ShowRunner {

    public static void main(String... args) {
        LightController lights = new DesktopLightController();
        MusicController music = new MusicController();

        Show myShow = new ExampleShow(); // Replace this with your Show class!
        int duration = myShow.run(lights, music);

        // Ends the show when it is finished
        Timer timer = Provider.getTimer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                lights.resetAll();
                music.reset();
                timer.cancel();
            }
        }, duration);
    }
}
