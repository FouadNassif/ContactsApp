package view;

import Components.Title;
import java.awt.*;
import javax.swing.*;

public class MainView extends JFrame {

    private final JButton contactsButton;
    private final JButton groupsButton;
    private final JPanel buttonsPanel, bluePanel, panel1, mainPanel;

    public MainView() {

        contactsButton = new JButton("Contacts");

        groupsButton = new JButton("Groups");
        buttonsPanel = new JPanel(new GridLayout(0, 1));

        buttonsPanel.add(contactsButton);
        buttonsPanel.add(groupsButton);

        bluePanel = new JPanel();
        bluePanel.setPreferredSize(new Dimension(350, 300));
        bluePanel.setBackground(Color.cyan);

        panel1 = new JPanel();
        panel1.add(buttonsPanel);
        panel1.add(bluePanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(Title.createTitle());
        mainPanel.add(panel1);

        setSize(500, 400);
        add(mainPanel);
        setVisible(true);
        setTitle("Project NFA035");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton getContactButton() {
        return contactsButton;
    }

    public JButton getGroupButton() {
        return groupsButton;
    }
}
