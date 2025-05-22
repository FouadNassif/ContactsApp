package Components;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class SelectAll {

    public static JCheckBox createSelectAllCheckBox(JTable table, DefaultTableModel model, int columnIndex, Runnable onChange) {
        JCheckBox cb = new JCheckBox("Select All");

        cb.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                boolean selected = cb.isSelected();
                changeCheckBoxStatus(selected, model, columnIndex);
            }
        });

        return cb;
    }

    private static void changeCheckBoxStatus(boolean status, DefaultTableModel model, int columnIndex) {
        for (int row = 0; row < model.getRowCount(); row++) {
            model.setValueAt(status, row, columnIndex);
        }
    }
}
