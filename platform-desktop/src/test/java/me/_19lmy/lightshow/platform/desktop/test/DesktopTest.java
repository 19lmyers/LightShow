package me._19lmy.lightshow.platform.desktop.test;

import me._19lmy.lightshow.platform.desktop.DesktopLightController;
import org.junit.jupiter.api.Test;

class DesktopTest {

    @Test
    void testDisplay() {
        DesktopLightController lights = new DesktopLightController();

        for (int i = 0; i < 25; i++) {
            lights.toggle(i);
        }
    }
}
