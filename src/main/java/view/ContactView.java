package view;

import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

public class ContactView extends JFrame {

    private JButton sFNButton, sLNButton, sCityButton, addNewContactButton, viewButton, updateButton, deleteButton;

    private JLabel title, inputLabel, title2;
    private JTextField searchInput;
    private JPanel titlePanel, inputPanel, filterPanel, searchPanel, managePanel, mainPanel;
    private JTextArea contactListBox;
    private JTable table;
    private String[] headers = {""};
    private DefaultTableModel model;

    public ContactView() {
        title = new JLabel("Contact Management");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        titlePanel = new JPanel();
        titlePanel.add(title);

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

        model = new DefaultTableModel(headers, 0);
        table = new JTable(model);
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
        mainPanel.add(searchPanel);
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 20));
        add(mainPanel);

        setTitle("Contact View");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Only closes this window
        setVisible(true);
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

}
