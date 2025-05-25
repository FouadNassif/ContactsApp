package Sorting;

import Model.Contact;
import java.util.ArrayList;
import java.util.Collections;

public class ContactSorter {

    public static ArrayList<Contact> sortByFirstName(ArrayList<Contact> contactsList) {
        ArrayList<Contact> sorted = new ArrayList<>(contactsList);
        Collections.sort(sorted, new SortByFirstName());
        return sorted;
    }

    public static ArrayList<Contact> sortByLastName(ArrayList<Contact> contactsList) {
        ArrayList<Contact> sorted = new ArrayList<>(contactsList);
        Collections.sort(sorted, new SortByLastName());
        return sorted;
    }

    public static ArrayList<Contact> sortByCity(ArrayList<Contact> contactsList) {
        ArrayList<Contact> sorted = new ArrayList<>(contactsList);
        Collections.sort(sorted, new SortByCity());
        return sorted;
    }

}
