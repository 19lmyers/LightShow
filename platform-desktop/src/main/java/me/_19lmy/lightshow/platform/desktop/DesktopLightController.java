package me._19lmy.lightshow.platform.desktop;


import me._19lmy.lightshow.api.controller.LightController;

public class DesktopLightController implements LightController {

    private boolean[] pinStates = new boolean[26];
    private LightDisplay display;

    //private Logger log;

    public DesktopLightController() {
        display = new LightDisplay();

        //log = Logger.getLogger(getClass().getName());
    }

    @Override
    public boolean get(int pin) {
        return pinStates[pin];
    }

    @Override
    public void set(boolean state, int pin) {
        //log.info("Set pin " + pin + " to " + state);

        pinStates[pin] = state;
        display.set(pin, state);
    }

    @Override
    public void resetAll() {
        for (int i = 0; i <= 25; i++) {
            set(false, i);
        }
    }
}
