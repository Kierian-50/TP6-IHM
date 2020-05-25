package graphical;

import utilities.RWFile;
import utilities.TechnicalClass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * This class represents the creation of the center of the application
 * with the welcome page.
 * @author Kierian Tirlemont, Tom Desormeaux--Delauney
 * @version 1
 */
public class DisplayWelcomePage {

    public static JFrame myFrame;

    public static Container welcomePageCont;

    public static Container historiquePageCont;

    public static Container meteoPageCont;

    public JButton newTraining;

    public JButton switchLeftButton;

    public JButton switchRightButton;

    public static boolean welcomePage;

    public static boolean meteoPage;

    public static boolean historiquePage;

    public static DefaultListModel<String> listModel;
    public static JList<String> list;

    public String pathWelcomePage;

    /**
     * The constructor of the class.
     */
    public DisplayWelcomePage(){
        welcomePage = true;
        meteoPage = false;
        historiquePage = false;

        try {
            String path = URLDecoder.decode(DisplayWelcomePage.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");

            String[] data = path.split("/");
            path ="";
            for(int i=0;i<data.length-2;i++){
                path+=data[i]+"/";
            }

            pathWelcomePage = path;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        initWindows();
    }

    /**
     * This method initializes the frame.
     */
    public void initWindows(){
        myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        myFrame.setTitle("Sail Manager : Welcome page");
        myFrame.setVisible(true);

        meteoPageCont = createMeteoFrame();
        historiquePageCont = createHistoriqueFrame();
        welcomePageCont = createWelcomePage();

        myFrame.add(welcomePageCont);

        myFrame.setLocationRelativeTo(null);
        myFrame.pack();
    }

    /**
     * This method create the container which contains every panels
     * of this page.
     * @return A container.
     */
    private Container createWelcomePage(){

        Container welcomePageCont = new Container();
        welcomePageCont.setLayout(new BorderLayout());

        JPanel buttonNewTraining = createButtonNewTraining();
        JPanel imageBoat = createImageForegroundWelcomePage();
        JPanel imageSwitchEntrainement = namePageSwitchEntrainemet();
        JPanel buttonSwitchPageRight = switchButtonRight();
        JPanel buttonSwitchPageLeft = switchButtonLeft();

        welcomePageCont.add(buttonNewTraining,BorderLayout.SOUTH);
        welcomePageCont.add(imageBoat,BorderLayout.CENTER);
        welcomePageCont.add(imageSwitchEntrainement,BorderLayout.NORTH);
        welcomePageCont.add(buttonSwitchPageRight,BorderLayout.EAST);
        welcomePageCont.add(buttonSwitchPageLeft,BorderLayout.WEST);

        return welcomePageCont;
    }

    /**
     * This method initializes the button newTraining.
     * @return The JPanel.
     */
    private JPanel createButtonNewTraining(){
        JPanel ret = new JPanel();

        this.newTraining = new JButton("New training");

        ret.add(this.newTraining);
        Action.buttonListener(this.newTraining,"New training");
        this.newTraining.setSize(50,50);

        return ret;
    }

    /**
     * Creates the image on the welcome page with the boat.
     * @return A panel with the image.
     */
    private JPanel createImageForegroundWelcomePage(){
        JPanel ret = new JPanel();

        try {

            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(pathWelcomePage+"data/imageBateauAVoile.JPG"))));
            ret.add(label);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    /**
     * Creates the image on the top of the frame with the
     * name of the tab.
     * @return The panel with the image.
     */
    private JPanel namePageSwitchEntrainemet(){
        JPanel ret = new JPanel();

        try {

            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(pathWelcomePage+"data/switchPageEntrainement.JPG"))));
            ret.add(label);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    /**
     * Creates the right button arrow.
     * (right = droite = east)
     * @return A panel with the button which has
     * the arrow as icon.
     */
    private JPanel switchButtonRight(){
        JPanel ret = new JPanel();

        this.switchLeftButton = new JButton();

        try {

            Image img = ImageIO.read(new File(pathWelcomePage+"data/flecheDroiteTom.PNG"));
            this.switchLeftButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        this.switchLeftButton.setSize(50,50);
        ret.add(this.switchLeftButton);
        Action.buttonListener(this.switchLeftButton,"Switch button right");

        return ret;
    }

    /**
     * Creates the left button arrow.
     * (left = gauche = west)
     * @return A panel with the button which has
     * the arrow as icon.
     */
    private JPanel switchButtonLeft(){
        JPanel ret = new JPanel();

        this.switchRightButton = new JButton();

        try {

            Image img = ImageIO.read(new File(pathWelcomePage+"data/flecheGaucheTom.PNG"));
            this.switchRightButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        this.switchRightButton.setSize(50,50);
        ret.add(this.switchRightButton);
        Action.buttonListener(this.switchRightButton,"Switch button left");

        return ret;
    }

    /**
     * Creates image which contains the position on the tab.
     * @return The panel with this image.
     */
    private JPanel namePageSwitchMeteo(){
        JPanel ret = new JPanel();

        try {

            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(pathWelcomePage+"data/switchPageMeteo.JPG"))));
            ret.add(label);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    /**
     * This method allows to create the weather's image.
     * @return A panel with the weather's image.
     */
    private JPanel imageMeteo(){
        JPanel ret = new JPanel();

        try {

            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(pathWelcomePage+"data/meteoPage.JPG"))));
            ret.add(label);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    /**
     * This method creates a "more" button and puts it in a panel.
     * @return A panel which contains the button.
     */
    private JPanel createButtonMoreMeteo(){
        JPanel ret = new JPanel();

        JButton button = new JButton("More...");

        ret.add(button);
        Action.buttonListener(button,"More meteo");

        return ret;
    }

    /**
     * This method creates a container which contains
     * the element of the weather's frame.
     * @return A container which contains the element
     * of this tab.
     */
    private Container createMeteoFrame(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        JPanel buttonSwitchPageRight = switchButtonRight();
        JPanel buttonSwitchPageLeft = switchButtonLeft();
        JPanel imageSwitch = namePageSwitchMeteo();
        JPanel imageMeteo = imageMeteo();
        JPanel buttonMore = createButtonMoreMeteo();

        ret.add(buttonSwitchPageLeft,BorderLayout.WEST);
        ret.add(buttonSwitchPageRight,BorderLayout.EAST);
        ret.add(imageSwitch,BorderLayout.NORTH);
        ret.add(imageMeteo,BorderLayout.CENTER);
        ret.add(buttonMore,BorderLayout.SOUTH);

        return ret;
    }

    /**
     * This method creates a "more..." button and puts it in a panel.
     * @return A panel which contains the button.
     */
    private JPanel createButtonMoreHistorique(){
        JPanel ret = new JPanel();

        JButton button = new JButton("More...");

        ret.add(button);
        Action.buttonListener(button,"More historique");

        return ret;
    }

    /**
     * This method creates the image which indicates the
     * position in the tabs and puts it in a panel.
     * @return A panel which contains the image.
     */
    private JPanel namePageSwitchHistorique(){
        JPanel ret = new JPanel();

        try {

            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(pathWelcomePage+"data/switchPageHistorique.JPG"))));
            ret.add(label);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    /**
     * This method creates a container which contains every elements
     * of the history's tab.
     * @return A container which contains every elements of the tab.
     */
    private Container createHistoriqueFrame(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        JPanel buttonSwitchPageRight = switchButtonRight();
        JPanel buttonSwitchPageLeft = switchButtonLeft();
        JPanel imageSwitch = namePageSwitchHistorique();
        JPanel moreButton = createButtonMoreHistorique();
        JPanel scrollPane = createscrollPane();

        ret.add(buttonSwitchPageLeft,BorderLayout.WEST);
        ret.add(buttonSwitchPageRight,BorderLayout.EAST);
        ret.add(imageSwitch,BorderLayout.NORTH);
        ret.add(moreButton,BorderLayout.SOUTH);
        ret.add(scrollPane,BorderLayout.CENTER);

        return ret;
    }

    /**
     * This method creates a scroll pane to see the old training.
     * @return A panel which contains the scroll pane.
     */
    private JPanel createscrollPane(){
        JPanel ret = new JPanel();

        listModel = new DefaultListModel<String>();
        list = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);

        ret.add(scrollPane);

        return ret;
    }
}
