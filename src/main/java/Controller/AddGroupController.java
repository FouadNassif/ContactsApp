package Controller;

import Modal.Contact;
import Modal.Group;
import Observable.GroupObservable;
import UsefulFunctions.ErrorFunctions;
import UsefulFunctions.FileFunctions;
import UsefulFunctions.StylingFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import view.AddGroupView;

public class AddGroupController {

    private AddGroupView addGroupView;
    private GroupObservable observable;
    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    File f = new File("Contacts.obj");
    File groupFile = new File("Groups.obj");

    public AddGroupController(AddGroupView view, GroupObservable observable) {
        this.observable = observable;
        addGroupView = view;
        renderContacts();

        addGroupView.getSaveBtn().addActionListener(new ActionListener() {
            JTextField[] fieldList = {addGroupView.getNameField(), addGroupView.getDesField()};
            Border defaultBorder = new JTextField().getBorder();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!StylingFunctions.checkField(fieldList, defaultBorder)) {
                    ErrorFunctions.showErrorDialogMessage("A Group require a Name and Description!", "Error Message");
                    return;
                }
                ArrayList<Integer> selectedContacts = getCheckedBoxes();
                Group newGroup = new Group(fieldList[0].getText(), fieldList[1].getText());
                for (Integer i : selectedContacts) {
                    newGroup.addContact(contacts.get(i));
                }
                if (FileFunctions.saveToFileGroup(newGroup, groupFile)) {
                    observable.groupAdded(newGroup);
                    addGroupView.dispose();
                }
            }
        }
        );

    }

    private void renderContacts() {
        contacts.addAll(FileFunctions.emptyFileInList(f));
        for (Contact contact : contacts) {
            addGroupView.getTableModel().addRow(new Object[]{
                contact.getFirstName() + " " + contact.getLastName(),
                contact.getCity(),
                false
            });

        }
    }

    private ArrayList<Integer> getCheckedBoxes() {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        DefaultTableModel model = addGroupView.getTableModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            Object value = model.getValueAt(row, 2);
            if (Boolean.TRUE.equals(value)) {
                indexes.add(row);
            }
        }
        return indexes;
    }
}
