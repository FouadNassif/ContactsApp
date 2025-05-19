package Controller;

import Modal.Contact;
import Modal.Group;
import java.util.ArrayList;
import view.UpdateGroupView;

public class UpdateGroupController {

    private UpdateGroupView updateGroupView;
    private ArrayList<Contact> contacts;

    public UpdateGroupController(UpdateGroupView updateGroupView, Group group) {
        this.updateGroupView = updateGroupView;
        contacts = new ArrayList<>(group.getContactList());

        updateGroupView.getNameField().setText(group.getName());
        updateGroupView.getDescField().setText(group.getDescription());

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
