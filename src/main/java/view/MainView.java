package view;

import java.awt.*;
import javax.swing.*;

public class MainView extends JFrame {

    private final JLabel title;
    private final JButton contactsButton;
    private final JButton groupsButton;
    private final JPanel titlePanel, buttonsPanel, bluePanel, panel1, mainPanel;

    public MainView() {
        title = new JLabel("Contact Management");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        titlePanel = new JPanel();
        titlePanel.add(title);

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
        mainPanel.add(titlePanel);
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
}
