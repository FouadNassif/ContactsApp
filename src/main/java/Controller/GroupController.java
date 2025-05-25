package Controller;

import Model.Contact;
import Model.Group;
import Observable.GroupObservable;
import UsefulFunctions.ErrorFunctions;
import UsefulFunctions.FileFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.GROUP_FILE;
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
    File groups_file = GROUP_FILE;

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
                if (selectedRow != - 1) {
                    if ("No groups".equals((String) groupView.getTable().getValueAt(selectedRow, 0))) {
                        ErrorFunctions.showErrorDialogMessage("Can't Delete This Group!", "Error Message");
                    } else {
                        JOptionPane optPane = new JOptionPane();
                        int reponse = optPane.showConfirmDialog(null, "Are you sure you want to delete?", "Comfirm Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (reponse == optPane.YES_OPTION) {
                            deleteGroup();
                        } else {
                            optPane.setVisible(false);
                        }
                    }
                } else {
                    ErrorFunctions.showErrorDialogMessage("Please Select a group to Delete", "Error Message");
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
                if (selectedRow != - 1) {
                    new UpdateGroupController(new UpdateGroupView(), observable, groups.get(selectedRow), selectedRow);
                } else {
                    ErrorFunctions.showErrorDialogMessage("Please Select a group to Delete", "Error Message");
                }
            }

        });

    }

    private void renderGroups() {
        groups.clear();
        groupView.getTableModel().setRowCount(0);
        ArrayList<Group> temp = FileFunctions.emptyFileInListGroup(groups_file);
        if (!temp.isEmpty()) {
            groups.addAll(temp);
            for (Group group : groups) {
                groupView.getTableModel().addRow(new String[]{group.getName(), " " + group.getContactList().size()});
            }
        }
    }

    private void renderContacts(Group g) {
        groupView.getContactTableModel().setRowCount(0);
        for (Contact contact : g.getContactList()) {
            groupView.getContactTableModel().addRow(new String[]{contact.getFirstName() + " " + contact.getLastName(), contact.getCity()});
        }
    }

    private void deleteGroup() {
        groups.remove(groups.get(selectedRow));
        ArrayList<Group> tempList = new ArrayList<Group>(groups);
        FileFunctions.saveToFileGroup(tempList, groups_file);
        groupView.getContactTableModel().setRowCount(0);
        renderGroups();
    }

    @Override
    public void update(Observable o, Object arg) {
        renderGroups();
        groupView.getContactTableModel().setRowCount(0);
    }
}
