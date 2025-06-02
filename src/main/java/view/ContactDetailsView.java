package view;

import Components.Title;
import Model.Contact;
import Model.PhoneNumber;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ContactDetailsView extends JFrame {

    private static ContactDetailsView instance;

    private JLabel groupsLabel;
    private JPanel fieldsPanel, tablePanel, buttonsPanel, allPanel, checkBoxPanel, mainPanel;
    private JTable table;
    private String[] headers = {"Region Code", "Phone number"};
    private DefaultTableModel model;
    private JButton backButton, shareButton;
    private Contact currentContact;

    public ContactDetailsView(Contact contact) {
        currentContact = contact;
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        JTextField firstNameField = new JTextField(20);
        firstNameField.setText(contact.getFirstName());
        firstNameField.setEditable(false);
        JPanel firstNamePanel = new JPanel();
        firstNamePanel.add(new JLabel("First Name"));
        firstNamePanel.add(firstNameField);
        fieldsPanel.add(firstNamePanel);

        JTextField lastNameField = new JTextField(20);
        lastNameField.setText(contact.getLastName());
        lastNameField.setEditable(false);
        JPanel lastNamePanel = new JPanel();
        lastNamePanel.add(new JLabel("Last Name"));
        lastNamePanel.add(lastNameField);
        fieldsPanel.add(lastNamePanel);

        JTextField cityField = new JTextField(20);
        cityField.setText(contact.getCity());
        cityField.setEditable(false);
        JPanel cityPanel = new JPanel();
        cityPanel.add(new JLabel("City"));
        cityPanel.add(cityField);
        fieldsPanel.add(cityPanel);

        model = new DefaultTableModel(headers, 0);
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        for (PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
            model.addRow(new String[]{phoneNumber.getRegionCode(), phoneNumber.getPhoneNumber()});
        }
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(350, 120));
        tablePanel.add(scrollPane);

        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollCheckBoxPanel = new JScrollPane(checkBoxPanel);
        scrollCheckBoxPanel.setBorder(null);
        groupsLabel = new JLabel("Contact Groups:");
        groupsLabel.add(Box.createVerticalStrut(5));

        checkBoxPanel.add(groupsLabel);

        backButton = new JButton("Back");
        shareButton = new JButton("Share");
        buttonsPanel = new JPanel();
        buttonsPanel.add(backButton);
        buttonsPanel.add(shareButton);

        allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
        allPanel.add(fieldsPanel);
        allPanel.add(tablePanel);
        allPanel.add(scrollCheckBoxPanel);
        allPanel.add(buttonsPanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Title.createTitle("Contact Details"));
        mainPanel.add(allPanel);
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 20));
        add(mainPanel);
        setTitle("Contact Details");
        setSize(500, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JPanel getCheckBoxPanel() {
        return checkBoxPanel;
    }

    public Contact getContact() {
        return currentContact;
    }

    public JButton getShareButton() {
        return shareButton;
    }

    public void setContact(Contact c) {
        currentContact = c;
    }

    public static ContactDetailsView getInstance(Contact contact) {
        if (instance == null || !instance.isDisplayable()) {
            instance = new ContactDetailsView(contact);
        } else {
            instance.setContact(contact);
        }
        return instance;
    }

}
