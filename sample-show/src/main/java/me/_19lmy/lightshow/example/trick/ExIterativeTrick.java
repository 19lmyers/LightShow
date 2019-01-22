package me._19lmy.lightshow.example.trick;

import me._19lmy.lightshow.api.controller.LightController;
import me._19lmy.lightshow.api.trick.Trick;

/**
 *
 */
public class ExIterativeTrick extends Trick {

    private LightController lights;

    public ExIterativeTrick(LightController lights) {
        super(50, 200, 20);
        this.lights = lights;
    }

    protected void runTrick(int count) {
        lights.set(true, count);
    }
}
