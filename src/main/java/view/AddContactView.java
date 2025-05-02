package view;

import Controller.AddContactController;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

public class AddContactView extends JFrame {

    private AddContactController addContactController;

    private JLabel title, fnLabel, lnLabel, cityLabel, title2, groupsLabel;
    private JTextField fnField, lnField, cityField;
    private JPanel titlePanel, fnPanel, lnPanel, cityPanel, fieldsPanel, tablePanel, buttonsPanel, allPanel, checkBoxPanel, mainPanel;
    private String[] groups = {"No Groups", "Family", "Friends", "Co-Workers"};
    private JTable table;
    private String[] headers = {"Region Code", "Phone number"};
    private DefaultTableModel model;

    private JButton saveButton, cancelButton;

    public AddContactView() {
        title = new JLabel("Contact Management");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        titlePanel = new JPanel();
        titlePanel.add(title);

        title2 = new JLabel("New Contact");

        fnLabel = new JLabel("First name");
        fnField = new JTextField(20);
        fnPanel = new JPanel();
        fnPanel.add(fnLabel);
        fnPanel.add(fnField);

        lnLabel = new JLabel("Last name");
        lnField = new JTextField(20);
        lnPanel = new JPanel();
        lnPanel.add(lnLabel);
        lnPanel.add(lnField);

        cityLabel = new JLabel("First name");
        cityField = new JTextField(20);
        cityPanel = new JPanel();
        cityPanel.add(cityLabel);
        cityPanel.add(cityField);

        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.add(fnPanel);
        fieldsPanel.add(lnPanel);
        fieldsPanel.add(cityPanel);

        model = new DefaultTableModel(headers, 7);
        table = new JTable(model);
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.add(new JScrollPane(table));

        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        groupsLabel = new JLabel("Add the contact to Groups:");
        checkBoxPanel.add(groupsLabel);
        for (String group : groups) {
            checkBoxPanel.add(new JCheckBox(group));
            checkBoxPanel.add(Box.createVerticalStrut(5));
        }

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonsPanel = new JPanel();
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
        allPanel.add(fieldsPanel);
        allPanel.add(tablePanel);
        allPanel.add(checkBoxPanel);
        allPanel.add(buttonsPanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.add(title2);
        mainPanel.add(allPanel);
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 20));
        add(mainPanel);
        setTitle("Contact View");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
