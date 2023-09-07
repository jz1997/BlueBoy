package org.blue.boy.sound;

import org.blue.boy.main.GamePanel;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class SoundManager {
    private static final Logger LOG = Logger.getLogger("Sound");

    GamePanel gp;
    Clip clip;
    URL[] soundUrls = new URL[30];

    public SoundManager(GamePanel gp) {
        this.gp = gp;
        loadSounds();
    }

    public void loadSounds() {
        soundUrls[0] = gp.fileUtil.loadSoundURL("/sound/BlueBoyAdventure.wav");
        soundUrls[1] = gp.fileUtil.loadSoundURL("/sound/coin.wav");
        soundUrls[2] = gp.fileUtil.loadSoundURL("/sound/powerup.wav");
        soundUrls[3] = gp.fileUtil.loadSoundURL("/sound/unlock.wav");
        soundUrls[4] = gp.fileUtil.loadSoundURL("/sound/fanfare.wav");
    }

    public void setFile(int i) {
        URL url = soundUrls[i];
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            LOG.severe(() -> String.format("Get audio input stream error, msg: '%s'", e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    private void play() {
        clip.start();
    }

    private void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void stop() {
        clip.stop();
    }

    public void playMusic(int i, boolean loop) {
        setFile(i);
        play();
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopMusic() {
        stop();
    }
}
