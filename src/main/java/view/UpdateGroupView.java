package view;

import Components.Title;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class UpdateGroupView extends JFrame {

    private JLabel nameLabel, descLabel;
    private JTextField nameField, descField;

    private JTable table;
    private DefaultTableModel model;
    private String[] headers = {"Contact Name", "City", "Add to Group"};

    private JButton saveBtn, cancelBtn;
    private JPanel field1Panel, field2Panel, btnPanels, mainPanel;

    public UpdateGroupView() {
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

            @Override
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
        mainPanel.add(Title.createTitle("Update Group"));
        mainPanel.add(field1Panel);
        mainPanel.add(field2Panel);
        mainPanel.add(textAreaPanel);
        mainPanel.add(btnPanels);

        add(mainPanel);
        setTitle("Update Group");
        setSize(520, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public JButton getCancelButton() {
        return cancelBtn;
    }

    public JButton getSaveButton() {
        return saveBtn;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getDescField() {
        return descField;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return model;
    }
}
