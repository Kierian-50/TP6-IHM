package graphical;

import utilities.TechnicalClass;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The action's class of the display class.
 * @author Kierian Tirlemont, Tom Desormeaux--Delauney
 * @version 1
 */
public class Action {

    /**
     * The button listener. It allows to say what happen if you click
     * on the reset, start and stop buttons.
     * It looks the selected mode thanks to the booleans.
     * @param button The button.
     * @param buttonName The name of the button.
     */
    public static void buttonListener(JButton button, String buttonName){ //déclaration de ta méthode
        button.addActionListener(new java.awt.event.ActionListener(){ //tu mets en écoute le boutton
            public void actionPerformed(ActionEvent e) {

                switch (buttonName){
                    case "Submit":
                        TechnicalClass.recoveryTextField();
                        break;

                    case "Create an account":
                        TechnicalClass.createUser();
                        break;

                    default:
                        break;
                }
            }
        });
    }

}
