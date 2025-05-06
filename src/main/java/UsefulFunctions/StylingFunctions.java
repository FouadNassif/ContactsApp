package UsefulFunctions;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StylingFunctions {

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
        if (field.getText() == null || field.getText().isEmpty()) {
            addBorderToField(field, "error");
            return false;
        }
        field.setBorder(b);
        return true;
    }
}
