package Controller;

import Components.CancelButton;
import Modal.Contact;
import Modal.Group;
import Observable.GroupObservable;
import UsefulFunctions.ErrorFunctions;
import UsefulFunctions.FileFunctions;
import UsefulFunctions.StylingFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.*;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import view.AddGroupView;

public class AddGroupController {

    private AddGroupView addGroupView;
    private GroupObservable observable;
    private ArrayList<Contact> allContacts = FileFunctions.emptyFileInList(CONTACT_FILE);
    private ArrayList<Contact> checkedContacts = new ArrayList<>();
    private ArrayList<Contact> renderedContacts = new ArrayList<>();

    public AddGroupController(AddGroupView view, GroupObservable observable) {
        this.observable = observable;
        addGroupView = view;
        renderContacts();

        addGroupView.getSaveBtn().addActionListener(new ActionListener() {
            JTextField[] fieldList = {addGroupView.getNameField(), addGroupView.getDesField()};
            Border defaultBorder = new JTextField().getBorder();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!StylingFunctions.checkField(fieldList, defaultBorder)) {
                    ErrorFunctions.showErrorDialogMessage("A Group requires a Name and Description!", "Error Message");
                    return;
                }

                getCheckedBoxes();
                Group newGroup = new Group(fieldList[0].getText(), fieldList[1].getText());
                for (Contact contact : checkedContacts) {
                    newGroup.addContact(contact);
                }

                if (FileFunctions.saveToFileGroup(newGroup, GROUP_FILE)) {
                    observable.groupAdded(newGroup);
                    addGroupView.dispose();
                }
            }
        }
        );
        addGroupView.getSearchInput().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchContact();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchContact();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchContact();
            }

        });
        addGroupView.getTableModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getColumn() == 2) {
                    getCheckedBoxes();
                }
            }
        });
        addGroupView.getSelectAllCB().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeCheckBoxStatus(addGroupView.getSelectAllCB().isSelected());
            }
        });

        addGroupView.getCancelButton().addActionListener(new CancelButton(addGroupView));

    }

    private void renderContacts() {
        renderedContacts = new ArrayList<>(allContacts);
        renderContacts(renderedContacts);
    }

    private void renderContacts(ArrayList<Contact> contacts) {
        addGroupView.getTableModel().setRowCount(0);
        renderedContacts = new ArrayList<>(contacts);
        for (Contact contact : contacts) {
            boolean isChecked = checkedContacts.contains(contact);
            addGroupView.getTableModel().addRow(new Object[]{
                contact.getFirstName() + " " + contact.getLastName(),
                contact.getCity(),
                isChecked
            });
        }
    }

    private void searchContact() {
        String searchQuery = addGroupView.getSearchInput().getText().toLowerCase();
        ArrayList<Contact> filtered = new ArrayList<>();
        for (Contact c : allContacts) {
            String fullName = c.toString().replace(" - ", " ").toLowerCase();
            if (fullName.startsWith(searchQuery)) {
                filtered.add(c);
            }
        }
        renderContacts(filtered);
    }

    private void getCheckedBoxes() {
        DefaultTableModel model = addGroupView.getTableModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            Contact contact = renderedContacts.get(row);
            Object value = model.getValueAt(row, 2);

            if (Boolean.TRUE.equals(value)) {
                if (!checkedContacts.contains(contact)) {
                    checkedContacts.add(contact);
                }
            } else {
                checkedContacts.remove(contact);
            }
        }
    }

    private void changeCheckBoxStatus(boolean status) {
        for (int row = 0; row < addGroupView.getTableModel().getRowCount(); row++) {
            addGroupView.getTableModel().setValueAt(status, row, 2);
        }
        getCheckedBoxes();
    }

}
