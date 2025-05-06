package UsefulFunctions;

import Modal.Contact;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableFunctions {

    public static void renderTableByList(ArrayList<Contact> list, DefaultTableModel model) {
        model.setRowCount(0);
        for (Contact contact : list) {
            model.addRow(new String[]{contact.toString()});
        }
    }

    public static String getSelectedRow(JTable table, String errorMessage) {
        if (table.getSelectedRow() == -1) {
            ErrorFunctions.showErrorDialogMessage(errorMessage, "Error Message");
            return null;
        } else {
            return table.getValueAt(table.getSelectedRow(), 0).toString();
        }
    }

}
