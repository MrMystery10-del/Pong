package front.paddle_speed;

/**
 * Paddle Speed, returns selected speed and sets a new speed
 * @author Kirils Turkins
 * @category Logic
 */

public class Paddle_Speed {
    
    private int speed = 1;

    // returns the sphere speed value
    public int getSpeed(){
        return speed;
    }

    // Set the Sphere Speed
    public void setSpeed(int n){
        speed = n;
    }
}
