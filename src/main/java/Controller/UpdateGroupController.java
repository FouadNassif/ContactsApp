package Controller;

import Components.CancelButton;
import Modal.Contact;
import Modal.Group;
import Observable.GroupObservable;
import UsefulFunctions.ErrorFunctions;
import UsefulFunctions.FileFunctions;
import UsefulFunctions.StylingFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.CONTACT_FILE;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.GROUP_FILE;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import view.UpdateGroupView;

public class UpdateGroupController {

    private UpdateGroupView updateGroupView;
    private ArrayList<Contact> currentGroupContacts;
    private ArrayList<Contact> renderContactsList = new ArrayList<>();
    private ArrayList<Contact> allContacts = FileFunctions.emptyFileInList(CONTACT_FILE);
    private ArrayList<Group> allGroup = FileFunctions.emptyFileInListGroup(GROUP_FILE);
    private int selectedGroupIndex;

    private GroupObservable observable;

    public UpdateGroupController(UpdateGroupView view, GroupObservable observable, Group group, int selectedGroupIndex) {
        this.selectedGroupIndex = selectedGroupIndex;
        this.updateGroupView = view;
        currentGroupContacts = new ArrayList<>(group.getContactList());
        this.observable = observable;
        updateGroupView.getNameField().setText(group.getName());
        updateGroupView.getDescField().setText(group.getDescription());

        updateGroupView.getCancelButton().addActionListener(new CancelButton(updateGroupView));
        renderAllContacts();
        updateGroupView.getSaveButton().addActionListener(new ActionListener() {
            JTextField[] fieldList = {updateGroupView.getNameField(), updateGroupView.getDescField()};
            Border defaultBorder = new JTextField().getBorder();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!StylingFunctions.checkField(fieldList, defaultBorder)) {
                    ErrorFunctions.showErrorDialogMessage("A Group requires a Name and Description!", "Error Message");
                    return;
                }

                group.clearAllContacts();
                for (Contact c : getCheckedContact()) {
                    group.addContact(c);
                }

                group.setName(updateGroupView.getNameField().getText().trim());
                group.setDescription(updateGroupView.getDescField().getText().trim());
                allGroup.set(selectedGroupIndex, group);
                if (FileFunctions.saveToFileGroup(allGroup, GROUP_FILE)) {
                    observable.groupAdded(group);
                    updateGroupView.dispose();
                }
            }

        });

    }

    public void renderAllContacts() {
        updateGroupView.getTableModel().setRowCount(0);
        for (Contact c : currentGroupContacts) {
            updateGroupView.getTableModel().addRow(new Object[]{c.getFullName(), c.getCity(), true});
            renderContactsList.add(c);
        }
        for (Contact c : allContacts) {
            boolean inGroup = false, skip = false;
            for (Contact z : currentGroupContacts) {
                if (c.compareTo(z) == 0) {
                    inGroup = true;
                    skip = true;
                    break;
                }
            }
            if (!skip) {
                updateGroupView.getTableModel().addRow(new Object[]{c.getFullName(), c.getCity(), inGroup});
                renderContactsList.add(c);
            }
        }
    }

    private ArrayList<Contact> getCheckedContact() {
        ArrayList<Contact> selectedContacts = new ArrayList<>();
        DefaultTableModel model = updateGroupView.getTableModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            Object value = model.getValueAt(row, 2);
            if (Boolean.TRUE.equals(value)) {
                selectedContacts.add(renderContactsList.get(row));
            }
        }
        return selectedContacts;
    }

}
