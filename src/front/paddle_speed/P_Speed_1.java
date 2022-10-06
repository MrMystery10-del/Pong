package front.paddle_speed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Paddle Speed button object, which sets the Speed Value on click
 * @author Kirils Turkins
 * @category Component
 */

public class P_Speed_1 extends JButton{

    public P_Speed_1(Paddle_Speed paddle_Speed){

        setBounds(1036, 572, 114, 105);
        setFocusable(false);
        addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                paddle_Speed.setSpeed(1);
            }
            
        });
    }

    // Removes the button
    public void removeB(){
        setBounds(0, 1000, 400, 100);
    }

}
