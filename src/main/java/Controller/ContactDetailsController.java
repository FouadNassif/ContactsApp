package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ContactDetailsView;

public class ContactDetailsController {

    private ContactDetailsView contactDetailsView;

    public ContactDetailsController(ContactDetailsView view) {
        this.contactDetailsView = view;

        contactDetailsView.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contactDetailsView.dispose();
            }
        });
    }
}
