package Modal;

import java.io.Serializable;
import java.util.ArrayList;

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

}
