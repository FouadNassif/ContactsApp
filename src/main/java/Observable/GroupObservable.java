package Observable;

import Model.Group;
import java.util.Observable;

public class GroupObservable extends Observable {

    public void groupAdded(Group group) {
        setChanged();
        notifyObservers(group);
    }
}
