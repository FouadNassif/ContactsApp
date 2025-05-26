package view;

import Components.Title;
import java.awt.*;
import javax.swing.*;

public class MainView extends JFrame {

    private final JButton contactsButton, groupsButton, ImpExpButton;
    private final JPanel buttonsPanel, bluePanel, panel1, mainPanel;

    public MainView() {

        contactsButton = new JButton("Contacts");

        groupsButton = new JButton("Groups");

        ImpExpButton = new JButton("Import / Export");
        buttonsPanel = new JPanel(new GridLayout(0, 1));

        buttonsPanel.add(contactsButton);
        buttonsPanel.add(groupsButton);
        buttonsPanel.add(ImpExpButton);

        bluePanel = new JPanel();
        bluePanel.setPreferredSize(new Dimension(350, 300));
        bluePanel.setBackground(Color.cyan);

        panel1 = new JPanel();
        panel1.add(buttonsPanel);
        panel1.add(bluePanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(Title.createTitle("Contacts Management"));
        mainPanel.add(panel1);

        setSize(500, 400);
        add(mainPanel);
        setVisible(true);
        setTitle("Project NFA035");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public JButton getContactButton() {
        return contactsButton;
    }

    public JButton getGroupButton() {
        return groupsButton;
    }

    public JButton getImpExpButton() {
        return ImpExpButton;
    }

}
