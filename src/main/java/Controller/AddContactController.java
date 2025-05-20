package Controller;

import Components.CancelButton;
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
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.CONTACT_FILE;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.GROUP_FILE;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import view.AddContactView;

public class AddContactController {

    private AddContactView addContactView;
    private ContactObservable observable;
    ArrayList<Group> groups = new ArrayList<>();
    File contactsFile = CONTACT_FILE;
    File groupsFile = GROUP_FILE;

    public AddContactController(AddContactView view, ContactObservable observable) {
        this.addContactView = view;
        this.observable = observable;
        renderGroups();

        // Cancel Function
        addContactView.getCancelButton().addActionListener(new CancelButton(addContactView));

        // Save Function
        addContactView.getSaveButton().addActionListener(new ActionListener() {
            JTextField[] fieldList = {addContactView.getFirstNameField(), addContactView.getLastNameField()};
            Border defaultBorder = new JTextField().getBorder();
            private Contact newContact = new Contact();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!StylingFunctions.checkField(fieldList, defaultBorder)) {
                    ErrorFunctions.showErrorDialogMessage("A contact require a Name, Last Name and Phone Number!", "Error Message");
                    return;
                }

                DefaultTableModel model = addContactView.getTableModel();
                if (!checkPhoneNumber(model)) {
                    return;
                }

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

            private boolean checkPhoneNumber(DefaultTableModel model) {
                boolean hasValidPhone = false;
                newContact.printPhoneNumbers();
                newContact.clearAllPhoneNumbers();

                for (int row = 0; row < model.getRowCount(); row++) {
                    Object regionCode = model.getValueAt(row, 0);
                    Object phoneNumber = model.getValueAt(row, 1);

                    boolean regionFilled = regionCode != null && !regionCode.toString().trim().isEmpty();
                    boolean phoneFilled = phoneNumber != null && !phoneNumber.toString().trim().isEmpty();

                    if (regionFilled && phoneFilled) {
                        if (checkRegionPhone(regionCode.toString(), phoneNumber.toString())) {
                            hasValidPhone = true;
                            if (!newContact.addPhoneNumber(new PhoneNumber(regionCode.toString(), phoneNumber.toString()))) {
                                ErrorFunctions.showErrorDialogMessage("Phone Number Already Exists!", "Error Message");
                                return false;
                            }
                        } else {
                            ErrorFunctions.showErrorDialogMessage("Phone number must contain digits only.\nPlease enter a valid number.", "Error Message");
                            return false;
                        }
                    } else if (regionFilled || phoneFilled) {
                        ErrorFunctions.showErrorDialogMessage("Both Region Code and Phone Number must be filled for each row.", "Error Message");
                        return false;
                    }
                }

                if (!hasValidPhone) {
                    addContactView.getTable().setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    ErrorFunctions.showErrorDialogMessage("At least one valid phone number is required.", "Error Message");
                    return false;
                }

                return true;
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

            private boolean checkDuplicateContacts(Contact newContact) {
                ArrayList<Contact> contactList = FileFunctions.emptyFileInList(contactsFile);
                boolean sameName = false;
                for (Contact existingContact : contactList) {
                    sameName = existingContact.getFirstName().equalsIgnoreCase(newContact.getFirstName())
                            && existingContact.getLastName().equalsIgnoreCase(newContact.getLastName());

                    if (sameName) {
                        for (PhoneNumber newPN : newContact.getPhoneNumbers()) {
                            for (PhoneNumber existingPN : existingContact.getPhoneNumbers()) {
                                if (newPN.compareTo(existingPN) == 0) {
                                    ErrorFunctions.showErrorDialogMessage("Duplicate contact: Same name and phone number!", "Error Message");
                                    return true;
                                }
                            }
                        }
                    }
                }
                if (sameName) {
                    ErrorFunctions.showErrorDialogMessage("Duplicate contact: Same name", "Error Message");
                    return true;
                }
                return false;
            }

            private boolean addContactToDB(Contact newContact) {
                ArrayList<Contact> tempContactsList = FileFunctions.emptyFileInList(contactsFile);
                tempContactsList.add(newContact);
                getSelectedGroups();
                return FileFunctions.saveToFile(tempContactsList, contactsFile);
            }

            private void getSelectedGroups() {
                ArrayList<JCheckBox> checkBoxList = addContactView.getCheckBoxes();
                for (JCheckBox checkBox : checkBoxList) {
                    if (checkBox.isSelected()) {
                        groups.get(checkBoxList.indexOf(checkBox)).addContact(newContact);
                    }
                }

                FileFunctions.saveToFileGroup(groups, groupsFile);
            }
        }
        );
    }

    private void renderGroups() {
        groups.clear();

        try {
            if (!groupsFile.exists() || groupsFile.length() == 0) {
                groups.add(new Group("No groups", ""));
            } else {
                FileInputStream fis = new FileInputStream(groupsFile);
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
                if (groups.isEmpty()) {
                    groups.add(new Group("No groups", ""));
                }
            }
            addContactView.renderGroups(groups);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while loading groups!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "An error occurred while loading groups!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
        }
    }

}
