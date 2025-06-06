package view;

import java.awt.*;
import javax.swing.*;

public class FileUploadView extends JFrame {

    private static FileUploadView instance;
    private JButton importBtn, exportBtn;

    public FileUploadView() {
        importBtn = new JButton("Import");
        exportBtn = new JButton("Export");

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(importBtn);
        panel.add(exportBtn);
        add(panel);
        setTitle("Import / Export Contacts & Groups");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    public JButton getImportBtn() {
        return importBtn;
    }

    public JButton getExportBtn() {
        return exportBtn;
    }

    public static FileUploadView getInstance() {
        if (instance == null || !instance.isDisplayable()) {
            instance = new FileUploadView();
        }
        return instance;
    }
}
