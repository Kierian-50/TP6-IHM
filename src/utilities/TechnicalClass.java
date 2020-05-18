package utilities;

import graphical.Display;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class is used for the technical part of the program.
 * @author Kierian Tirlemont, Tom Desormeaux--Delauney
 * @version 1
 */
@SuppressWarnings("deprecation")
public class TechnicalClass {

    /**
     * The delimiter
     */
    private static final String DELIMITER = "\\s*:\\s*";

    private static JFrame frameOptionPane;

    /**
     *
     */
    public static void recoveryTextField(){
        String nameEnter = Display.name.getText();
        String passwordEnter = Display.password.getText();

        ArrayList<String> theFile = RWFile.readFile("data/BDDuser.txt");

        boolean exist = isInFile(theFile,nameEnter,passwordEnter);

        if(exist){
             JOptionPane.showMessageDialog(frameOptionPane,"You're connected !");
        }else{
            JOptionPane.showMessageDialog(frameOptionPane,"The id or the password are incorrect !");
        }

    }

    public static void createUser(){
        String nameEnter = Display.name.getText();
        String passwordEnter = Display.password.getText();

        String theInformation = nameEnter+" : "+passwordEnter;
        ArrayList<String> theFile = RWFile.readFile("data/BDDuser.txt");

        if(isInFile(theFile,nameEnter)){
            JOptionPane.showMessageDialog(frameOptionPane,"Your account can't be create : " +
                    "this user name already exist !");
        }else{
            RWFile.writeFileString(theInformation,"data/BDDuser.txt");
            theFile.add(theInformation);

            RWFile.writeFile(theFile,"data/BDDuser.txt");

            JOptionPane.showMessageDialog(frameOptionPane,"Your account has been created !");
        }
    }

    /**
     *
     * @param theLine
     * @param thePwd
     * @param theName
     * @return
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
     *
     * @param theLine
     * @param searchElement
     * @return
     */
    private static boolean isInFile(ArrayList<String> theLine, String searchElement){
        boolean ret = false;

        int i = 0;
        String lineString;
        String[] theInformation = new String[2];

        while(!ret || theLine.size()<i){
            lineString = theLine.get(i);
            theInformation = lineString.split(DELIMITER);
            if(theInformation[0].equals(searchElement)){
                ret = true;
            }
            i++;
        }
        return ret;
    }

}
