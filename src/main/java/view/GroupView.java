package view;

import Components.Title;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GroupView extends JFrame {

    private JButton addGroupBtn, updateButton, deleteButton;

    private JLabel title, title2;
    private JPanel titlePanel, inputPanel, filterPanel, searchPanel, managePanel, mainPanel;
    private JTable table;
    private String[] headers = {"Group Name", "Nb. of Contacts"};
    private DefaultTableModel model;

    public GroupView() {
        title = new JLabel("Contact Management");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        titlePanel = new JPanel();
        titlePanel.add(title);

        title2 = new JLabel("Groups");
        addGroupBtn = new JButton("Add New Group");
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        int ITEMS_GAP = 20;
        filterPanel.add(title2);
        filterPanel.add(Box.createVerticalStrut(ITEMS_GAP));
        filterPanel.add(addGroupBtn);

        model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setRowHeight(20);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));
        textAreaPanel.add(new JScrollPane(table));

        updateButton = new JButton("Update Group");
        deleteButton = new JButton("Delete");
        managePanel = new JPanel();
        managePanel.add(updateButton);
        managePanel.add(deleteButton);

        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.add(textAreaPanel);
        searchPanel.add(Box.createVerticalStrut(30));
        searchPanel.add(managePanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.add(filterPanel);
        mainPanel.add(Box.createHorizontalStrut(ITEMS_GAP));
        mainPanel.add(searchPanel);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new BorderLayout());
        tempPanel.add(Title.createTitle(), BorderLayout.NORTH);
        tempPanel.add(mainPanel, BorderLayout.CENTER);

        add(tempPanel);

        setTitle("Contact View");
        setSize(520, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public JButton getAddNewGroupButton() {
        return addGroupBtn;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return model;
    }
}
