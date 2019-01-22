package me._19lmy.lightshow.example.trick;

import me._19lmy.lightshow.api.controller.LightController;
import me._19lmy.lightshow.api.trick.Trick;

/**
 *
 */
public class ExPeriodicTrick extends Trick {

    private LightController lights;

    public ExPeriodicTrick(LightController lights) {
        super(50, 300);
        this.lights = lights;
    }

    protected void runTrick(int count) {
        /*if (count > 25) {
            cancel();
        }*/

        lights.toggle(25);
    }
}
