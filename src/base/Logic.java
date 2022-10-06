package base;

import front.AgainstAi;
import front.Frame;
import front.ImageLoader;
import front.Sound;
import front.SoundLoader;
import front.TwoPlayer;
import front.paddle_speed.P_Speed_1;
import front.paddle_speed.P_Speed_2;
import front.paddle_speed.P_Speed_3;
import front.paddle_speed.Paddle_Speed;
import front.sphere_speed.S_Speed_1;
import front.sphere_speed.S_Speed_2;
import front.sphere_speed.S_Speed_3;
import front.sphere_speed.Sphere_Speed;

import java.awt.Point;

/**
 * The main logic and structure of the Game, container of all game based objects
 * @author Kirils Turkins
 * @category Base, Logic
 */

public class Logic {
    
    // Load and link all classes/objects
    
    private Score score = new Score();

    private SoundLoader soundLoader = new SoundLoader();

    private TwoPlayer twoPlayer = new TwoPlayer();

    private ImageLoader imageLoader = new ImageLoader();

    private AgainstAi againstAi = new AgainstAi();

    private Sphere_Speed sphere_Speed = new Sphere_Speed();

    private Paddle_Speed paddle_Speed = new Paddle_Speed();

    private S_Speed_1 s_Speed_1 = new S_Speed_1(sphere_Speed);
    private S_Speed_2 s_Speed_2 = new S_Speed_2(sphere_Speed);
    private S_Speed_3 s_Speed_3 = new S_Speed_3(sphere_Speed);

    private P_Speed_1 p_Speed_1 = new P_Speed_1(paddle_Speed);
    private P_Speed_2 p_Speed_2 = new P_Speed_2(paddle_Speed);
    private P_Speed_3 p_Speed_3 = new P_Speed_3(paddle_Speed);

    private Paddle paddle = new Paddle(paddle_Speed);

    private Ball ball = new Ball(paddle, score, soundLoader, sphere_Speed);

    private KeyHandler keyHandler = new KeyHandler(paddle, ball);

    private Frame frame = new Frame(keyHandler, score, twoPlayer, imageLoader, againstAi, s_Speed_1, s_Speed_2, s_Speed_3, sphere_Speed, paddle, paddle_Speed, p_Speed_1, p_Speed_2, p_Speed_3);

    // Start the Pong game
    public void run(){

        imageLoader.loadImage();
        soundLoader.play(Sound.MENU_BACKGROUND);

        // Wait until game get started
        if (twoPlayer.gameStarted() == false && againstAi.gameStarted() == false){while(twoPlayer.gameStarted() == false && againstAi.gameStarted() == false){frame.baseDraw();}}

        // Check if game got started
        if (twoPlayer.gameStarted() == true || againstAi.gameStarted() == true){

            // Check if two Player or Ai got selected
            if (twoPlayer.gameStarted() == true){
                keyHandler.movePaddle();
            } else {
                keyHandler.moveAIPaddle();
            }
    
        // Set up ball and paddle
        ball.moveBall();
        paddle.setSpeed();

        // Remove select speed and paddle buttons from screen
        s_Speed_1.removeB();
        s_Speed_2.removeB();
        s_Speed_3.removeB();
        p_Speed_1.removeB();
        p_Speed_2.removeB();
        p_Speed_3.removeB();

        // Game loop which repaints the current ball and paddle positions
        while(twoPlayer.gameStarted() == true || againstAi.gameStarted() == true){

            Point p = ball.getPoint();

            int y1 = (int) paddle.getPoint1().getY();
            int y2 = (int) paddle.getPoint2().getY();

            frame.drawMove(y1, y2, p.x, p.y);

            // Set into winner screen if one player have reached 10 Points
            if (score.getScoreP1() >= 10 || score.getScoreP2() >= 10){
                soundLoader.play(Sound.WON_GAME);
                while(true){
                frame.drawMove(y1, y2, p.x, p.y);
                ball.stopBall();
            }

            }
        }
    }

    }
}
