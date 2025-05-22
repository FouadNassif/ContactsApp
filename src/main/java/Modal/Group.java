package Modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Group implements Serializable {

    private String name;
    private String description;
    private ArrayList<Contact> contactList;

    public Group(String name, String description, ArrayList<Contact> list) {
        this.name = name;
        this.description = description;
        contactList = list;
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
        contactList = new ArrayList<>();
    }

    public Group() {
        contactList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    public void addContact(Contact c) {
        contactList.add(c);
    }

    public void clearAllContacts() {
        contactList.clear();
    }

    public void deleteContact(Contact c) {
        Iterator<Contact> iterator = contactList.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.compareTo(c) == 0) {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Group other = (Group) obj;
        return name != null && name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.toLowerCase().hashCode() : 0;
    }
}
