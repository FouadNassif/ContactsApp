package Controller;

import Components.CancelButton;
import Modal.Contact;
import Modal.Group;
import java.util.ArrayList;
import view.UpdateGroupView;

public class UpdateGroupController {

    private UpdateGroupView updateGroupView;
    private ArrayList<Contact> contacts;

    public UpdateGroupController(UpdateGroupView view, Group group) {
        this.updateGroupView = view;
        contacts = new ArrayList<>(group.getContactList());

        updateGroupView.getNameField().setText(group.getName());
        updateGroupView.getDescField().setText(group.getDescription());

        updateGroupView.getCancelButton().addActionListener(new CancelButton(updateGroupView));
    }

//    private void renderContacts() {
//        contacts.addAll(FileFunctions.emptyFileInList(f));
//        for (Contact contact : contacts) {
//            addGroupView.getTableModel().addRow(new Object[]{
//                contact.getFirstName() + " " + contact.getLastName(),
//                contact.getCity(),
//                false
//            });
//
//        }
//    }
}
