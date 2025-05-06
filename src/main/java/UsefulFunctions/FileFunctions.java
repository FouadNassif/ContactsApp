package UsefulFunctions;

import Modal.Contact;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FileFunctions {

    public static ArrayList<Contact> emptyFileInList(File f) {
        ArrayList<Contact> tempList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Contact contact = (Contact) ois.readObject();
                    tempList.add(contact);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "A error occured while saving!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "A error occured while saving!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
        }
        return tempList;
    }

    public static Boolean saveToFile(ArrayList<Contact> list, File f) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Contact contact : list) {
                oos.writeObject(contact);
            }
            list.clear();
            oos.close();
            return true;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "A error occured while saving!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "A error occured while saving!", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
