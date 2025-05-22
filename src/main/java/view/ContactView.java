package view;

import Components.Title;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

public class ContactView extends JFrame {

    private JButton sFNButton, sLNButton, sCityButton, addNewContactButton, viewButton, updateButton, deleteButton;

    private JLabel title, inputLabel, title2;
    private JTextField searchInput;
    private JPanel titlePanel, inputPanel, filterPanel, searchPanel, managePanel, mainPanel;
    private JTable table;
    private String[] headers = {""};
    private DefaultTableModel model;

    public ContactView() {
        title2 = new JLabel("Contacts");
        sFNButton = new JButton("Sort By First Name");
        sLNButton = new JButton("Sort By Last Name");
        sCityButton = new JButton("Sort By City");
        addNewContactButton = new JButton("Add new Contact");
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        int ITEMS_GAP = 20;
        filterPanel.add(title2);
        filterPanel.add(Box.createVerticalStrut(ITEMS_GAP));
        filterPanel.add(sFNButton);
        filterPanel.add(Box.createVerticalStrut(ITEMS_GAP));
        filterPanel.add(sLNButton);
        filterPanel.add(Box.createVerticalStrut(ITEMS_GAP));
        filterPanel.add(sCityButton);
        filterPanel.add(Box.createVerticalStrut(ITEMS_GAP));
        filterPanel.add(addNewContactButton);

        inputLabel = new JLabel("Search");
        searchInput = new JTextField(20);
        inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(searchInput);

        model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setRowHeight(20);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));
        textAreaPanel.add(new JScrollPane(table));

        viewButton = new JButton("View");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        managePanel = new JPanel();
        managePanel.add(viewButton);
        managePanel.add(updateButton);
        managePanel.add(deleteButton);

        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.add(inputPanel);
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
        tempPanel.add(Title.createTitle("Contact Management"), BorderLayout.NORTH);
        tempPanel.add(mainPanel, BorderLayout.CENTER);

        add(tempPanel);

        setTitle("Contacts");
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public JButton getAddNewContactButton() {
        return addNewContactButton;
    }

    public DefaultTableModel getTableModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getSortByFirstNameButton() {
        return sFNButton;
    }

    public JButton getSortByLastNameButton() {
        return sLNButton;
    }

    public JButton getSortByCityButton() {
        return sCityButton;
    }

    public JTextField getSearchField() {
        return searchInput;
    }

}
