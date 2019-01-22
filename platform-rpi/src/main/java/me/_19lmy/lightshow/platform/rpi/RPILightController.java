package me._19lmy.lightshow.platform.rpi;

import com.pi4j.io.gpio.*;
import me._19lmy.lightshow.api.controller.LightController;

public class RPILightController implements LightController {

    private final GpioController gpio;
    private final GpioPinDigitalOutput[] digitalOutputs;

    public RPILightController() {
        gpio = GpioFactory.getInstance();

        Pin[] pins = { // Note from an engineer: this is hideous and impossible to test
                RaspiPin.GPIO_08, RaspiPin.GPIO_09, RaspiPin.GPIO_07, RaspiPin.GPIO_00, RaspiPin.GPIO_02,
                RaspiPin.GPIO_03, RaspiPin.GPIO_12, RaspiPin.GPIO_13, RaspiPin.GPIO_14, RaspiPin.GPIO_21,
                RaspiPin.GPIO_22, RaspiPin.GPIO_23, RaspiPin.GPIO_24, RaspiPin.GPIO_25, RaspiPin.GPIO_15,
                RaspiPin.GPIO_16, RaspiPin.GPIO_01, RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06,
                RaspiPin.GPIO_10, RaspiPin.GPIO_11, RaspiPin.GPIO_26, RaspiPin.GPIO_27, RaspiPin.GPIO_28,
                RaspiPin.GPIO_29
        };

        digitalOutputs = new GpioPinDigitalOutput[pins.length];
        for (int i = 0; i < pins.length; i++) {
            digitalOutputs[i] = gpio.provisionDigitalOutputPin(pins[i], "Pin #" + i, PinState.LOW);
        }
    }

    @Override
    public boolean get(int pin) {
        return digitalOutputs[pin].isHigh();
    }

    @Override
    public void set(boolean state, int pin) {
        digitalOutputs[pin].setState(state);
    }

    @Override
    public void toggle(int pin) {
        digitalOutputs[pin].toggle();
    }

    @Override
    public void resetAll() {
        for (GpioPinDigitalOutput digitalOutput : digitalOutputs) {
            digitalOutput.setState(PinState.LOW);
        }
    }
}
