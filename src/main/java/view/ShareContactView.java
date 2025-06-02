package view;

import Model.Contact;
import UsefulFunctions.QrCode;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShareContactView extends JFrame {

    private static ShareContactView instance;
    private JLabel shareLabel;
    private JButton shareButton;
    Contact contact;

    public ShareContactView(Contact contact) {
        this.contact = contact;
        setTitle("Share Contact");

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));

        shareLabel = new JLabel("Share Contact");
        shareButton = new JButton("Share Contact!");

        JLabel qrLabel = QrCode.generateQrCode(contact);

        verticalPanel.add(Box.createVerticalStrut(20));
        verticalPanel.add(shareLabel);
        verticalPanel.add(Box.createVerticalStrut(20));
        if (qrLabel != null) {
            verticalPanel.add(qrLabel);
            verticalPanel.add(Box.createVerticalStrut(20));
        }
        verticalPanel.add(shareButton);
        verticalPanel.add(Box.createVerticalStrut(20));

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(verticalPanel);

        setLayout(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public JButton getShareButton() {
        return shareButton;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact c) {
        contact = c;
    }

    public static ShareContactView getInstance(Contact contact) {
        if (instance == null || !instance.isDisplayable()) {
            instance = new ShareContactView(contact);
        } else {
            instance.setContact(contact);
        }
        return instance;
    }
}
