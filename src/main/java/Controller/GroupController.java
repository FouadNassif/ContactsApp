package Controller;

import Modal.Contact;
import Modal.Group;
import Observable.GroupObservable;
import UsefulFunctions.ErrorFunctions;
import UsefulFunctions.FileFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.AddGroupView;
import view.GroupView;
import view.UpdateGroupView;

public class GroupController implements Observer {

    private GroupView groupView;
    private ArrayList<Group> groups = new ArrayList<Group>();
    private GroupObservable observable = new GroupObservable();
    int selectedRow = -1;
    File f = new File("Groups.obj");

    public GroupController(GroupView view) {
        observable.addObserver(this);
        groupView = view;
        renderGroups();
        groupView.getAddNewGroupButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddGroupController(new AddGroupView(), observable);
            }
        });
        groupView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane optPane = new JOptionPane();
                int reponse = optPane.showConfirmDialog(null, "Are you sure you want to delete?", "Comfirm Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (reponse == optPane.YES_OPTION) {
                    deleteGroup();
                } else {
                    optPane.setVisible(false);
                }
            }
        });

        groupView.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRow = groupView.getTable().getSelectedRow();
                    if (selectedRow >= 0) {
                        renderContacts(groups.get(selectedRow));
                    }
                }
            }
        });

        groupView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateGroupController(new UpdateGroupView(), groups.get(selectedRow));
            }

        });

    }

    private void renderGroups() {
        groups.clear();
        groupView.getTableModel().setRowCount(0);
        ArrayList<Group> temp = FileFunctions.emptyFileInListGroup(f);
        if (!temp.isEmpty()) {
            groups.addAll(temp);
            for (Group group : groups) {
                groupView.getTableModel().addRow(new String[]{group.getName(), " " + group.getContactList().size()});
            }
        }
    }

    private void renderContacts(Group g) {
        System.out.println(g.getContactList());
        groupView.getContactTableModel().setRowCount(0);
        for (Contact contact : g.getContactList()) {
            System.out.println(contact);
            groupView.getContactTableModel().addRow(new String[]{contact.getFirstName() + " " + contact.getLastName(), contact.getCity()});
        }
    }

    private void deleteGroup() {
        int selectedRow = groupView.getTable().getSelectedRow();
        if (selectedRow != -1) {
            groups.remove(groups.get(selectedRow));
            ArrayList<Group> tempList = new ArrayList<Group>(groups);
            FileFunctions.saveToFileGroup(tempList, f);
            renderGroups();
        } else {
            ErrorFunctions.showErrorDialogMessage("Please Select a group to Delete", "Error Message");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        renderGroups();
    }
}
