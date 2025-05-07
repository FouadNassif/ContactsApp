package view;

import Components.Title;
import Modal.Contact;
import Modal.PhoneNumber;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ContactDetailsView extends JFrame {

    private JLabel groupsLabel;
    String[] inputsLabel = {"First Name", "Last Name", "City"};
    private JPanel fieldsPanel, tablePanel, buttonsPanel, allPanel, checkBoxPanel, mainPanel;
    private String[] groups = {"No Groups", "Family", "Friends", "Co-Workers"};
    private JTable table;
    private String[] headers = {"Region Code", "Phone number"};
    private DefaultTableModel model;
    private JButton backButton;

    public ContactDetailsView(Contact contact) {

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
        buttonsPanel = new JPanel();
        buttonsPanel.add(backButton);

        allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
        allPanel.add(fieldsPanel);
        allPanel.add(tablePanel);
        allPanel.add(scrollCheckBoxPanel);
        allPanel.add(buttonsPanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Title.createTitle());
        mainPanel.add(allPanel);
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 20));
        add(mainPanel);
        setTitle("Contact View");
        setSize(500, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public JButton getBackButton() {
        return backButton;
    }
}
