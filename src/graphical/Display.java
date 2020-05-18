package graphical;

import javax.swing.*;
import java.awt.*;

/**
 * This class to create the frame and to create every around the frame (buttons, label...)
 * It creates also a timer with his ActionListener.
 * @author Kierian Tirlemont, Tom Desormeaux--Delauney
 * @version 1
 */
public class Display {

    public static JTextField name;

    public static JPasswordField password;

    public JButton submit;

    public JButton createAccount;

    /**
     * The constructor of the class.
     * It sets the time and init the frame's components
     */
    public Display(){
        initWindows();
    }

    /**
     * The entry point of the program.
     * @param args The arguments.
     */
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Display();
            }
        });
    }

    /**
     * This class create the frame and calls
     * others methods to recovery every elements
     * need to the frame.
     */
    public void initWindows(){
        JFrame myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        myFrame.setTitle("Sail Manager : Connexion");
        myFrame.setVisible(true);

        //Create a container to set every panels
        Container monCont = new Container();
        monCont.setLayout(new BorderLayout());

        JPanel textField = creationJTextFieldNamePwd();
        JPanel buttons = creationJButtonSubmitCreate();
        JPanel emptyLabel1 = createEmptyLabel();
        JPanel emptyLabel2 = createEmptyLabel();
        JPanel emptyLabel3 = createEmptyLabel();

        monCont.add(emptyLabel1,BorderLayout.NORTH);
        monCont.add(emptyLabel2,BorderLayout.EAST);
        monCont.add(emptyLabel3,BorderLayout.WEST);
        monCont.add(textField,BorderLayout.CENTER);
        monCont.add(buttons,BorderLayout.SOUTH);

        myFrame.add(monCont);
        myFrame.setLocationRelativeTo(null);
        myFrame.pack();
    }

    /**
     * This method create and returns the JPanel.
     * It create the JPanel which contains the JTextField.
     * @return The JPanel with the JTextField.
     */
    private JPanel creationJTextFieldNamePwd(){
        JPanel ret = new JPanel();

        JLabel labelId = new JLabel("Id : ");
        JLabel labelPwd = new JLabel("Password : ");

        this.name = new JTextField();
        this.password = new JPasswordField();

        JLabel emptyLabel = new JLabel("  ");

        ret.add(labelId);
        ret.add(name);
        ret.add(labelPwd);
        ret.add(password);
        ret.add(emptyLabel);

        ret.setLayout(new GridLayout(5,1,5,5));

        return ret;
    }

    /**
     * This method create and returns the JPanel.
     * It create the JPanel which contains the JButton.
     * @return The JPanel with the JButton.
     */
    private JPanel creationJButtonSubmitCreate(){
        JPanel ret = new JPanel();

        this.submit = new JButton("Submit");
        this.createAccount = new JButton("Create an account");

        ret.add(this.submit);
        Action.buttonListener(submit,"Submit");
        this.submit.setSize(50,50);

        ret.add(this.createAccount);
        Action.buttonListener(createAccount,"Create an account");

        ret.setLayout(new GridLayout(2,1,5,5));

        return ret;
    }

    /**
     * This method create and returns the JPanel.
     * It create the JPanel which contains an empty label.
     * @return The JPanel with an empty label.
     */
    private JPanel createEmptyLabel(){
        JPanel ret = new JPanel();

        JLabel emptyLabel = new JLabel("        ");

        ret.add(emptyLabel);

        return ret;
    }
}
