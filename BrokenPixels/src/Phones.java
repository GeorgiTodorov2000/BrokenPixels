public class Phones {
    private String PHONE_MODEL = "";
    private int BROKEN;
    private int NUMBERS_OF_CHECKED_PHONES;

    public Phones(String phoneModel, int broken) {
        this.PHONE_MODEL = phoneModel;
        this.BROKEN = broken;

    }
    public Phones() {

    }
    public void print() {
        System.out.print(PHONE_MODEL);
    }

    public String getPHONE_MODEL() {
        return PHONE_MODEL;
    }

    public int getBROKEN() {
        return BROKEN;
    }

    public int getNUMBERS_OF_CHECKED_PHONES() {
        return NUMBERS_OF_CHECKED_PHONES;
    }

    public void setNUMBERS_OF_CHECKED_PHONES(int NUMBERS_OF_CHECKED_PHONES) {
        this.NUMBERS_OF_CHECKED_PHONES = NUMBERS_OF_CHECKED_PHONES;
    }
}
