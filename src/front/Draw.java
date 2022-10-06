package front;

import javax.swing.JPanel;

import base.Paddle;
import base.Score;
import front.paddle_speed.Paddle_Speed;
import front.sphere_speed.Sphere_Speed;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * The main draw class for every component
 * @author Kirils Turkins
 * @category Draw, Gui
 */

public class Draw extends JPanel {
    
    protected int y1 = 0;
    protected int y2 = 0;

    protected int ballX = 0;
    protected int ballY = 0;

    private int x1;
    private int x2;
    
    private int width;
    private int heigth;

    protected int scoreP1 = 0;
    protected int scoreP2 = 0;

    private Paddle_Speed paddle_Speed;

    private TwoPlayer twoPlayer = new TwoPlayer();

    private ImageLoader imageLoader;

    private AgainstAi againstAi;

    private Sphere_Speed sphere_Speed;

    private Score score;

    // Body of the draw class
    public Draw(int width, int heigth, TwoPlayer twoPlayer, ImageLoader imageLoader, AgainstAi againstAi, Sphere_Speed sphere_Speed, Paddle_Speed paddle_Speed, Paddle paddle, Score score){

        this.width = width;
        this.heigth = heigth;

        this.x1 = (int) paddle.getPoint1().getX();
        this.x2 = (int) paddle.getPoint2().getX();

        this.twoPlayer = twoPlayer;
        this.imageLoader = imageLoader;
        this.againstAi = againstAi;
        this.sphere_Speed = sphere_Speed;
        this.paddle_Speed = paddle_Speed;
        this.score = score;

    }

    // Draws all draw components
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        if (twoPlayer.gameStarted() == true || againstAi.gameStarted() == true){
        drawBase(g2d);
        drawPaddle1(g2d);
        drawPaddle2(g2d);
        drawBall(g2d);
        drawScore(g2d);

        if (score.getScoreP1() >= 10 || score.getScoreP2() >= 10){
            drawWinner(g2d);
        }
    } else {
        drawMenu(g2d);
    }
        g2d.dispose();
    }

    // Draw the base structure/backround
    private void drawBase(Graphics2D g2d){

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, heigth);
    }

    // Draw the first Paddle
    private void drawPaddle1(Graphics2D g2d){

        g2d.setColor(Color.WHITE);
        g2d.fillRect(x1, y1, 50, 5);
        g2d.fillRect(x1, y1 + 150, 50, 5);
        g2d.fillRect(x1, y1, 5, 150);
        g2d.fillRect(x1 + 50, y1, 5, 155);

        g2d.fillRect(x1 + 10, y1 + 10, 35, 5);
        g2d.fillRect(x1 + 10, y1 + 10, 5, 105);

        g2d.fillRect(x1 + 10, y1 + 140, 35, 5);
        g2d.fillRect(x1 + 40, y1 + 40, 5, 105);
    }

    // Draw the second Paddle
    private void drawPaddle2(Graphics2D g2d){

        g2d.setColor(Color.WHITE);
        g2d.fillRect(x2, y2, 50, 5);
        g2d.fillRect(x2, y2 + 150, 50, 5);
        g2d.fillRect(x2, y2, 5, 150);
        g2d.fillRect(x2 + 50, y2, 5, 155);

        g2d.fillRect(x2 + 10, y2 + 10, 35, 5);
        g2d.fillRect(x2 + 10, y2 + 10, 5, 105);

        g2d.fillRect(x2 + 10, y2 + 140, 35, 5);
        g2d.fillRect(x2 + 40, y2 + 40, 5, 105);
    }

    // Draw the Ball on the field
    private void drawBall(Graphics2D g2d){

        g2d.setColor(Color.WHITE);
        g2d.fillArc(ballX, ballY, 50, 50, 0, 360);
    }

    // Draw the score tab
    private void drawScore(Graphics2D g2d){

        g2d.setColor(Color.WHITE);
        g2d.fillRect(575, 0, 10, 800);
        g2d.setFont(new Font("Arial", Font.BOLD, 120));
        g2d.drawString(""+scoreP1, 450, 100);
        g2d.drawString(""+scoreP2, 650, 100);
    }

    // Draw the start menu
    private void drawMenu(Graphics2D g2d){

        g2d.drawImage(imageLoader.Menu, 0, 0, 1200, 800, null);
        
        switch (sphere_Speed.getSpeed()) {
            case 1:
            g2d.drawImage(imageLoader.Choice, 31, 572, 113, 106, null);
                break;
            case 2:
            g2d.drawImage(imageLoader.Choice, 31, 446, 113, 106, null);
                break;
            case 3:
            g2d.drawImage(imageLoader.Choice, 31, 322, 113, 105, null);
                break;
        }

        switch (paddle_Speed.getSpeed()) {
            case 1:
            g2d.drawImage(imageLoader.Choice, 1036, 572, 113, 106, null);
                break;
            case 2:
            g2d.drawImage(imageLoader.Choice, 1036, 446, 113, 106, null);
                break;
            case 3:
            g2d.drawImage(imageLoader.Choice, 1036, 322, 113, 105, null);
                break;
        }
    }

    // Draw player won screen
    private void drawWinner(Graphics2D g2d){

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, heigth);
        g2d.setColor(Color.WHITE);
        if (score.getScoreP1() >= 10){
            g2d.drawImage(imageLoader.Player1_WON, 0, 0, 1180, 780, null);
        } else {
            g2d.drawImage(imageLoader.Player2_WON, 0, 0, 1180, 780, null);
        }
    }
}
