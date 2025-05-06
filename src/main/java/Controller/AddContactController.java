package Controller;

import Modal.Contact;
import Modal.Group;
import Modal.PhoneNumber;
import Observable.ContactObservable;
import UsefulFunctions.ErrorFunctions;
import UsefulFunctions.FileFunctions;
import UsefulFunctions.StylingFunctions;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import view.AddContactView;

public class AddContactController {

    private AddContactView addContactView;
    private ContactObservable observable;
    ArrayList<Group> groups = new ArrayList<>();

    public AddContactController(AddContactView view, ContactObservable observable) {
        this.addContactView = view;
        this.observable = observable;
        renderGroups();

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

            @Override
            public void actionPerformed(ActionEvent e) {
                getSelectedGroups();
                if (!StylingFunctions.checkField(fieldList, defaultBorder)) {
                    ErrorFunctions.showErrorDialogMessage("A contact require a Name, Last Name and Phone Number!", "Error Message");
                    return;
                }

                DefaultTableModel model = addContactView.getTableModel();
                checkPhoneNumber(model);
                getSelectedGroups();
                if (!newContact.getPhoneNumbers().isEmpty()) {
                    newContact.setFirstName(fieldList[0].getText());
                    newContact.setLastName(fieldList[1].getText());
                    newContact.setCity(addContactView.getCityField().getText());
                    if (addContactToDB(newContact)) {
                        observable.contactAdded(newContact);
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
                                ErrorFunctions.showErrorDialogMessage("Phone Number Already Exists!", "Error Message");
                            }
                        }
                    }
                }

                if (!hasValidPhone) {
                    addContactView.getTable().setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    ErrorFunctions.showErrorDialogMessage("Phone number must contain digits only. \nPlease enter a valid number.", "Error Message");
                }
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
                File f = new File("Contacts.obj");
                ArrayList<Contact> tempContactsList = FileFunctions.emptyFileInList(f);
                tempContactsList.add(newContact);
                return FileFunctions.saveToFile(tempContactsList, f);
            }

            private void getSelectedGroups() {
                ArrayList<JCheckBox> checkBoxList = addContactView.getCheckBoxes();
                for (JCheckBox checkBox : checkBoxList) {
                    if (checkBox.isSelected()) {
                        System.out.println(checkBox.getText());
                    }
                }
            }
        }
        );
    }

    private void renderGroups() {
        groups.add(new Group("No groups", ""));
        try {
            File f = new File("Groups.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Group group = (Group) ois.readObject();
                    groups.add(group);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
            addContactView.renderGroups(groups);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "A error occured while saving!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "A error occured while saving!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
        }
    }
}
