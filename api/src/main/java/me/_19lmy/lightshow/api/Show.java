package me._19lmy.lightshow.api;

import me._19lmy.lightshow.api.controller.LightController;
import me._19lmy.lightshow.api.controller.MusicController;

/**
 * Implement this to declare a Light Show.
 */
public interface Show {

    //TODO abstract class with constructor parameters? (could be helpful with Music playing)

    /**
     * Executes the light show using the provided light and music controllers.
     * @param lights Provided LightController instance
     * @param music Provided MusicController instance
     * @return Duration of show, in ms
     */
    int run(LightController lights, MusicController music);
}
