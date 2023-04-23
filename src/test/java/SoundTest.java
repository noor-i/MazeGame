import maze.Sound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoundTest {
    @Test
    @DisplayName("Test loading sound file")
    void testSetFile() {
        Sound s = new Sound();
        s.setFile(0);
        assertNotNull(s.getClip());
    }

    @Test
    @DisplayName("Test playing sound")
    void testPlay() throws InterruptedException {
        Sound s = new Sound();
        s.setFile(1);
        s.play();
        Thread.sleep(100);
        assertTrue(s.isPlaying());
    }

    @Test
    @DisplayName("Test looping sound")
    void testLoop() throws InterruptedException {
        Sound s = new Sound();
        s.setFile(0);
        s.loop();
        Thread.sleep(100);
        assertTrue(s.isPlaying());
    }

    @Test
    @DisplayName("Test stopping sound")
    void testStop() {
        Sound s = new Sound();
        s.setFile(1);
        s.play();
        s.stop();
        assertFalse(s.isPlaying());
    }
}
