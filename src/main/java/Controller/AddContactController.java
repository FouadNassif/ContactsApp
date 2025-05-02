package Controller;

import java.awt.event.*;
import javax.swing.*;
import view.AddContactView;

public class AddContactController {

    private AddContactView addContactView;

    public AddContactController(AddContactView view) {
        this.addContactView = view;

        // Cancel Function
        addContactView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = "Comfirm Message";
                String message = "Do you want close this Window! \n All the Progress will be Gone!";
                JOptionPane optPane = new JOptionPane();
                int reponse = optPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (reponse == optPane.YES_OPTION) {
                    addContactView.dispose();
                } else {
                    optPane.setVisible(false);
                }
            }
        }
        );

        // Save Function
        addContactView.getSaveButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
