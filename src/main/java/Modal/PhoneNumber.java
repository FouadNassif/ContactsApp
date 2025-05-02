package Modal;

public class PhoneNumber {

    private int regionCode;
    private int phoneNumber;

    public PhoneNumber(int r, int p) {
        regionCode = r;
        phoneNumber = p;
    }

    public int getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(int regionCode) {
        this.regionCode = regionCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
