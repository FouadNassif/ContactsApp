package Controller;

import Model.Group;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import view.ContactDetailsView;
import view.ShareContactView;

public class ContactDetailsController {

    private ContactDetailsView contactDetailsView;

    public ContactDetailsController(ContactDetailsView view) {
        this.contactDetailsView = view;
        renderGroups();

        contactDetailsView.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contactDetailsView.dispose();
            }
        });

        contactDetailsView.getShareButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ShareContactController(new ShareContactView(contactDetailsView.getContact()));
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
