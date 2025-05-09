package Controller;

import Modal.Contact;
import Observable.ContactObservable;
import Sorting.ContactSorter;
import UsefulFunctions.ErrorFunctions;
import UsefulFunctions.FileFunctions;
import UsefulFunctions.TableFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
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
    ArrayList<Contact> searchedContact = new ArrayList<Contact>();
    ArrayList<Contact> renderContactList = new ArrayList<Contact>();

    public ContactController(ContactView view) {
        this.contactView = view;
        observable.addObserver(this);
        renderContacts();
        contactView.getAddNewContactButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddContactController(new AddContactView(), observable);
            }
        });

        contactView.getSortByFirstNameButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderContactList.clear();
                ArrayList<Contact> sorted = ContactSorter.sortByFirstName(contactsList);
                renderContactList.addAll(sorted);
                TableFunctions.renderTableByList(sorted, contactView.getTableModel());
            }
        });

        contactView.getSortByLastNameButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderContactList.clear();
                ArrayList<Contact> sorted = ContactSorter.sortByLastName(contactsList);
                renderContactList.addAll(sorted);
                TableFunctions.renderTableByList(sorted, contactView.getTableModel());
            }
        });

        contactView.getSortByCityButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderContactList.clear();
                ArrayList<Contact> sorted = ContactSorter.sortByCity(contactsList);
                renderContactList.addAll(sorted);
                TableFunctions.renderTableByList(sorted, contactView.getTableModel());
            }
        });

        contactView.getSearchField()
                .getDocument().addDocumentListener(new DocumentListener() {
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

        contactView.getViewButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int rowIndex = contactView.getTable().getSelectedRow();
                if (rowIndex != -1) {
                    System.out.println(renderContactList.get(rowIndex).toString());
                    new ContactDetailsController(new ContactDetailsView(renderContactList.get(rowIndex)));
                } else {
                    ErrorFunctions.showErrorDialogMessage("Please select a contact to View!", "Error Message");
                }
            }
        }
        );

        contactView.getDeleteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedContact = TableFunctions.getSelectedRow(contactView.getTable(), "Please select a contact to delete!");
                if (selectedContact != null) {
                    JOptionPane optPane = new JOptionPane();
                    int reponse = optPane.showConfirmDialog(null, "Are you sure you want to delete?", "Comfirm Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (reponse == optPane.YES_OPTION) {
                        deleteContact();
                    } else {
                        optPane.setVisible(false);
                    }
                }
            }
        });
    }

    private void renderContacts() {
        contactView.getTableModel().setRowCount(0);
        contactsList.clear();
        renderContactList.clear();
        try {
            File f = new File("Contacts.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Contact contact = (Contact) ois.readObject();
                    contactsList.add(contact);
                    renderContactList.add(contact);
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
        searchedContact.clear();
        renderContactList.clear();
        for (Contact contact : contactsList) {
            if (contact.getFirstName().toLowerCase().startsWith(s.toLowerCase())) {
                searchedContact.add(contact);
            }
        }
        contactView.getTableModel().setRowCount(0);
        if (searchedContact.isEmpty()) {
            contactView.getTableModel().addRow(new String[]{"No Contact Found!"});
            return;
        }
        renderContactList.addAll(searchedContact);
        TableFunctions.renderTableByList(searchedContact, contactView.getTableModel());
    }

    private void deleteContact() {
        int selectedRow = contactView.getTable().getSelectedRow();
        if (selectedRow != -1) {
            contactsList.remove(renderContactList.get(selectedRow));
            ArrayList<Contact> tempList = new ArrayList<Contact>(contactsList);
            FileFunctions.saveToFile(tempList, new File("Contacts.obj"));
            renderContacts();
        }
    }

    @Override

    public void update(Observable o, Object arg) {
        renderContacts();
    }
}
