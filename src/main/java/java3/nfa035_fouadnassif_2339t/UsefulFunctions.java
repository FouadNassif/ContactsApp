package java3.nfa035_fouadnassif_2339t;

import Exception.EmptyStringException;
import Modal.Contact;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class UsefulFunctions {

    public static void checkString(String s) throws EmptyStringException {
        if (s == null || s.isEmpty()) {
            throw new EmptyStringException("Fields are missing or empty!!");
        }
    }

    public static void checkString(String s, String Message) throws EmptyStringException {
        if (s == null || s.isEmpty()) {
            throw new EmptyStringException(Message);
        }
    }

    public static void addBorderToField(JTextField field, String type) {
        if (type.equals("error")) {
            Border border = BorderFactory.createLineBorder(Color.RED, 2);
            field.setBorder(border);
        }
    }

    public static void clearBorderFromField(JTextField[] fields, Border b) {
        for (JTextField field : fields) {
            clearBorderFromField(field, b);
        }
    }

    public static void clearBorderFromField(JTextField field, Border b) {
        field.setBorder(b);
    }

    // Check field from field list
    public static boolean checkField(JTextField[] fields, Border b) {
        boolean state = true;
        for (JTextField field : fields) {
            state = checkField(field, b);
        }
        return state;
    }

    // Check field from only one field
    public static boolean checkField(JTextField field, Border b) {
        boolean state = true;
        if (field.getText() == null || field.getText().isEmpty()) {
            addBorderToField(field, "error");
            return false;
        }
        field.setBorder(b);
        return true;
    }

    // Files related Functions
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

    public static void renderTableByList(ArrayList<Contact> list, DefaultTableModel model) {
        model.setRowCount(0);
        for (Contact contact : list) {
            model.addRow(new String[]{contact.toString()});
        }
    }
}
