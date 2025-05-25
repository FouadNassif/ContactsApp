package Sorting;

import Model.Contact;
import java.util.Comparator;

public class SortByLastName implements Comparator {

    public int compare(Object o1, Object o2) {
        Contact contact1 = (Contact) o1;
        Contact contact2 = (Contact) o2;
        if (contact1.getLastName().compareToIgnoreCase(contact2.getLastName()) > 0) {
            return 1;
        } else {
            if (contact1.getLastName().compareToIgnoreCase(contact2.getLastName()) < 0) {
                return -1;
            }
        }
        return 0;
    }
}
