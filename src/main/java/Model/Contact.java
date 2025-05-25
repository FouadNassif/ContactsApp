package Model;

import UsefulFunctions.FileFunctions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.GROUP_FILE;

public class Contact extends Observable implements Serializable, Comparable {

    private String firstName;
    private String lastName;
    private String city;
    private ArrayList<PhoneNumber> phoneNumbers;

    public Contact(String fName, String lName, String c) {
        firstName = fName.trim();
        lastName = lName.trim();
        city = c.trim();
        phoneNumbers = new ArrayList<>();
    }

    public Contact() {
        phoneNumbers = new ArrayList<>();
    }

    @Override
    public String toString() {
        String s = firstName + " " + lastName;
        if (city != null && !city.trim().isEmpty()) {
            s += " - " + city;
        }
        return s;
    }

    public void printPhoneNumbers() {
        for (PhoneNumber current : phoneNumbers) {
            System.out.println(current.toString());
        }
    }

    public boolean addPhoneNumber(PhoneNumber pn) {
        for (PhoneNumber current : phoneNumbers) {
            if (current.compareTo(pn) == 0) {
                return false;
            }
        }
        this.setChanged();
        this.notifyObservers();
        return phoneNumbers.add(pn);
    }

    public void clearAllPhoneNumbers() {
        phoneNumbers.clear();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String ville) {
        this.city = ville.trim();
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String getFullName() {
        return firstName + "  " + lastName;
    }

    @Override
    public int compareTo(Object o) {
        Contact c = (Contact) o;
        if (this.getFirstName().compareTo(c.getFirstName()) > 0 && this.getLastName().compareTo(c.getLastName()) > 0) {
            return 1;
        } else if (this.getFirstName().compareTo(c.getFirstName()) < 0 && this.getLastName().compareTo(c.getLastName()) < 0) {
            return -1;
        } else {
            if (this.phoneNumbers.size() == c.getPhoneNumbers().size()) {
                return checkNumbers(this.phoneNumbers, c.getPhoneNumbers());
            } else {
                return -1;
            }
        }
    }

    private int checkNumbers(ArrayList<PhoneNumber> List1, ArrayList<PhoneNumber> List2) {
        for (int i = 0; i < List1.size(); i++) {
            if (List1.get(i).compareTo(List2.get(i)) != 0) {
                return -1;
            }
        }
        return 0;
    }

    public ArrayList<Group> getGroups() {
        ArrayList<Group> groups = FileFunctions.emptyFileInListGroup(GROUP_FILE);
        ArrayList<Group> contactGroups = new ArrayList<>();
        for (Group group : groups) {
            for (Contact contact : group.getContactList()) {
                if (contact.compareTo(this) == 0) {
                    contactGroups.add(group);
                    break;
                }
            }
        }
        return contactGroups;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) obj;

        return this.firstName.equalsIgnoreCase(other.firstName)
                && this.lastName.equalsIgnoreCase(other.lastName)
                && this.phoneNumbers.equals(other.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase(), phoneNumbers);
    }

}
