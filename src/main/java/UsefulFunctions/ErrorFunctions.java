package UsefulFunctions;

import javax.swing.JOptionPane;

public class ErrorFunctions {

    // Error Message Option Pane
    public static void showErrorDialogMessage(String Message, String title) {
        JOptionPane.showMessageDialog(null, Message, title, JOptionPane.ERROR_MESSAGE);
    }
}
