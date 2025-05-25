package Sorting;

import Model.Contact;
import java.util.Comparator;

public class SortByFirstName implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Contact contact1 = (Contact) o1;
        Contact contact2 = (Contact) o2;
        if (contact1.getFirstName().compareToIgnoreCase(contact2.getFirstName()) > 0) {
            return 1;
        } else {
            if (contact1.getFirstName().compareToIgnoreCase(contact2.getFirstName()) < 0) {
                return -1;
            }
        }
        return 0;
    }

}
