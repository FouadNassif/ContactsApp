package java3.nfa035_fouadnassif_2339t;

import Controller.MainController;
import Model.Contact;
import Model.Group;
import Model.PhoneNumber;
import UsefulFunctions.FileFunctions;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import view.MainView;

public class NFA035_FouadNassif_2339t {

    private static final String[] FIRST_NAMES = {"John", "Alice", "David", "Emma", "Michael", "Sophia", "Daniel", "Olivia", "James", "Ava"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Brown", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin"};
    private static final String[] CITIES = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Seattle", "Miami", "Boston", "Denver", "Dallas"};
    private static final String[] REGION_CODES = {"06", "01", "71", "76", "03", "78", "81", "86", "02", "80"};
    private static final String[][] GROUPS = {
        {"No groups", ""},
        {"Family", "Close family members and relatives"},
        {"Friends", "Personal friends and close acquaintances"},
        {"Work", "Colleagues and professional connections"},
        {"VIP", "Very important or special guests"},
        {"School", "Schoolmates or classmates"},
        {"Gym", "People from the gym or fitness group"},
        {"Neighbors", "Neighbors in your area"},
        {"Travel Buddies", "People you travel with"},
        {"Volunteers", "Volunteers and helpers"}
    };
    private static final int RANDOM_CONTACTS_COUNT = 100;

    public static void main(String[] args) {
        new MainController(new MainView());
        checkAndCreateFiles();
        randomContact();
        randomGroups();
    }

    private static void checkAndCreateFiles() {
        try {
            if (!GlobalVariables.CONTACT_FILE.exists()) {
                GlobalVariables.CONTACT_FILE.createNewFile();
            }
            if (!GlobalVariables.GROUP_FILE.exists()) {
                GlobalVariables.GROUP_FILE.createNewFile();
                ArrayList<Group> temp = new ArrayList<>();
                temp.add(new Group("No groups", "NO DESCRIPTION"));
                FileFunctions.saveToFileGroup(temp, GlobalVariables.GROUP_FILE);
                temp.clear();
            }
        } catch (IOException e) {
        }
    }

    private static void randomContact() {
        ArrayList<Contact> temp = new ArrayList<>();
        for (int i = 0; i < RANDOM_CONTACTS_COUNT; i++) {
            Random random = new Random();
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            String city = CITIES[random.nextInt(CITIES.length)];
            Contact newContact = new Contact(firstName, lastName, city);
            for (int j = 1; j < random.nextInt(6) + 1; j++) {
                PhoneNumber pn = new PhoneNumber(REGION_CODES[random.nextInt(REGION_CODES.length)], random.nextInt(999999) + 100000 + "");
                newContact.addPhoneNumber(pn);
            }
            temp.add(newContact);
        }
        FileFunctions.saveToFile(temp, new File("Contacts.obj"));
    }

    private static void randomGroups() {
        ArrayList<Group> temp = new ArrayList<>();
        for (String[] group : GROUPS) {
            temp.add(new Group(group[0], group[1]));
        }
        FileFunctions.saveToFileGroup(temp, GlobalVariables.GROUP_FILE);
    }

}
