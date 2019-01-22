package me._19lmy.lightshow.api.controller;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

public class MusicController {

    private Clip musicClip;

    /**
     * @param musicFile
     */
    public void initialize(File musicFile) {
        try {
            musicClip = AudioSystem.getClip();
            musicClip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(musicFile))));
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
    }

    public void start() {
        musicClip.setFramePosition(0);
        musicClip.loop(0);
        musicClip.start();
    }

    public void reset() {
        musicClip.stop();
        musicClip.close();
    }
}
