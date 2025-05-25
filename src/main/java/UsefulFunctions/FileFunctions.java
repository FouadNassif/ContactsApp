package UsefulFunctions;

import Model.Contact;
import Model.Group;
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
        if (f.length() > 0) {
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

    public static ArrayList<Group> emptyFileInListGroup(File f) {
        ArrayList<Group> tempList = new ArrayList<>();
        if (f.length() > 0) {

            try {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);

                while (true) {
                    try {
                        Group group = (Group) ois.readObject();
                        tempList.add(group);
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "A error occured while saving1!", "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "A error occured while saving2!", "Error Message", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e) {
            }
        }
        return tempList;
    }

    public static Boolean saveToFileGroup(Group newGroup, File f) {
        ArrayList<Group> tempList = emptyFileInListGroup(f);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Group group : tempList) {
                oos.writeObject(group);
            }
            oos.writeObject(newGroup);
            tempList.clear();
            oos.close();
            return true;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "A error occured while saving3!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "A error occured while saving4!", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static Boolean saveToFileGroup(ArrayList<Group> list, File f) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Group group : list) {
                oos.writeObject(group);
            }
            list.clear();
            oos.close();
            return true;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "A error occured while saving5!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "A error occured while saving6!", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
