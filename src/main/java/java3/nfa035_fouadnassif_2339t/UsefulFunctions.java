package java3.nfa035_fouadnassif_2339t;

import Exception.EmptyStringException;

public class UsefulFunctions {

    public static void checkString(String s) throws EmptyStringException {
        if (s == null || s.isEmpty()) {
            throw new EmptyStringException("Fields are missing or empty!!");
        }
    }

    public static void checkString(String s, String Message) throws EmptyStringException {
        if (s == null || s.isEmpty()) {
            throw new EmptyStringException(Message);
        }
    }

}
