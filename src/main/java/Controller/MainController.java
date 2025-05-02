package Controller;

import java.awt.event.*;
import view.ContactView;
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
    }
}
