package java3.nfa035_fouadnassif_2339t;

import Exception.EmptyStringException;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.*;

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
}
