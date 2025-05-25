package Model;

import java.io.Serializable;

public class PhoneNumber implements Comparable, Serializable {

    private String regionCode;
    private String phoneNumber;

    public PhoneNumber(String r, String p) {
        regionCode = r;
        phoneNumber = p;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return regionCode + " " + phoneNumber;
    }

    @Override
    public int compareTo(Object o) {
        PhoneNumber pn = (PhoneNumber) o;
        if (this.getPhoneNumber().compareTo(pn.getPhoneNumber()) > 0) {
            return 1;
        } else if (this.getPhoneNumber().compareTo(pn.getPhoneNumber()) < 0) {
            return -1;
        } else {
            if (this.getRegionCode().compareTo(pn.getRegionCode()) > 0) {
                return 1;
            } else if (this.getRegionCode().compareTo(pn.getRegionCode()) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
