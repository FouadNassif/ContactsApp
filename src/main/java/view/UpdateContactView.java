package view;

import Components.Title;
import Model.Contact;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class UpdateContactView extends JFrame {

    private static UpdateContactView instance;
    private JLabel fnLabel, lnLabel, cityLabel, groupsLabel;
    private JTextField fnField, lnField, cityField;
    private JPanel fnPanel, lnPanel, cityPanel, fieldsPanel, tablePanel, buttonsPanel, allPanel, checkBoxPanel, mainPanel;
    private JTable table;
    private String[] headers = {"Region Code", "Phone number"};
    private DefaultTableModel model;
    private ArrayList<JCheckBox> checkBoxList = new ArrayList<>();
    private JButton saveButton, cancelButton;
    private Contact contact;

    public UpdateContactView(Contact contact) {
        this.contact = contact;

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

        model = new DefaultTableModel(headers, 0);

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
        groupsLabel = new JLabel("Contact Groups:");
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
        mainPanel.add(Title.createTitle("Update Contact"));
        mainPanel.add(allPanel);
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 20));
        add(mainPanel);
        setTitle("Update Contact");
        setSize(550, 530);
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

    public Contact getContact() {
        return contact;
    }

    public JPanel getCheckBoxPanel() {
        return checkBoxPanel;
    }

    public void setContact(Contact c) {
        contact = c;
    }

    public static UpdateContactView getInstance(Contact contact) {
        if (instance == null || !instance.isDisplayable()) {
            instance = new UpdateContactView(contact);
        } else {
            instance.setContact(contact);
        }
        return instance;
    }
}
