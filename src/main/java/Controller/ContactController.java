package Controller;

import Modal.Contact;
import Observable.ContactObservable;
import Sorting.ContactSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java3.nfa035_fouadnassif_2339t.UsefulFunctions;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.AddContactView;
import view.ContactDetailsView;
import view.ContactView;

public class ContactController implements Observer {

    private ContactView contactView;
    private ContactObservable observable = new ContactObservable();
    private ArrayList<Contact> contactsList = new ArrayList<>();

    public ContactController(ContactView view) {
        this.contactView = view;
        observable.addObserver(this);
        renderContacts();
        contactView.getAddNewContactButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddContactController(new AddContactView(), observable);
            }
        });

        contactView.getViewButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ContactDetailsController(new ContactDetailsView());
            }
        });

        contactView.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
            JTextField searchField = contactView.getSearchField();

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchContact(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchContact(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchContact(searchField.getText());
            }

        });

        contactView.getSortByFirstNameButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Contact> sorted = ContactSorter.sortByFirstName(contactsList);
                UsefulFunctions.renderTableByList(sorted, contactView.getTableModel());
            }
        });

        contactView.getSortByLastNameButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Contact> sorted = ContactSorter.sortByLastName(contactsList);
                UsefulFunctions.renderTableByList(sorted, contactView.getTableModel());
            }
        });

        contactView.getSortByCityButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Contact> sorted = ContactSorter.sortByCity(contactsList);
                UsefulFunctions.renderTableByList(sorted, contactView.getTableModel());
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
                    contactsList.add(contact);
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

    private void searchContact(String s) {
        ArrayList<Contact> searchedContact = new ArrayList<Contact>();
        for (Contact contact : contactsList) {
            if (contact.getFirstName().toLowerCase().startsWith(s.toLowerCase())) {
                searchedContact.add(contact);
            }
        }
        contactView.getTableModel().setRowCount(0);
        if (searchedContact.isEmpty()) {
            contactView.getTableModel().addRow(new String[]{"We couldn't fin any contacts that match your search!"});
        }
        UsefulFunctions.renderTableByList(searchedContact, contactView.getTableModel());
    }

    @Override
    public void update(Observable o, Object arg) {
        renderContacts();
    }

}
