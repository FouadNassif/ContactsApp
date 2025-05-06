package view;

import Components.Title;
import Modal.Contact;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ContactDetailsView extends JFrame {

    private JLabel fullNameLabel, cityLabel;
    private JButton backButton;
    private JPanel namePanel, cityPanel, mainPanel, numbersPanel;

    public ContactDetailsView(Contact contact) {
        // General styling for panels
        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        Font valueFont = new Font("SansSerif", Font.PLAIN, 14);
        Color backgroundColor = new Color(245, 248, 255);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(backgroundColor);

        int ITEMS_GAP = 15;

        // Title
        mainPanel.add(Title.createTitle());
        mainPanel.add(Box.createVerticalStrut(ITEMS_GAP));

        // Name panel
        namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setBackground(backgroundColor);
        fullNameLabel = new JLabel("Full Name: ");
        fullNameLabel.setFont(labelFont);
        JLabel fullNameValue = new JLabel(contact.getFirstName() + " " + contact.getLastName());
        fullNameValue.setFont(valueFont);
        namePanel.add(fullNameLabel);
        namePanel.add(fullNameValue);
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(ITEMS_GAP));

        // City panel
        cityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cityPanel.setBackground(backgroundColor);
        cityLabel = new JLabel("City: ");
        cityLabel.setFont(labelFont);
        JLabel cityValue = new JLabel(contact.getCity());
        cityValue.setFont(valueFont);
        cityPanel.add(cityLabel);
        cityPanel.add(cityValue);
        mainPanel.add(cityPanel);
        mainPanel.add(Box.createVerticalStrut(ITEMS_GAP));

        // Phone numbers panel
        numbersPanel = new JPanel();
        numbersPanel.setLayout(new BoxLayout(numbersPanel, BoxLayout.Y_AXIS));
        numbersPanel.setBackground(backgroundColor);
        numbersPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 255)),
                "Phone Numbers",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                labelFont
        ));

        for (int i = 0; i < contact.getPhoneNumbers().size(); i++) {
            String s = "Phone " + (i + 1) + ": "
                    + contact.getPhoneNumbers().get(i).getRegionCode() + " "
                    + contact.getPhoneNumbers().get(i).getPhoneNumber();
            JLabel phoneLabel = new JLabel(s);
            phoneLabel.setFont(valueFont);
            numbersPanel.add(phoneLabel);
            numbersPanel.add(Box.createVerticalStrut(5));
        }

        mainPanel.add(numbersPanel);
        mainPanel.add(Box.createVerticalStrut(ITEMS_GAP));

        // Back button
        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(ITEMS_GAP));
        mainPanel.add(backButton);

        add(mainPanel);

        setTitle("Contact Details");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public JButton getBackButton() {
        return backButton;
    }
}
