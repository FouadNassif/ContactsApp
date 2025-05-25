package Controller;

import Components.CancelButton;
import Model.Contact;
import Model.Group;
import Model.PhoneNumber;
import Observable.ContactObservable;
import UsefulFunctions.ErrorFunctions;
import UsefulFunctions.FileFunctions;
import UsefulFunctions.StylingFunctions;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.CONTACT_FILE;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.GROUP_FILE;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import view.UpdateContactView;

public class UpdateContactController {

    UpdateContactView updateContactView;
    private Contact contact;
    private ContactObservable observable;
    private int contactIndex;
    private File contacts_file = CONTACT_FILE;
    private ArrayList<Group> contactGroups;
    private ArrayList<Group> allGroups = FileFunctions.emptyFileInListGroup(GROUP_FILE);
    private ArrayList<Group> renderGroupList = new ArrayList<>(allGroups);
    ArrayList<Contact> allContacts = FileFunctions.emptyFileInList(contacts_file);

    public UpdateContactController(UpdateContactView view, ContactObservable observable, int contactIndex) {
        this.contactIndex = contactIndex;
        updateContactView = view;
        this.observable = observable;
        contact = updateContactView.getContact();
        contactGroups = contact.getGroups();

        updateContactView.getCancelButton().addActionListener(new CancelButton(updateContactView));
        renderContact();

        updateContactView.getSaveButton().addActionListener(new ActionListener() {
            JTextField[] fieldList = {updateContactView.getFirstNameField(), updateContactView.getLastNameField()};
            Border defaultBorder = new JTextField().getBorder();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!StylingFunctions.checkField(fieldList, defaultBorder)) {
                    ErrorFunctions.showErrorDialogMessage("A contact require a Name, Last Name and Phone Number!", "Error Message");
                    return;
                }

                DefaultTableModel model = updateContactView.getTableModel();
                if (!checkPhoneNumber(model)) {
                    return;
                }

                if (!contact.getPhoneNumbers().isEmpty()) {
                    contact.setFirstName(fieldList[0].getText());
                    contact.setLastName(fieldList[1].getText());
                    contact.setCity(updateContactView.getCityField().getText());
                    if (addContactToDB(contact)) {
                        observable.contactAdded(contact);
                        updateContactView.dispose();
                    }
                }
            }
        });
    }

    private void renderContact() {
        ArrayList<JCheckBox> checkBoxList = updateContactView.getCheckBoxes();
        updateContactView.getFirstNameField().setText(contact.getFirstName());
        updateContactView.getLastNameField().setText(contact.getLastName());
        updateContactView.getCityField().setText(contact.getCity());

        int phoneCount = contact.getPhoneNumbers().size();
        for (int i = 0; i < phoneCount; i++) {
            updateContactView.getTableModel().addRow(new String[]{
                contact.getPhoneNumbers().get(i).getRegionCode(),
                contact.getPhoneNumbers().get(i).getPhoneNumber()
            });
        }
        for (int i = phoneCount; i < 6; i++) {
            updateContactView.getTableModel().addRow(new String[]{"", ""});
        }
        renderGroups(checkBoxList);
    }

    public void renderGroups(ArrayList<JCheckBox> checkBoxList) {

        for (Group group : contactGroups) {
            JCheckBox cb = new JCheckBox(group.getName());
            cb.setSelected(true);
            if ("No groups".equals(group.getName())) {
                checkBoxList.add(0, cb);
            } else {
                checkBoxList.add(cb);
            }
        }
        renderGroupList.removeAll(contactGroups);
        for (Group group : renderGroupList) {
            JCheckBox cb = new JCheckBox(group.getName());
            if ("No groups".equals(group.getName())) {
                checkBoxList.add(0, cb);
            } else {
                checkBoxList.add(cb);
            }
        }

        for (JCheckBox cb : checkBoxList) {
            updateContactView.getCheckBoxPanel().add(cb);
        }

        for (int i = 0; i < checkBoxList.size(); i++) {
            final int index = i;
            JCheckBox cb = checkBoxList.get(i);
            cb.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (index == 0) {
                        if (cb.isSelected()) {
                            for (int j = 1; j < checkBoxList.size(); j++) {
                                checkBoxList.get(j).setSelected(false);
                            }
                        }
                    } else {
                        if (cb.isSelected()) {
                            checkBoxList.get(0).setSelected(false);
                        } else {
                            boolean anySelected = false;
                            for (int j = 1; j < checkBoxList.size(); j++) {
                                if (checkBoxList.get(j).isSelected()) {
                                    anySelected = true;
                                    break;
                                }
                            }
                            if (!anySelected) {
                                checkBoxList.get(0).setSelected(true);
                            }
                        }
                    }
                }
            });
        }
    }

    private boolean checkPhoneNumber(DefaultTableModel model) {
        boolean hasValidPhone = false;
        contact.clearAllPhoneNumbers();

        for (int row = 0; row < model.getRowCount(); row++) {
            Object regionCode = model.getValueAt(row, 0);
            Object phoneNumber = model.getValueAt(row, 1);

            boolean regionFilled = regionCode != null && !regionCode.toString().trim().isEmpty();
            boolean phoneFilled = phoneNumber != null && !phoneNumber.toString().trim().isEmpty();

            if (regionFilled && phoneFilled) {
                if (checkRegionPhone(regionCode.toString(), phoneNumber.toString())) {
                    hasValidPhone = true;
                    if (!contact.addPhoneNumber(new PhoneNumber(regionCode.toString(), phoneNumber.toString()))) {
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
            updateContactView.getTable().setBorder(BorderFactory.createLineBorder(Color.RED, 2));
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

    private boolean addContactToDB(Contact newContact) {
        allContacts.set(contactIndex, newContact);
        getSelectedGroups();
        return FileFunctions.saveToFile(allContacts, contacts_file);
    }

    private void getSelectedGroups() {
        ArrayList<JCheckBox> checkBoxList = updateContactView.getCheckBoxes();
        if (!checkBoxList.getFirst().isSelected()) {
            checkBoxList.getFirst().setSelected(true);
        }
        for (JCheckBox checkBox : checkBoxList) {
            for (Group group : allGroups) {
                if (group.getName().equals(checkBox.getText())) {
                    group.deleteContact(contact);
                    if (checkBox.isSelected()) {
                        group.addContact(contact);
                    }
                    break;
                }
            }
        }

        renderGroupList.clear();
        renderGroupList.addAll(renderGroupList);
        FileFunctions.saveToFileGroup(allGroups, GROUP_FILE);
    }

}
