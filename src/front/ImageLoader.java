package front;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;

import javax.imageio.ImageIO;

/**
 * Load all images for the game
 * @author Kirils Turkins
 * @category Image Loader
 */

public class ImageLoader {
    
    BufferedImage Menu, Choice, Player1_WON, Player2_WON;

    // Load image files
    public void loadImage(){

        try {
            Menu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu.png")));
            Choice = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/choice.png")));
            Player1_WON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player1_won.png")));
            Player2_WON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player2_won.png")));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
