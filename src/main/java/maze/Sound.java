package maze;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * The Sound class is responsible for handling the sound effects and background
 * music used in the game. It provides methods to play, loop, and stop sounds
 * as well as checking if a sound is currently playing.
 */
public class Sound {
    private Clip clip;
    private URL soundURL[] = new URL[2];

    /**
     * Initializes the Sound class with the file paths for the required sounds.
     */
    public Sound() {
        soundURL[0] = getClass().getResource("/SoundRe/Sweden.wav");
        soundURL[1] = getClass().getResource("/SoundRe/coin.wav");
    }

    /**
     * Sets the sound file to be played, opened, and prepared for playing.
     *
     * @param i The index of the sound file in the soundURL array.
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts playing the sound.
     */
    public void play() {
        this.clip.start();
    }

    /**
     * Loops the sound continuously.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops playing the sound.
     */
    public void stop() {
        clip.stop();
    }

    /**
     * Returns the Clip object associated with the current sound.
     *
     * @return The Clip object.
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Checks if the sound is currently playing.
     *
     * @return True if the sound is playing, false otherwise.
     */
    public boolean isPlaying() {
        return clip.isRunning();
    }
}
