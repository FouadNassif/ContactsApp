package view;

import Components.Title;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.*;

public class AddGroupView extends JFrame {

    private static AddGroupView instance;
    private JLabel nameLabel, descLabel;
    private JTextField nameField, descField, searchInput;

    private JTable table;
    private DefaultTableModel model;
    private String[] headers = {"Contact Name", "City", "Add to Group"};
    private JButton saveBtn, cancelBtn;
    JCheckBox selectAll;
    private JPanel field1Panel, field2Panel, btnPanels, usefuelButtonsPanel, mainPanel;

    public AddGroupView() {
        nameLabel = new JLabel("Group Name");
        nameField = new JTextField(20);
        field1Panel = new JPanel();
        field1Panel.add(nameLabel);
        field1Panel.add(nameField);

        descLabel = new JLabel("Description");
        descField = new JTextField(20);
        field2Panel = new JPanel();
        field2Panel.add(descLabel);
        field2Panel.add(descField);

        model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }

            public Class getColumnClass(int columnIndex) {
                return columnIndex == 2 ? Boolean.class : String.class;
            }
        };

        table = new JTable();
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setRowHeight(20);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        usefuelButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchInput = new JTextField(15);
        usefuelButtonsPanel.add(searchInput);
        selectAll = new JCheckBox("Select All");
        usefuelButtonsPanel.add(selectAll);

        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));
        textAreaPanel.add(new JScrollPane(table));

        saveBtn = new JButton("Save Group");
        cancelBtn = new JButton("Cancel");
        btnPanels = new JPanel();
        btnPanels.add(saveBtn);
        btnPanels.add(cancelBtn);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Title.createTitle("New Group"));
        mainPanel.add(field1Panel);
        mainPanel.add(field2Panel);
        mainPanel.add(usefuelButtonsPanel);
        mainPanel.add(textAreaPanel);
        mainPanel.add(btnPanels);

        add(mainPanel);
        setTitle("Add Group");
        setSize(520, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return model;
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getDesField() {
        return descField;
    }

    public JButton getCancelButton() {
        return cancelBtn;
    }

    public JTextField getSearchInput() {
        return searchInput;
    }

    public JCheckBox getSelectAllCB() {
        return selectAll;
    }

    public static AddGroupView getInstance() {
        if (instance == null || !instance.isDisplayable()) {
            instance = new AddGroupView();
        }
        return instance;
    }
}
