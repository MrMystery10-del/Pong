package base;

import java.awt.Point;

import front.paddle_speed.Paddle_Speed;

/**
 * Gives the stats of the paddles
 * @author Kirils Turkins
 * @category Pong Component
 */

public class Paddle {
    
    private int s = 1;

    //Coords of the Paddles
    Point p1 = new Point(10, 10);
    Point p2 = new Point(1120, 10);

    private Paddle_Speed paddle_Speed;

    // Paddle body
    protected Paddle(Paddle_Speed paddle_Speed){

        this.paddle_Speed = paddle_Speed;
    }

    // set the paddle speed based on the selected padle speed
    protected void setSpeed(){

        if (paddle_Speed.getSpeed() == 1){
            s = 20;
        } else if (paddle_Speed.getSpeed() == 2){
            s = 30;
        } else {
            s = 40;
        }
    }

    // Get coords from paddle 1
    public Point getPoint1(){
        return p1;
    }

    // Get coords from paddle 2
    public Point getPoint2(){
        return p2;
    }

    // Increace the Y coord of paddle 1 by selecte speed
    protected void incY1(){
        p1.y += s;
    }

    // Decreace the Y coord of paddle 1 by selecte speed
    protected void decY1(){
        p1.y -= s;
    }

    // Increace the Y coord of paddle 2 by selecte speed
    protected void incY2(){
        p2.y += s;
    }

    // Decreace the Y coord of paddle 1 by selecte speed
    protected void decY2(){
        p2.y -= s;
    }

    // Set the coords from paddle 1
    protected void setPoint1(int coords){
        p1.y = coords;

    }

    // Set the coords from paddle 2
    protected void setPoint2(int coords){
        p2.y = coords;

    }
}
