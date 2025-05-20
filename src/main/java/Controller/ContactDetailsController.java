package Controller;

import Modal.Group;
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
        ArrayList<Group> groups = contactDetailsView.getContact().getGroups();
        for (Group group : groups) {
            contactDetailsView.getCheckBoxPanel().add(new JLabel(group.getName()));
        }
    }

}
