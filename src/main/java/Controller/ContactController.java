package Controller;

import Modal.Contact;
import Observable.ContactObservable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Observable;
import java.util.Observer;
import view.AddContactView;
import view.ContactView;

public class ContactController implements Observer {

    private ContactView contactView;
    private ContactObservable observable = new ContactObservable();

    public ContactController(ContactView view) {
        this.contactView = view;
        observable.addObserver(this);
        renderContacts();
        contactView.getAddNewContactButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddContactController(new AddContactView(), observable);
            }
        });

    }

    public void renderContacts() {
        try {
            File f = new File("Contacts.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            contactView.getTableModel().setRowCount(0);

            while (true) {
                try {
                    Contact contact = (Contact) ois.readObject();
                    contactView.getTableModel().addRow(new String[]{contact.toString()});
                } catch (EOFException e) {
                    break;
                }
            }

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        renderContacts();
    }

}
