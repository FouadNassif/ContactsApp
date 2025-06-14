package Controller;

import Model.Contact;
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
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.CONTACT_FILE;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.AddContactView;
import view.ContactDetailsView;
import view.ContactView;
import view.UpdateContactView;

public class ContactController implements Observer {

    private ContactView contactView;
    private ContactObservable observable = new ContactObservable();
    private ArrayList<Contact> contactsList = new ArrayList<>();
    ArrayList<Contact> searchedContact = new ArrayList<Contact>();
    ArrayList<Contact> renderContactList = new ArrayList<Contact>();
    private int selectedRow = -1;
    private Contact selectedContact = new Contact();
    File contactsFile = CONTACT_FILE;

    public ContactController(ContactView view) {
        this.contactView = view;
        observable.addObserver(this);
        renderContacts();
        contactView.getAddNewContactButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddContactView view = AddContactView.getInstance();
                new AddContactController(view, observable);
                view.setVisible(true);
                view.toFront();
            }
        });

        contactView.getUpdateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1 && !contactView.getTableModel().getValueAt(selectedRow, 0).equals("No Contact Found!")) {
                    UpdateContactView view = UpdateContactView.getInstance(selectedContact);
                    new UpdateContactController(view, observable, selectedRow);
                    view.setVisible(true);
                    view.toFront();

                } else {
                    ErrorFunctions.showErrorDialogMessage("Please Select A Contact!", "Error Message");
                }
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
                if (selectedRow != -1 && !contactView.getTableModel().getValueAt(selectedRow, 0).equals("No Contact Found!")) {
                    ContactDetailsView view = ContactDetailsView.getInstance(selectedContact);
                    new ContactDetailsController(view);
                    view.setVisible(true);
                    view.toFront();
                } else {
                    ErrorFunctions.showErrorDialogMessage("Please Select A Contact!", "Error Message");
                }
            }
        }
        );

        contactView.getDeleteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1 && !contactView.getTableModel().getValueAt(selectedRow, 0).equals("No Contact Found!")) {
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
                } else {
                    ErrorFunctions.showErrorDialogMessage("Please Select A Contact!", "Error Message");
                }
            }
        });

        contactView.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRow = contactView.getTable().getSelectedRow();
                    if (selectedRow >= 0 && selectedRow < renderContactList.size()) {
                        selectedContact = renderContactList.get(selectedRow);
                    } else {
                        selectedContact = null;
                    }
                }
            }
        });

    }

    private void renderContacts() {
        contactView.getTableModel().setRowCount(0);
        contactsList.clear();
        renderContactList.clear();
        selectedContact = null;
        try {
            FileInputStream fis = new FileInputStream(contactsFile);
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
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void searchContact(String s) {
        searchedContact.clear();
        renderContactList.clear();
        for (Contact contact : contactsList) {
            String fullName = contact.toString().replace(" - ", " ");
            if (fullName.toLowerCase().startsWith(s.toLowerCase())) {
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
            ArrayList<Contact> tempList = new ArrayList<>(contactsList);
            FileFunctions.saveToFile(tempList, contactsFile);
            renderContacts();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        renderContacts();
    }

}
