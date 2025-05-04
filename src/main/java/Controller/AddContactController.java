package Controller;

import Modal.Contact;
import Modal.PhoneNumber;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java3.nfa035_fouadnassif_2339t.UsefulFunctions;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import view.AddContactView;

public class AddContactController {

    private AddContactView addContactView;
    private HashMap<String, String> phoneNumbers = new HashMap<>();

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
            JTextField[] fieldList = {addContactView.getFirstNameField(), addContactView.getLastNameField()};
            Border defaultBorder = new JTextField().getBorder();
            private Contact newContact = new Contact();
            boolean created = false;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!UsefulFunctions.checkField(fieldList, defaultBorder)) {
                    showErrorDialogMessage();
                    return;
                }

                DefaultTableModel model = addContactView.getTableModel();
                checkPhoneNumber(model);
                if (!newContact.getPhoneNumbers().isEmpty()) {
                    newContact.setFirstName(fieldList[0].getText());
                    newContact.setLastName(fieldList[1].getText());
                    if (addContactToDB(newContact)) {
                        addContactView.dispose();
                    }
                }
            }

            private void checkPhoneNumber(DefaultTableModel model) {
                boolean hasValidPhone = false;
                newContact.printPhoneNumbers();
                // Clearing the Phoneumber list so i can check for duplicate
                newContact.clearAllPhoneNumbers();
                for (int row = 0; row < model.getRowCount(); row++) {
                    Object regionCode = model.getValueAt(row, 0);
                    Object phoneNumber = model.getValueAt(row, 1);
                    if (regionCode != null && phoneNumber != null) {
                        if (checkRegionPhone(regionCode.toString(), phoneNumber.toString())) {
                            hasValidPhone = true;
                            if (!newContact.addPhoneNumber(new PhoneNumber(regionCode.toString(), phoneNumber.toString()))) {
                                JOptionPane.showMessageDialog(null, "Phone Number Already Exists!", "Error Message", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }

                if (!hasValidPhone) {
                    addContactView.getTable().setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    showErrorDialogMessage();
                }
            }

            private void showErrorDialogMessage() {
                JOptionPane.showMessageDialog(null, "A contact require a Name, Last Name and Phone Number!", "Error Message", JOptionPane.ERROR_MESSAGE);
            }

            private boolean checkRegionPhone(String rg, String pn) {
                if (!rg.trim().isEmpty() && !pn.trim().isEmpty()) {
                    try {
                        Integer.parseInt(rg);
                        Integer.parseInt(pn);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
                return true;
            }

            private boolean addContactToDB(Contact newContact) {
                try {
                    File f = new File("Contacts.obj");
                    FileOutputStream fos = new FileOutputStream(f);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(newContact);
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
        );
    }
}
