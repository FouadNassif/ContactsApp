package Modal;

import java.util.ArrayList;
import Exception.EmptyStringException;
import java3.nfa035_fouadnassif_2339t.UsefulFunctions;

public class Contact {

    private String firstName;
    private String lastName;
    private String ville;
    private ArrayList<PhoneNumber> phoneNumbers;

    public Contact(String fName, String lName, String v) {
        try {
            checkFields();
            firstName = fName;
            lastName = lName;
            ville = v;
        } catch (EmptyStringException ese) {
            System.out.println(ese.getMessage());
        }
    }

    @Override
    public String toString() {
        try {
            checkFields();
            return firstName + " " + lastName + " " + ville;
        } catch (EmptyStringException ese) {
            System.out.println(ese.getMessage());
        }
        return null;
    }

    private void checkFields() throws EmptyStringException {
        UsefulFunctions.checkString(firstName);
        UsefulFunctions.checkString(lastName);
        UsefulFunctions.checkString(ville);
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
