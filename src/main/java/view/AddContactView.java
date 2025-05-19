package view;

import Modal.Group;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class AddContactView extends JFrame {

    private JLabel title, fnLabel, lnLabel, cityLabel, title2, groupsLabel;
    private JTextField fnField, lnField, cityField;
    private JPanel titlePanel, fnPanel, lnPanel, cityPanel, fieldsPanel, tablePanel, buttonsPanel, allPanel, checkBoxPanel, mainPanel;
    private JTable table;
    private String[] headers = {"Region Code", "Phone number"};
    private DefaultTableModel model;
    private ArrayList<JCheckBox> checkBoxList = new ArrayList<>();
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

        cityLabel = new JLabel("City");
        cityField = new JTextField(20);
        cityPanel = new JPanel();
        cityPanel.add(cityLabel);
        cityPanel.add(cityField);

        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.add(fnPanel);
        fieldsPanel.add(lnPanel);
        fieldsPanel.add(cityPanel);

        model = new DefaultTableModel(headers, 6);
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(350, 120));
        tablePanel.add(scrollPane);

        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollCheckBoxPanel = new JScrollPane(checkBoxPanel);
        scrollCheckBoxPanel.setBorder(null);
        groupsLabel = new JLabel("Add the contact to Groups:");
        groupsLabel.add(Box.createVerticalStrut(5));
        checkBoxPanel.add(groupsLabel);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonsPanel = new JPanel();
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
        allPanel.add(fieldsPanel);
        allPanel.add(tablePanel);
        allPanel.add(scrollCheckBoxPanel);
        allPanel.add(buttonsPanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.add(title2);
        mainPanel.add(allPanel);
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 20));
        add(mainPanel);
        setTitle("Contact View");
        setSize(500, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    // Text field
    public JTextField getFirstNameField() {
        return fnField;
    }

    public JTextField getLastNameField() {
        return lnField;
    }

    public JTextField getCityField() {
        return cityField;
    }

    public DefaultTableModel getTableModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }

    public ArrayList<JCheckBox> getCheckBoxes() {
        return checkBoxList;
    }

    public void renderGroups(ArrayList<Group> groups) {
        for (Group group : groups) {
            JCheckBox cb = new JCheckBox(group.getName());
            checkBoxList.add(cb);
            checkBoxPanel.add(cb);
        }
        checkBoxList.get(0).setSelected(true);
    }

}
