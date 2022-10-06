package front;

import javax.swing.JFrame;

import base.KeyHandler;
import base.Paddle;
import base.Score;
import front.paddle_speed.P_Speed_1;
import front.paddle_speed.P_Speed_2;
import front.paddle_speed.P_Speed_3;
import front.paddle_speed.Paddle_Speed;
import front.sphere_speed.S_Speed_1;
import front.sphere_speed.S_Speed_2;
import front.sphere_speed.S_Speed_3;
import front.sphere_speed.Sphere_Speed;

/**
 * The Frame/Screen of the Game, combined with draw class
 * @author Kirils Turkins
 * @category Gui
 */

public class Frame extends JFrame {

    private int width = 1200;
    private int heigth = 800;

    private Score score;

    private Draw draw;

    protected KeyHandler keyHandler;

    protected TwoPlayer twoPlayer;

    protected ImageLoader imageLoader;

    protected AgainstAi againstAi;

    protected S_Speed_1 s_Speed_1;
    protected S_Speed_2 s_Speed_2;
    protected S_Speed_3 s_Speed_3;

    protected Paddle paddle;

    protected Paddle_Speed paddle_Speed;

    protected P_Speed_1 p_Speed_1;
    protected P_Speed_2 p_Speed_2;
    protected P_Speed_3 p_Speed_3;

    // Build of the the Frame
    public Frame(KeyHandler keyHandler, Score score, TwoPlayer twoPlayer, ImageLoader imageLoader, AgainstAi againstAi, S_Speed_1 s_Speed_1, S_Speed_2 s_Speed_2, S_Speed_3 s_Speed_3, Sphere_Speed sphere_Speed, Paddle paddle, Paddle_Speed paddle_Speed, P_Speed_1 p_Speed_1, P_Speed_2 p_Speed_2, P_Speed_3 p_Speed_3){
        this.keyHandler = keyHandler;
        this.score = score;
        this.twoPlayer = twoPlayer;
        this.imageLoader = imageLoader;
        this.againstAi = againstAi;
        this.s_Speed_1 = s_Speed_1;
        this.s_Speed_2 = s_Speed_2;
        this.s_Speed_3 = s_Speed_3;
        this.p_Speed_1 = p_Speed_1;
        this.p_Speed_2 = p_Speed_2;
        this.p_Speed_3 = p_Speed_3;

        draw = new Draw(width, heigth, twoPlayer, imageLoader, againstAi, sphere_Speed, paddle_Speed, paddle, score);

        setSize(width, heigth);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Ping, Pong, Ping, Pong, Ping, Pong, Ping, Pong, Ping, Pong, Ping, Pong");

        add(twoPlayer);
        add(againstAi);
        add(s_Speed_1);
        add(s_Speed_2);
        add(s_Speed_3);
        add(p_Speed_1);
        add(p_Speed_2);
        add(p_Speed_3);
        add(draw);
        addKeyListener(keyHandler);

        setVisible(true);
    }

    // Give the draw method the coords from the Walls and repaint it
    public void drawMove(int y1, int y2, int ballX, int ballY){
        
        draw.y1 = y1;
        draw.y2 = y2;

        draw.scoreP1 = score.getScoreP1();
        draw.scoreP2 = score.getScoreP2();

        draw.ballX = ballX;
        draw.ballY = ballY;

        draw.repaint();
    }

    public void baseDraw(){
        draw.repaint();
    }
}
