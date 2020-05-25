package graphical;

import utilities.RWFile;
import utilities.Time;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * This class creates the training page.
 * @author Kierian Tirlemont, Tom Desormeaux--Delauney
 * @version 1
 */
public class DisplayNewTraining {

    public static JFrame myTrainingFrame;

    public static Container TrainingPageCont;

    public JButton returnButton;

    public static JButton startStop;

    public static JButton emergency;

    public static JLabel theTime;

    /**
     * The timer.
     */
    public static Timer myTimer = new Timer(1000,new java.awt.event.ActionListener(){
        public void actionPerformed(ActionEvent e) {
            Time.seconde++;
            Time.setTheTime();
            theTime.setText(Time.theTime);
        }
    });


    /**
     * The constructor of the class.
     */
    public DisplayNewTraining(){
        Time.setTheTime();
        initWindows();
    }

    /**
     * This class initializes the elements of the frame.
     */
    public void initWindows(){
        myTrainingFrame = new JFrame();

        myTrainingFrame.setTitle("Sail Manager : Training Page");
        myTrainingFrame.setVisible(true);

        TrainingPageCont = createTrainingPage();
        Container centerTraining = createMainTraining();

        myTrainingFrame.add(TrainingPageCont, BorderLayout.SOUTH);
        myTrainingFrame.add(centerTraining,BorderLayout.CENTER);

        myTrainingFrame.setLocationRelativeTo(null);
        myTrainingFrame.pack();
    }

    /**
     * This method creates the container which contains every panels
     * of this page.
     * @return A container.
     */
    private Container createTrainingPage(){

        Container welcomePageCont = new Container();
        welcomePageCont.setLayout(new BorderLayout());

        JPanel returnButton = createReturnButton();

        welcomePageCont.add(returnButton,BorderLayout.SOUTH);

        return welcomePageCont;
    }

    /**
     * Creates the return button's
     * @return The JPanel which contains the button.
     */
    private JPanel createReturnButton(){
        JPanel ret = new JPanel();

        this.returnButton = new JButton("Return");
        ret.add(this.returnButton);
        Action.buttonListener(this.returnButton,"Return", myTrainingFrame);

        return ret;
    }

    /**
     * Creates the element of the panel and puts it in a container.
     * @return A container which contains the elements.
     */
    private Container createMainTraining(){
        JPanel ret = new JPanel();

        emergency = new JButton("Emergency");
        Action.buttonListener(emergency,"Emergency");

        startStop = new JButton("Start");
        Time.buttonListener(startStop,"StartStop");

        JLabel labelImage = null;
        try {

            String path = URLDecoder.decode(DisplayNewTraining.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");

            String[] data = path.split("/");
            path ="";
            for(int i=0;i<data.length-2;i++){
                path+=data[i]+"/";
            }

            labelImage = new JLabel(new ImageIcon(ImageIO.read(new File(path+"data/googleMap.PNG"))));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        JLabel durationTraining = new JLabel("Duration of training : ");

        theTime = new JLabel(Time.theTime);

        JPanel haut = new JPanel();
        haut.setLayout(new GridLayout(1,2,10,10));
        haut.add(durationTraining);
        haut.add(theTime);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(1,1,10,10));
        center.add(labelImage);

        JPanel bas = new JPanel();
        bas.setLayout(new GridLayout(1,2,10,10));
        bas.add(startStop);
        bas.add(emergency);

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new GridLayout(3,1,10,10));
        finalPanel.add(haut);
        finalPanel.add(center);
        finalPanel.add(bas);

        ret.add(finalPanel);

        return ret;
    }
}
