package utilities;

import graphical.DisplayConnexion;
import graphical.DisplayNewTraining;
import graphical.DisplayWelcomePage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * This class is used for the technical part of the program.
 * @author Kierian Tirlemont, Tom Desormeaux--Delauney
 * @version 1
 */
@SuppressWarnings("deprecation")
public class TechnicalClass {

    private static final String DELIMITER = "\\s*:\\s*";

    private static JFrame frameOptionPane;

    /**
     * This method allows to recovery the value enter
     * in the Text field by the user.
     */
    public static void recoveryTextField(){
        String nameEnter = DisplayConnexion.name.getText();
        String passwordEnter = DisplayConnexion.password.getText();

        ArrayList<String> theFile = RWFile.readFile("data/BDDuser.txt");

        boolean exist = isInFile(theFile,passwordEnter,nameEnter);

        if(exist){

             JOptionPane.showMessageDialog(frameOptionPane,"You're connected !");

             DisplayConnexion.myFrame.dispose();

             new DisplayWelcomePage();

        }else{
            JOptionPane.showMessageDialog(frameOptionPane,"The id or the password are incorrect !");
        }

    }

    /**
     * This method allows to create a new user.
     */
    public static void createUser(){
        String nameEnter = DisplayConnexion.name.getText();
        String passwordEnter = DisplayConnexion.password.getText();

        String theInformation = nameEnter+" : "+passwordEnter;
        ArrayList<String> theFile = RWFile.readFile("data/BDDuser.txt");

        if(emptyString(nameEnter) || emptyString(passwordEnter)
                || spaceIntoString(nameEnter) || spaceIntoString(passwordEnter)){
            JOptionPane.showMessageDialog(frameOptionPane,"Your account can't be create : " +
                    "Your name or password can't be empty or contains space character !");
        }else {

            if (isInFile(theFile, nameEnter)) {
                JOptionPane.showMessageDialog(frameOptionPane, "Your account can't be create : " +
                        "this user name already exist !");
            } else {
                RWFile.writeFileString(theInformation, "data/BDDuser.txt");
                theFile.add(theInformation);

                RWFile.writeFile(theFile, "data/BDDuser.txt");

                JOptionPane.showMessageDialog(frameOptionPane, "Your account has been created !");
            }
        }
    }

    /**
     * This method search in the arraylist if the name and the
     * password are already in the data base.
     * This method used for the connection.
     * @param theLine The arraylist of users.
     * @param thePwd The password.
     * @param theName The name.
     * @return true : if the name and the password are already
     * in the data base and else false.
     */
    private static boolean isInFile(ArrayList<String> theLine, String thePwd,String theName){
        boolean ret = false;
        int i = 0;
        String lineString;
        String[] theInformation = new String[2];

        while(!ret && theLine.size()>i){
            lineString = theLine.get(i);
            theInformation = lineString.split(DELIMITER);
            if(theInformation[0].equals(theName) && theInformation[1].equals(thePwd)){
                ret = true;
            }
            i++;
        }

        return ret;
    }

    /**
     * This method search in the arraylist if the name
     * are already in the data base.
     * This method used for the creation of account.
     * @param theLine The arraylist of user.
     * @param searchElement The name of the user.
     * @return true : if the name are already
     * in the data base and else false.
     */
    private static boolean isInFile(ArrayList<String> theLine, String searchElement){
        boolean ret = false;

        int i = 0;
        String lineString;
        String[] theInformation = new String[2];

        if(theLine.size()==0){
            ret=false;
        }else{
            while(!ret && theLine.size()>i){
                lineString = theLine.get(i);
                theInformation = lineString.split(DELIMITER);
                if(theInformation[0].equals(searchElement)){
                    ret = true;
                }
                i++;
            }
        }
        return ret;
    }

    /**
     * This method realises what happens when
     * you click on the button "new training".
     */
    public static void createPageNewTraining(){
        new DisplayNewTraining();
    }

    /**
     * This method display a frame which apologizes
     * because the function is not finish.
     */
    public static void createPageError(){
        JOptionPane.showMessageDialog(frameOptionPane,"This part is not available now" +
                "\nSorry for the inconveniences");
    }

    /**
     * This method remove the training's frame. And puts
     * the time (seconds, minutes, hours) to 0.
     * @param theFrame The frame that you have to remove.
     */
    public static void returnActionTraining(Frame theFrame){
        DisplayNewTraining.myTimer.stop();
        theFrame.dispose();
        Time.seconde = 0;
        Time.minute = 0;
        Time.heure = 0;
        Time.setTheTime();
    }

    /**
     * This method make the action when we click on the
     * emergency's button. It refers to a web site.
     */
    public static void emergencyAction(){
        try{
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://www.gouvernement.fr/risques/connaitre-les-numeros-d-urgence"));
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method asks to the user if the user has really finish
     * his training or does he want to continue.
     * @return True : if he has finish his training.
     * false : if he want to continue his training.
     */
    public static boolean isFinish(){
        boolean ret = false;
        int value;
        value = JOptionPane.showConfirmDialog(frameOptionPane,"Have you finish ?");
        if(value == 0){
            ret=true;
        }else if(value==1 || value==2){
            ret = false;
        }
        return ret;
    }

    /**
     * This method checks if the string is not empty.
     * @param string The string that we want check.
     * @return True : if the string is empty, false :
     * the string is not empty.
     */
    private static boolean emptyString(String string){
        boolean ret = false;

        if(string.length()==0){
            ret = true;
        }

        return ret;
    }

    /**
     * This method checks that there is no space in the string.
     * @param string The string that you want to check.
     * @return True if the string contains a space, false else.
     */
    private static boolean spaceIntoString(String string){
        boolean ret = false;

        if(string.contains(" ")){
            ret = true;
        }

        return ret;
    }

    /**
     * This method allows to change the tab of the frame.
     * @param switchSide The selected side (left or right).
     */
    public static void switchFrame(String switchSide){

        //Update the frame
        if(DisplayWelcomePage.welcomePage){
            if(switchSide.equalsIgnoreCase("Left")){
                DisplayWelcomePage.myFrame.remove(DisplayWelcomePage.welcomePageCont);
                DisplayWelcomePage.myFrame.add(DisplayWelcomePage.meteoPageCont);
                DisplayWelcomePage.myFrame.revalidate();
                DisplayWelcomePage.myFrame.repaint();
            }else if(switchSide.equalsIgnoreCase("Right")){
                DisplayWelcomePage.myFrame.remove(DisplayWelcomePage.welcomePageCont);
                DisplayWelcomePage.myFrame.add(DisplayWelcomePage.historiquePageCont);
                DisplayWelcomePage.myFrame.revalidate();
                DisplayWelcomePage.myFrame.repaint();
            }
        }else if(DisplayWelcomePage.meteoPage){
            if(switchSide.equalsIgnoreCase("Left")){
                DisplayWelcomePage.myFrame.remove(DisplayWelcomePage.meteoPageCont);
                DisplayWelcomePage.myFrame.add(DisplayWelcomePage.historiquePageCont);
                DisplayWelcomePage.myFrame.revalidate();
                DisplayWelcomePage.myFrame.repaint();
            }else if(switchSide.equalsIgnoreCase("Right")){
                DisplayWelcomePage.myFrame.remove(DisplayWelcomePage.meteoPageCont);
                DisplayWelcomePage.myFrame.add(DisplayWelcomePage.welcomePageCont);
                DisplayWelcomePage.myFrame.revalidate();
                DisplayWelcomePage.myFrame.repaint();
            }
        }else if(DisplayWelcomePage.historiquePage){
            if(switchSide.equalsIgnoreCase("Left")){
                DisplayWelcomePage.myFrame.remove(DisplayWelcomePage.historiquePageCont);
                DisplayWelcomePage.myFrame.add(DisplayWelcomePage.welcomePageCont);
                DisplayWelcomePage.myFrame.revalidate();
                DisplayWelcomePage.myFrame.repaint();
            }else if(switchSide.equalsIgnoreCase("Right")){
                DisplayWelcomePage.myFrame.remove(DisplayWelcomePage.historiquePageCont);
                DisplayWelcomePage.myFrame.add(DisplayWelcomePage.meteoPageCont);
                DisplayWelcomePage.myFrame.revalidate();
                DisplayWelcomePage.myFrame.repaint();
            }
        }

        //Update of the current page for the boolean to know where are we
        if(DisplayWelcomePage.welcomePage){
            if(switchSide.equalsIgnoreCase("Left")){ //gauche
                DisplayWelcomePage.meteoPage = true;
                DisplayWelcomePage.welcomePage = false;
                DisplayWelcomePage.historiquePage = false;
            }else if(switchSide.equalsIgnoreCase("Right")){ //droite
                DisplayWelcomePage.meteoPage = false;
                DisplayWelcomePage.welcomePage = false;
                DisplayWelcomePage.historiquePage = true;
            }
        }else if(DisplayWelcomePage.meteoPage){
            if(switchSide.equalsIgnoreCase("Left")){ //gauche
                DisplayWelcomePage.meteoPage = false;
                DisplayWelcomePage.welcomePage = false;
                DisplayWelcomePage.historiquePage = true;
            }else if(switchSide.equalsIgnoreCase("Right")){ //droite
                DisplayWelcomePage.meteoPage = false;
                DisplayWelcomePage.welcomePage = true;
                DisplayWelcomePage.historiquePage = false;
            }
        }else if(DisplayWelcomePage.historiquePage) {
            if (switchSide.equalsIgnoreCase("Left")) { //gauche
                DisplayWelcomePage.meteoPage = false;
                DisplayWelcomePage.welcomePage = true;
                DisplayWelcomePage.historiquePage = false;
            } else if (switchSide.equalsIgnoreCase("Right")) { //droite
                DisplayWelcomePage.meteoPage = true;
                DisplayWelcomePage.welcomePage = false;
                DisplayWelcomePage.historiquePage = false;
            }
        }
    }

    /**
     * This method make the action when the more's button in
     * weather's tab is selected. It refers to a web site.
     */
    public static void actionMoreMeteo(){
        try{
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("http://www.meteofrance.com/accueil"));
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

}
