package view;

import Components.Title;
import Model.Group;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class AddContactView extends JFrame {

    private JLabel fnLabel, lnLabel, cityLabel, groupsLabel;
    private JTextField fnField, lnField, cityField;
    private JPanel fnPanel, lnPanel, cityPanel, fieldsPanel, tablePanel, buttonsPanel, allPanel, checkBoxPanel, mainPanel;
    private JTable table;
    private String[] headers = {"Region Code", "Phone number"};
    private DefaultTableModel model;
    private ArrayList<JCheckBox> checkBoxList = new ArrayList<>();
    private JButton saveButton, cancelButton;

    public AddContactView() {

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
        scrollPane.setPreferredSize(new Dimension(350, 150));
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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Title.createTitle("New Contact"));
        mainPanel.add(allPanel);
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 20));
        add(mainPanel);
        setTitle("Add Contact");
        setSize(550, 500);
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
        for (int i = 0; i < groups.size(); i++) {
            JCheckBox cb = new JCheckBox(groups.get(i).getName());
            checkBoxList.add(cb);
            checkBoxPanel.add(cb);
        }

        if (!checkBoxList.isEmpty()) {
            checkBoxList.get(0).setSelected(true);
        }

        for (int i = 0; i < checkBoxList.size(); i++) {
            final int index = i;
            JCheckBox cb = checkBoxList.get(i);
            cb.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (index == 0) {
                        if (cb.isSelected()) {
                            for (int j = 1; j < checkBoxList.size(); j++) {
                                checkBoxList.get(j).setSelected(false);
                            }
                        }
                    } else {
                        if (cb.isSelected()) {
                            checkBoxList.get(0).setSelected(false);
                        } else {
                            boolean anySelected = false;
                            for (int j = 1; j < checkBoxList.size(); j++) {
                                if (checkBoxList.get(j).isSelected()) {
                                    anySelected = true;
                                    break;
                                }
                            }
                            if (!anySelected) {
                                checkBoxList.get(0).setSelected(true);
                            }
                        }
                    }
                }
            });
        }
    }

}
