package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AddContactView;
import view.ContactView;

public class ContactController {

    private ContactView contactView;

    public ContactController(ContactView view) {
        this.contactView = view;
        contactView.getAddNewContactButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddContactController(new AddContactView());
            }
        });
    }

}
