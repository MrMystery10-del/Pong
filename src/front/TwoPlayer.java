package front;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Two Player button object, which return a started boolean on click
 * @author Kirils Turkins
 * @category Logic
 */

public class TwoPlayer extends JButton{

    boolean started = false;

    // start two player game button
    public TwoPlayer(){

        setBounds(318, 249, 534, 160);
        setFocusable(false);
        addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                started = true;
                setBounds(0, 1000, 400, 100);
            }
            
        });
    }

    // Check if the game got started
    public boolean gameStarted(){
        return started;
    }

}
