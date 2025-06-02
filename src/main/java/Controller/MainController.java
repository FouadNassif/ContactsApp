package Controller;

import java.awt.event.*;
import view.ContactView;
import view.FileUploadView;
import view.GroupView;
import view.MainView;

public class MainController {

    private MainView mainView;

    public MainController(MainView view) {
        this.mainView = view;

        mainView.getContactButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ContactView view = ContactView.getInstance();
                new ContactController(view);
                view.setVisible(true);
                view.toFront();
            }
        });

        mainView.getContactButton().addActionListener(e -> {

        });

        mainView.getGroupButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GroupView view = GroupView.getInstance();
                new GroupController(view);
                view.setVisible(true);
                view.toFront();
            }
        });
        mainView.getImpExpButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileUploadView view = FileUploadView.getInstance();
                new FileUploadController(view);
                view.setVisible(true);
                view.toFront();
            }
        });

    }
}
