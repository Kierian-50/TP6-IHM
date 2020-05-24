package graphical;

import utilities.TechnicalClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The action's class of the display class.
 * @author Kierian Tirlemont, Tom Desormeaux--Delauney
 * @version 1
 */
public class Action {

    /**
     * This method finds the selected button and calls the good
     * method which will do the correct action thanks to the
     * name of the button.
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

                    case "New training":
                        TechnicalClass.createPageNewTraining();
                        break;

                    case "Switch button right":
                        TechnicalClass.switchFrame("Right");
                        break;

                    case "Switch button left":
                        TechnicalClass.switchFrame("Left");
                        break;

                    case "Emergency":
                        TechnicalClass.emergencyAction();
                        break;

                    case "More meteo":
                        TechnicalClass.actionMoreMeteo();
                        break;

                    default:
                        TechnicalClass.createPageError();
                        break;
                }
            }
        });
    }

    /**
     * This method finds the selected button and calls the good
     * method which will do the correct action thanks to the
     * name of the button. Here we've the frame in parameter because
     * I must delete the frame in parameter.
     * @param button The button.
     * @param buttonName The name of the button.
     * @param frame The frame that you must delete.
     */
    public static void buttonListener(JButton button, String buttonName, Frame frame){ //déclaration de ta méthode
        button.addActionListener(new java.awt.event.ActionListener(){ //tu mets en écoute le boutton
            public void actionPerformed(ActionEvent e) {

                switch (buttonName){
                    case "Return":
                        TechnicalClass.returnActionTraining(frame);
                        break;

                    default:
                        TechnicalClass.createPageError();
                        break;
                }
            }
        });
    }

}
