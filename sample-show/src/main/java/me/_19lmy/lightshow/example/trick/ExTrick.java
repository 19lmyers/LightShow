package me._19lmy.lightshow.example.trick;

import me._19lmy.lightshow.api.controller.LightController;
import me._19lmy.lightshow.api.trick.Trick;

/**
 *
 */
public class ExTrick extends Trick {

    private LightController lights;

    public ExTrick(LightController lights) {
        super(50);
        this.lights = lights;
    }

    protected void runTrick(int count) {
        lights.set(true, 15);
    }
}
