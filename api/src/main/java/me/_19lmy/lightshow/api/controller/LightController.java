package me._19lmy.lightshow.api.controller;

public interface LightController {
    boolean get(int pin);

    void set(boolean state, int pin);

    default void toggle(int pin) {
        set(!get(pin), pin);
    }

    default void set(boolean state, int... pins) {
        for (int pin : pins) {
            set(state, pin);
        }
    }

    default void toggle(int... pins) {
        for (int pin : pins) {
            toggle(pin);
        }
    }

    void resetAll();
}
