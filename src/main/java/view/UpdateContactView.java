package view;

import Modal.Contact;
import Modal.Group;
import java.awt.Dimension;
import java.awt.Font;
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

    private JLabel title, fnLabel, lnLabel, cityLabel, title2, groupsLabel;
    private JTextField fnField, lnField, cityField;
    private JPanel titlePanel, fnPanel, lnPanel, cityPanel, fieldsPanel, tablePanel, buttonsPanel, allPanel, checkBoxPanel, mainPanel;
    private String[] groups = {"No Groups", "Family", "Friends", "Co-Workers"};
    private JTable table;
    private String[] headers = {"Region Code", "Phone number"};
    private DefaultTableModel model;
    private ArrayList<JCheckBox> checkBoxList = new ArrayList<>();
    private JButton saveButton, cancelButton;

    public UpdateContactView(Contact contact) {
        title = new JLabel("Contact Management");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        titlePanel = new JPanel();
        titlePanel.add(title);

        title2 = new JLabel("New Contact");

        fnLabel = new JLabel("First name");
        fnField = new JTextField(20);
        fnField.setText(contact.getFirstName());
        fnPanel = new JPanel();
        fnPanel.add(fnLabel);
        fnPanel.add(fnField);

        lnLabel = new JLabel("Last name");
        lnField = new JTextField(20);
        lnField.setText(contact.getLastName());
        lnPanel = new JPanel();
        lnPanel.add(lnLabel);
        lnPanel.add(lnField);

        cityLabel = new JLabel("City");
        cityField = new JTextField(20);
        cityField.setText(contact.getCity());
        cityPanel = new JPanel();
        cityPanel.add(cityLabel);
        cityPanel.add(cityField);

        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.add(fnPanel);
        fieldsPanel.add(lnPanel);
        fieldsPanel.add(cityPanel);

        model = new DefaultTableModel(headers, 0);
        int phoneCount = contact.getPhoneNumbers().size();
        for (int i = 0; i < phoneCount; i++) {
            model.addRow(new String[]{
                contact.getPhoneNumbers().get(i).getRegionCode(),
                contact.getPhoneNumbers().get(i).getPhoneNumber()
            });
        }
        for (int i = phoneCount; i < 6; i++) {
            model.addRow(new String[]{"", ""});
        }

        table = new JTable(model);
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
