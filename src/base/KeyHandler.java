package base;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Check for pressed Keys for both Players
 * @author Kirils Turkins
 * @category KeyHandler, KeyListener
 */

public class KeyHandler implements KeyListener{

    private boolean UP1 = false;
    private boolean UP2 = false;
    private boolean DOWN2 = false;
    private boolean DOWN1 = false;

    private Paddle paddle;

    private Ball ball;

    protected KeyHandler(Paddle paddle, Ball ball){

        this.paddle = paddle;
        this.ball = ball;
    }

    @Override
    public void keyTyped(KeyEvent e) {} // Unused

    // Check if Key got pressed
    @Override
    public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) UP1 = true;
                if (e.getKeyCode() == KeyEvent.VK_S) DOWN1 = true;
                if (e.getKeyCode() == KeyEvent.VK_UP) UP2 = true;
                if (e.getKeyCode() == KeyEvent.VK_DOWN) DOWN2 = true;
            }

    // Check if Key got unreleased
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) UP1 = false;
        if (e.getKeyCode() == KeyEvent.VK_S) DOWN1 = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) UP2 = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) DOWN2 = false;
    }

    // Check for pressed Keys for both Players and move them every 30 mili seconds
    void movePaddle(){

        // Start new running Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) { 
                        
                        // Check for pressed key cases
                        if (UP1 && UP2) {
                            paddle.decY1(); paddle.decY2();
                        } else if (UP1 && DOWN2) {
                            paddle.decY1(); paddle.incY2();
                        } else if (DOWN1 && UP2) {
                           paddle.incY1(); paddle.decY2();
                        } else if (DOWN1 && DOWN2) {
                           paddle.incY1(); paddle.incY2();
                        } else if (UP1) {
                            paddle.decY1();
                        } else if (UP2) {
                            paddle.decY2();;
                        } else if (DOWN1) {
                           paddle.incY1();
                        } else if (DOWN2) {
                            paddle.incY2();;
                        } 

                        // Check if paddle 1 is outside of border
                        if (paddle.getPoint1().getY() > 600){
                            paddle.setPoint1(600);
                        } else if (paddle.getPoint1().getY() < 10){
                            paddle.setPoint1(10);
                        }

                        // Check if paddle 2 is outside of border
                        if (paddle.getPoint2().getY() > 600){
                            paddle.setPoint2(600);
                        } else if (paddle.getPoint2().getY() < 10){
                            paddle.setPoint2(10);
                        }

                        Thread.sleep(30);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(0);
                }
            }
        }).start();
    }

    // Same as movePaddle, only that this method is used if the enemy is the AI
    void moveAIPaddle(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {  

                        if (paddle.p2.y > ball.getPoint().y){
                            paddle.decY2();
                        } else if (paddle.p2.y < ball.getPoint().y){
                            paddle.incY2();
                        }

                        if (UP1) {
                            paddle.decY1();
                        } else if (DOWN1) {
                           paddle.incY1();
                        }

                        if (paddle.getPoint1().getY() > 600){
                            paddle.setPoint1(600);
                        } else if (paddle.getPoint1().getY() < 10){
                            paddle.setPoint1(10);
                        }

                        if (paddle.getPoint2().getY() > 600){
                            paddle.setPoint2(600);
                        } else if (paddle.getPoint2().getY() < 10){
                            paddle.setPoint2(10);
                        }

                        Thread.sleep(30);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(0);
                }
            }
      }).start();
    }
    
}
