package Controller;

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
import view.AddGroupView;
import view.GroupView;

public class GroupController implements Observer {

    private GroupView groupView;
    private ArrayList<Group> groups = new ArrayList<Group>();
    private GroupObservable observable = new GroupObservable();
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

    }

    private void renderGroups() {
        groups.clear();
        groupView.getTableModel().setRowCount(0);
        groups.addAll(FileFunctions.emptyFileInListGroup(f));
        for (Group group : groups) {
            groupView.getTableModel().addRow(new String[]{group.getName(), " " + group.getContactList().size()});
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
