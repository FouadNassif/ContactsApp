/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observable;

import Model.Contact;
import java.util.Observable;

/**
 *
 * @author fouad
 */
public class ContactObservable extends Observable {

    public void contactAdded(Contact contact) {
        setChanged();
        notifyObservers(contact);
    }
}
