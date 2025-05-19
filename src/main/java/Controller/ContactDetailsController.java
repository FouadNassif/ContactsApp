package Controller;

import Modal.Contact;
import Modal.Group;
import UsefulFunctions.FileFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import view.ContactDetailsView;

public class ContactDetailsController {

    private ContactDetailsView contactDetailsView;
    File groupsFile = new File("Groups.obj");

    public ContactDetailsController(ContactDetailsView view) {
        this.contactDetailsView = view;
        renderGroups();

        contactDetailsView.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contactDetailsView.dispose();
            }
        });

    }

    public void renderGroups() {
        System.out.println(contactDetailsView.getContact());
        ArrayList<Group> groups = FileFunctions.emptyFileInListGroup(groupsFile);
        for (Group group : groups) {
            for (Contact contact : group.getContactList()) {
                System.out.println(contact);
                if (contact.compareTo(contactDetailsView.getContact()) == 0) {
                    contactDetailsView.getCheckBoxPanel().add(new JLabel(group.getName()));
                    break;
                }
            }
        }
    }
}
