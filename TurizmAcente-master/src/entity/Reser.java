package entity;

public class Reser {
    private int id;
    private int room_id;
    private String reserFllName;
    private String reserPhone;
    private String reserEmail;
    private String reserNote;
    private String reserCheckInDdate;
    private String reserCheckOutDate;
    private String adultNumb;
    private String childNumb;
    private String totalPrice;

    private  int guestId;
    private String guestFllName;

    private  String guestnationalNumber;

    private String guestCountry;
    private String guestClass;

    public Reser() {
    }

    public Reser(int guestId,String guestCountry, String guestnationalNumber, String guestFllName,String guestClass) {
        this.guestId=guestId;
        this.guestCountry = guestCountry;
        this.guestnationalNumber = guestnationalNumber;
        this.guestFllName = guestFllName;
        this.guestClass = guestClass;
    }

    public Reser(int id, int room_id, String reserFllName, String reserPhone, String reserEmail, String reserNote, String reserCheckInDdate, String reserCheckOutDate, String adultNumb, String childNumb, String totalPrice) {
        this.id = id;
        this.room_id = room_id;
        this.reserFllName = reserFllName;
        this.reserPhone = reserPhone;
        this.reserEmail = reserEmail;
        this.reserNote = reserNote;
        this.reserCheckInDdate = reserCheckInDdate;
        this.reserCheckOutDate = reserCheckOutDate;
        this.adultNumb = adultNumb;
        this.childNumb = childNumb;
        this.totalPrice = totalPrice;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestFllName() {
        return guestFllName;
    }

    public void setGuestFllName(String guestFllName) {
        this.guestFllName = guestFllName;
    }

    public String getGuestnationalNumber() {
        return guestnationalNumber;
    }

    public void setGuestnationalNumber(String guestnationalNumber) {
        this.guestnationalNumber = guestnationalNumber;
    }

    public String getGuestCountry() {
        return guestCountry;
    }

    public void setGuestCountry(String guestCountry) {
        this.guestCountry = guestCountry;
    }

    public String getGuestClass() {
        return guestClass;
    }

    public void setGuestClass(String guestClass) {
        this.guestClass = guestClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getReserFllName() {
        return reserFllName;
    }

    public void setReserFllName(String reserFllName) {
        this.reserFllName = reserFllName;
    }

    public String getReserPhone() {
        return reserPhone;
    }

    public void setReserPhone(String reserPhone) {
        this.reserPhone = reserPhone;
    }

    public String getReserEmail() {
        return reserEmail;
    }

    public void setReserEmail(String reserEmail) {
        this.reserEmail = reserEmail;
    }

    public String getReserNote() {
        return reserNote;
    }

    public void setReserNote(String reserNote) {
        this.reserNote = reserNote;
    }

    public String getReserCheckInDdate() {
        return reserCheckInDdate;
    }

    public void setReserCheckInDdate(String reserCheckInDdate) {
        this.reserCheckInDdate = reserCheckInDdate;
    }

    public String getReserCheckOutDate() {
        return reserCheckOutDate;
    }

    public void setReserCheckOutDate(String reserCheckOutDate) {
        this.reserCheckOutDate = reserCheckOutDate;
    }

    public String getAdultNumb() {
        return adultNumb;
    }

    public void setAdultNumb(String adultNumb) {
        this.adultNumb = adultNumb;
    }

    public String getChildNumb() {
        return childNumb;
    }

    public void setChildNumb(String childNumb) {
        this.childNumb = childNumb;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
