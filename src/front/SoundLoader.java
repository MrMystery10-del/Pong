package front;

import java.io.IOException;

import javax.sound.sampled.*;

/**
 * Load and play selected Sound
 * @author Kirils Turkins
 * @category Sound Loader
 */

public class SoundLoader {

    private AudioInputStream file;
    // Play selected Sound
    public void play(Sound sound){
        try {
            Clip clip = AudioSystem.getClip();
        switch (sound) {
            case PADDLE_COLLISION:
            file = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/paddle_collision.wav"));
            break;
            case WON_POINT:
            file = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/won_point.wav"));
            break;
            case MENU_BACKGROUND:
            file = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/menu_background.wav"));
            break;
            case WON_GAME:
            file = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/player_won.wav"));
            break;
        }
        clip.open(file);
        clip.start();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        e.printStackTrace();
    }
    }
}
