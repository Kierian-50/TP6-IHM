package utilities;

import graphical.DisplayNewTraining;
import graphical.DisplayWelcomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Date;

/**
 * This class takes care about the action which refers to the time.
 * @author Kierian Tirlemont, Tom Desormeaux--Delauney
 * @version 1
 */
public class Time {

    public static int seconde = 0;

    public static int minute = 0;

    public static int heure = 0;
    
    public static String theTime;

    /**
     * The action when we select the button start and stop.
     * It starts and stops the timer. And do others things
     * as add the training on the Scroll Pane when you click
     * and stop and yes.
     * @param button The button.
     * @param buttonName The name of the button.
     */
    public static void buttonListener(JButton button, String buttonName){
        button.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e) {

                switch (buttonName){
                    case "StartStop":
                        if(button.getText()=="Start"){
                            DisplayNewTraining.startStop.setText("Stop");
                            DisplayNewTraining.myTimer.start();
                        }else if(button.getText()=="Stop"){
                            DisplayNewTraining.startStop.setText("Start");
                            DisplayNewTraining.myTimer.stop();
                            boolean finish = TechnicalClass.isFinish();
                            if(finish){
                                Date today = new Date();
                                DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                                        DateFormat.SHORT,
                                        DateFormat.SHORT);
                                String label = "The training on "+shortDateFormat.format(today)+" lasted "+theTime;
                                TechnicalClass.returnActionTraining(DisplayNewTraining.myTrainingFrame);
                                DisplayWelcomePage.listModel.addElement(label);
                            }
                        }
                        break;

                    default:
                        TechnicalClass.createPageError();
                        break;
                }
            }
        });
    }

    /**
     * This method set the time. In function,
     * of the value of the integer variable
     * (minute, seconds, hours), it creates a valid
     * time and give it to the string attribute
     * the time.
     */
    public static void setTheTime(){
        String secondeString = String.valueOf(seconde); //create the string variable for the seconds
        if(seconde<10 && seconde>=0){ //if the second is between 0 and 9
            secondeString = "0"+secondeString; //we need to had an 0 before the second
        }else if(seconde>=10 && seconde <60){ //if the second is between 10 and 59
            secondeString = ""+secondeString; //we do nothing
        }else if(seconde >=60){ //if the time is a large number as 60
            seconde = 0; //Seconde will have the value 0
            secondeString = "00"; //and the value of the string is 00
            minute++; //we need to add a new minute
        }else if(seconde<0 && minute<=0){ //if the seconds and the minute is less that 0
            seconde = 0; //the second need to stay to 0
            secondeString = "00";
            minute = 0; //the minute need to stau to 0

        }else if(seconde<0 && minute>0){ //if the second is less than 0 but we have more than 1 minute left
            seconde = 59; //the new value of the second
            secondeString = ""+seconde; //give it to the secondeString
            minute--; //We lose one minute
        }
        String minuteString = String.valueOf(minute); //create the string for the minute
        if(minute<10){ //if we have less than 10 minutes
            minuteString = "0"+minuteString; //we need to add a 0 to the string
        }else if(minute>=10){ //if the minute is bigger than 10
            minuteString = ""+minuteString; //we do noting
        }

        String heureString = String.valueOf(heure);
        if(minute>=60 && heure<10){
            heure++;
            minute=0;
            minuteString = "00";
            heureString=""+heure;
        }else if(minute>=60 && heure>=10){
            heure++;
            minute=0;
            minuteString = "00";
            heureString=""+heure;
        }

        //and we create the final time
        theTime = heureString+":"+minuteString+":"+secondeString;
    }
}
