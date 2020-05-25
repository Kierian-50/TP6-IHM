package utilities;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class allows to write and to read file.
 * This class implements the Serializable class.
 * @Author Kierian Tirlemont
 * @version final
 */
public class RWFile {

    /**
     * This method allow to recover every lines of a folder past in parameter.
     * @param fileName The folder that you want to recover/return every lines.
     * @return An array which contains the lines of the files.
     */
    public static ArrayList<String> readFile (String fileName){
        ArrayList<String> list = new ArrayList<String>();
        Scanner in = null;
        try{

            String path = URLDecoder.decode(RWFile.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");

            String[] data = path.split("/");
            path ="";
            for(int i=0;i<data.length-2;i++){
                path+=data[i]+"/";
            }

            path += "data/";

            //Open the file
             in = new Scanner (new FileReader(path+fileName));
            //read the file and add it to the arrayList
            while(in.hasNextLine()){
                list.add(in.nextLine());
            }
            //close the file
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            in.close();
        }
        return list;
    }

    /**
     * This method allow to write on a file past in parameter.
     * The content that you want to add is in an array past
     * in parameter.
     * @param list The array which contains the content that you want to add on your file.
     * @param FileName The file where you want to write
     */
    public static void writeFile(ArrayList<String> list, String FileName){
        PrintWriter out = null;
        try{

            String path = URLDecoder.decode(RWFile.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");

            String[] data = path.split("/");
            path ="";
            for(int i=0;i<data.length-2;i++){
                path+=data[i]+"/";
            }

            path += "data/";

            //Open the file
            out = new PrintWriter (path+FileName);
            //Write in the file
            for(String ligne : list)
                out.println(ligne);

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

    /**
     * This method allow to write on a file past in parameter.
     * The content that you want to add is in an array past
     * in parameter.
     * @param theText The text in a String
     * @param FileName The file where you want to write
     */
    public static void writeFileString(String theText,String FileName){
        PrintWriter out = null;
        try{
            //Open the file
            out = new PrintWriter (FileName);
            //Write in the file
            out.println(theText);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

    /**
     * This class allows to create a file.
     * @param fileName The name of the file.
     */
    public static void createFile(String fileName){
        try{
            File newFile = new File(fileName);
            newFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This class allows to delete a file.
     * @param fileName The name of the file.
     */
    public static void deleteFile(String fileName){
        try {
            File deleteFile = new File(fileName);
            deleteFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This class allows to create a directory.
     * @param directoryName The name of the directory.
     */
    public static void createDirectory(String directoryName){
        try{
            File dir = new File(directoryName);
            dir.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This class allows to delete a directory.
     * @param directoryName The name of the directory.
     */
    public static void deleteDirectory(String directoryName){
        try{
            File dir = new File(directoryName);
            dir.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This class allows to print an arrayList.
     * @param theList The list that you want to print.
     */
    public static void printArrayList(ArrayList<String> theList){
        if(theList != null){
            for(String s : theList){
                System.out.println(s);
            }
        }else{
            System.err.println("printArrayList : error");
        }
    }

    /**
     * This method allows to write a float in file
     * in bytes thanks to the class DataOutputStream.
     * @param theFloat The float that you want to write
     *                 in a file.
     * @param fileName The name of the file.
     */
    public static void writeFloatDataOutputStream(Float theFloat, String fileName) throws IOException {
        FileOutputStream stream = null;
        try{

            stream = new FileOutputStream(fileName);
            DataOutputStream dataOutput = new DataOutputStream(stream);

            dataOutput.writeFloat(theFloat);

            dataOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            stream.close();
        }
    }

    /**
     * This method allows to read a float in a file
     * where the float is written in byte.
     * @param fileName The name of the file.
     * @return The float which is in the file.
     */
    public static float readFloatDataOutputStream(String fileName) throws IOException {
        float ret = 0;
        FileInputStream input = null;
        try{

            input = new FileInputStream(fileName);
            DataInputStream inst = new DataInputStream(input);

            ret = inst.readFloat();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            input.close();
        }
        return ret;
    }

    /**
     * This method write an object in a file.
     * @param object The object that you want to write.
     * @param fileName The name of the file.
     */
    public static void writeObject(Object object, String fileName) throws IOException {

        FileOutputStream file = null;

        try {

            file = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(file);

            oos.writeObject(object);

            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            file.close();
        }
    }

    /**
     * This method read an object which in a file.
     * @param fileName The name of the file.
     * @return The object that where in a file.
     */
    public static Object readObject(String fileName) throws IOException {

        Object theObject = null;
        FileInputStream file = null;

        try {

            file = new FileInputStream(fileName);
            ObjectInputStream oos = new ObjectInputStream(file);


            theObject = oos.readObject();

        } catch (Exception e) {
           System.err.println(e+"\nreadObject : Error : not an object for Serializable");
        }finally {
            file.close();
        }

        return theObject;
    }

    /**
     * This method sees if the file exists.
     * @param fileName The name of the file.
     * @return True : If the file exists
     *         False : If the file doesn't exist.
     */
    public static boolean isFile(String fileName){
        File file = new File(fileName);
        boolean ret = true;
        if(!file.exists()){
            ret = false;
        }
        return ret;
    }
}
