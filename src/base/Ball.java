package base;

import java.awt.Point;
import java.util.Random;

import front.Sound;
import front.SoundLoader;
import front.sphere_speed.Sphere_Speed;

/**
 * Gives the stats of the ball
 * @author Kirils Turkins
 * @category Pong Component
 */

public class Ball {
    
    //Coords of the Ball
    private int x = 550;
    private int y = 300;

    private int dir;

    private Paddle paddle;

    private SoundLoader soundLoader;

    private Sphere_Speed sphere_Speed;

    private Random random = new Random();

    private Direction dirE = fullyRandomD();

    private Point p1 = new Point(x, y);

    protected Score score;

    // Ball body
    protected Ball(Paddle paddle, Score score, SoundLoader soundLoader, Sphere_Speed sphere_Speed){

        this.paddle = paddle;
        this.score = score;
        this.soundLoader = soundLoader;
        this.sphere_Speed = sphere_Speed;
    }

    // Check for choosed direction and move it
    protected void moveBall(){

        // Create new running Thread
        new Thread(new Runnable() {
            
            @Override
            public void run(){

            while(true){
                int s;

                // Check for seleted speed
                if (sphere_Speed.getSpeed() == 1){
                    s = 20;
                } else if (sphere_Speed.getSpeed() == 2){
                    s = 30;
                } else {
                    s = 40;
                }

                try {
                
                    // Get new direction if necessary (kinda Loonnnnggggg if statement....)
                    if (p1.x >= 1200 || p1.x <= -80 || p1.y >= 760 || p1.y <= 0 || p1.x >= paddle.p1.getX() && p1.x <= paddle.p1.getX() + 50 && p1.y >= paddle.p1.getY() - 50 && p1.y <= paddle.p1.getY() + 150 || p1.x >= paddle.p2.getX() - 50 && p1.x <= paddle.p2.getX() + 50 && p1.y >= paddle.p2.getY() - 50 && p1.y <= paddle.p2.getY() + 150){
                    dirE = getDirection();
                    }

                    // Move based on selected direction
                    switch (dirE) {
                        case UP_LEFT1:
                        p1.x -= s;
                        p1.y -= s;
                            break;
                    
                        case UP_LEFT2:
                        p1.x -= s + (s*0.5);
                        p1.y -= s - (s*0.5);
                            break;

                        case UP_RIGHT1:
                        p1.x += s;
                        p1.y -= s;
                            break;
                        
                        case UP_RIGHT2:
                        p1.x += s + (s*0.5);
                        p1.y -= s - (s*0.5);
                            break;

                        case DOWN_LEFT1:
                        p1.x -= s;
                        p1.y += s;
                             break;
                        
                        case DOWN_LEFT2:
                        p1.x -= s + (s*0.5);
                        p1.y += s - (s*0.5);
                            break;

                        case DOWN_RIGHT1:
                        p1.x += s;
                        p1.y += s;
                            break;
                            
                        case DOWN_RIGHT2:
                        p1.x += s + (s*0.5);
                        p1.y += s - (s*0.5);
                            break;
                        
                        case WIN1:
                        score.incScore(1);
                        p1.x = 550;
                        p1.y = 300;
                        dirE = fullyRandomD();
                            break;

                        case WIN2:
                        score.incScore(2);
                        p1.x = 550;
                        p1.y = 300;
                        dirE = fullyRandomD();
                            break;

                        case ERROR:
                            break;
                    }
            
                    Thread.sleep(30);
                } catch (Exception e) {
                    
                }
            }
        }
        }).start();

    }

    // Stops the ball from moving
    public void stopBall(){
        p1.x = 500;
        p1.y = 300;
    }

    // Get the Ball coords
    protected Point getPoint(){
        return p1;
    }

    // Check for possible direction choice and choose it between 2 variants
    private Direction getDirection(){

        // Check if ball reached the right Side 
        if (p1.x >= 1200){
            soundLoader.play(Sound.WON_POINT);
            return Direction.WIN1;
        }

        // Check if ball reached the left Side
        if (p1.x <= -80){
            soundLoader.play(Sound.WON_POINT);
            return Direction.WIN2;
        }

        // Check for collision with left paddle
        if (p1.x >= paddle.p1.getX() && p1.x <= paddle.p1.getX() + 50 && p1.y >= paddle.p1.getY() - 50 && p1.y <= paddle.p1.getY() + 150){
            return collisionLP();
        }

        // Check for collision with right paddle
        if (p1.x >= paddle.p2.getX() - 50 && p1.x <= paddle.p2.getX() + 50 && p1.y >= paddle.p2.getY() - 50 && p1.y <= paddle.p2.getY() + 150){
            return collisionRP();
        }

        // Check for collision with buttom
        if (p1.y >= 760){
            return collisionB();
        }

        // Check for collision with top
        if (p1.y <= 0){
            return collisionT();
        }

        return Direction.ERROR;
    }

    // Get random direction on collision with left paddle
    private Direction collisionLP(){

        soundLoader.play(Sound.PADDLE_COLLISION);
        dir = random.nextInt(3);
            dir ++;

            if (dir == 1){
                return Direction.UP_RIGHT1;
            } else if (dir == 2){
                return Direction.UP_RIGHT2;
            } else if (dir == 3){
                return Direction.DOWN_RIGHT1;
            } else if (dir == 4){
                return Direction.DOWN_RIGHT2;
            }
            return Direction.ERROR;
    }

    // Get random direction on collision with right paddle
    private Direction collisionRP(){

        soundLoader.play(Sound.PADDLE_COLLISION);
        dir = random.nextInt(3);
            dir ++;

            if (dir == 1){
                return Direction.UP_LEFT1;
            } else if (dir == 2){
                return Direction.UP_LEFT2;
            } else if (dir == 3){
                return Direction.DOWN_LEFT1;
            } else if (dir == 4){
                return Direction.DOWN_LEFT2;
            }
            return Direction.ERROR;
    }

    // Get random direction on collision with buttom
    private Direction collisionB(){

        dir = random.nextInt(1);
            dir ++;

            if (dir == 1 && dirE == Direction.DOWN_LEFT1 || dirE == Direction.DOWN_LEFT2){
                return Direction.UP_LEFT1;
            } else if (dir == 2 && dirE == Direction.DOWN_LEFT1 || dirE == Direction.DOWN_LEFT2){
                return Direction.UP_LEFT2;
            }

            if (dir == 1 && dirE == Direction.DOWN_RIGHT1 || dirE == Direction.DOWN_RIGHT2){
                return Direction.UP_RIGHT1;
            } else if (dir == 2 && dirE == Direction.DOWN_RIGHT1 || dirE == Direction.DOWN_RIGHT2){
                return Direction.UP_RIGHT2;
            }
            return Direction.ERROR;
    }

    // Get random direction on collision with top
    private Direction collisionT(){

        dir = random.nextInt(1);
            dir ++;

            if (dir == 1 && dirE == Direction.UP_LEFT1 || dirE == Direction.UP_LEFT2){
                return Direction.DOWN_LEFT1;
            } else if (dir == 2 && dirE == Direction.DOWN_LEFT1 || dirE == Direction.DOWN_LEFT2){
                return Direction.DOWN_LEFT2;
            }

            if (dir == 1 && dirE == Direction.UP_RIGHT1 || dirE == Direction.UP_RIGHT2){
                return Direction.DOWN_RIGHT1;
            } else if (dir == 2 && dirE == Direction.UP_RIGHT1 || dirE == Direction.UP_RIGHT2){
                return Direction.DOWN_RIGHT2;
            }
            return Direction.ERROR;
    }

    // Get a fully random direction with all possible variants on resets
    private Direction fullyRandomD(){

        dir = random.nextInt(7);
        dir ++;

        switch (dir) {
            case 1:
            return Direction.UP_LEFT1;
            case 2:
            return Direction.UP_LEFT2;
            case 3:
            return Direction.UP_RIGHT1;
            case 4:
            return Direction.UP_RIGHT2;
            case 5:
            return Direction.DOWN_LEFT1;
            case 6:
            return Direction.DOWN_LEFT2;
            case 7:
            return Direction.DOWN_RIGHT1;
            case 8:
            return Direction.DOWN_RIGHT2;
        }

        return Direction.ERROR;
    }

}
