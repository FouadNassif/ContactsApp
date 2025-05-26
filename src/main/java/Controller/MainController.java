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
                new ContactController(new ContactView());
            }
        });

        mainView.getGroupButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GroupController(new GroupView());
            }
        });
        mainView.getImpExpButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FileUploadController(new FileUploadView());
            }
        });

    }
}
