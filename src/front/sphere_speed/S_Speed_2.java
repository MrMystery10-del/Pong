package front.sphere_speed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Sphere Speed button object, which sets the Speed Value on click
 * @author Kirils Turkins
 * @category Component
 */

public class S_Speed_2 extends JButton{

    public S_Speed_2(Sphere_Speed sphere_Speed){

        setBounds(31, 446, 113, 106);
        setFocusable(false);
        addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                sphere_Speed.setSpeed(2);
            }
            
        });
    }

    // Removes the button
    public void removeB(){
        setBounds(0, 1000, 400, 100);
    }

}
