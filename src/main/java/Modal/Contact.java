package Modal;

import Exception.EmptyStringException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java3.nfa035_fouadnassif_2339t.UsefulFunctions;

public class Contact extends Observable implements Serializable, Comparable {

    private String firstName;
    private String lastName;
    private String city;
    private ArrayList<PhoneNumber> phoneNumbers;

    public Contact(String fName, String lName, String c) {
        try {
            firstName = fName;
            lastName = lName;
            city = c;
            checkFields();
            System.out.println("WW");
            phoneNumbers = new ArrayList<>();
        } catch (EmptyStringException ese) {
            System.out.println(ese.getMessage());
        }
    }

    public Contact() {
        phoneNumbers = new ArrayList<>();
    }

    @Override
    public String toString() {
        try {
            checkFields();
            return firstName + " " + lastName + " " + city;
        } catch (EmptyStringException ese) {
            System.out.println(ese.getMessage());
        }
        return null;
    }

    public void printPhoneNumbers() {
        for (PhoneNumber current : phoneNumbers) {
            System.out.println(current.toString());
        }
    }

    private void checkFields() throws EmptyStringException {
        UsefulFunctions.checkString(firstName);
        UsefulFunctions.checkString(lastName);
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
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String ville) {
        this.city = ville;
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public int compareTo(Object o) {
        Contact c = (Contact) o;
        if (this.getFirstName().compareTo(c.getFirstName()) > 0 && this.getLastName().compareTo(c.getLastName()) > 0) {
            return 1;
        } else if (this.getFirstName().compareTo(c.getFirstName()) < 0 && this.getLastName().compareTo(c.getLastName()) < 0) {
            return -1;
        } else {
            if (this.phoneNumbers.size() > c.getPhoneNumbers().size()) {
                return checkNumbers(this.phoneNumbers, c.getPhoneNumbers());
            } else {
                return checkNumbers(c.getPhoneNumbers(), this.phoneNumbers);
            }
        }
    }

    private int checkNumbers(ArrayList<PhoneNumber> List1, ArrayList<PhoneNumber> List2) {
        for (PhoneNumber pn : List2) {
            for (PhoneNumber pn1 : List1) {
                if (pn.compareTo(pn1) == 0) {
                    return 0;
                }
            }
        }
        return -1;
    }
}
