package Observable;

import Model.Contact;
import java.util.Observable;

public class ContactObservable extends Observable {

    public void contactAdded(Contact contact) {
        setChanged();
        notifyObservers(contact);
    }
}
