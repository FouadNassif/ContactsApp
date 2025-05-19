package Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CancelButton implements ActionListener {

    private final JFrame frameToClose;

    public CancelButton(JFrame frameToClose) {
        this.frameToClose = frameToClose;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = "Confirm Message";
        String message = "Do you want to close this window?\nAll progress will be lost!";

        int response = JOptionPane.showConfirmDialog(
                frameToClose,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            frameToClose.dispose();
        }
    }
}
