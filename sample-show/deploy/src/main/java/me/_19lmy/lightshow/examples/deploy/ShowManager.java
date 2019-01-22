package me._19lmy.lightshow.examples.deploy;

import me._19lmy.lightshow.api.Provider;
import me._19lmy.lightshow.api.Show;
import me._19lmy.lightshow.api.controller.LightController;
import me._19lmy.lightshow.api.controller.MusicController;
import me._19lmy.lightshow.example.AdvShow;
import me._19lmy.lightshow.example.ExampleShow;
import me._19lmy.lightshow.platform.rpi.RPILightController;

import java.util.Timer;
import java.util.TimerTask;

public class ShowManager {

    private LightController lights = new RPILightController();
    private MusicController music = new MusicController();

    private Timer timer = Provider.getTimer();

    private volatile boolean showInProgress = false;

    public static void main(String... args) {
        Show[] shows = new Show[]{new ExampleShow(), new AdvShow()}; //TODO load from classpath / folder?
        new ShowManager().run(shows);
    }

    private void run(Show... shows) {
        while (true) { //TODO method to terminate process?

            for (Show show : shows) {
                int duration = show.run(lights, music);

                showInProgress = true;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        showInProgress = false;
                    }
                }, duration);

                //noinspection StatementWithEmptyBody
                while (showInProgress) {
                } //Wait until show is done
                lights.resetAll();
                music.reset();
                Provider.resetTimer();
            }
        }
    }

}
