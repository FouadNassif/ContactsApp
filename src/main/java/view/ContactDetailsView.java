package view;

import Components.Title;
import javax.swing.JFrame;

public class ContactDetailsView extends JFrame {

    public ContactDetailsView() {
        add(Title.createTitle());
        setTitle("Contact View");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
