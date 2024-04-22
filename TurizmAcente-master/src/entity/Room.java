package entity;

public class Room {
    private int id;
    private String roomType;
    private int stock;
    private int seasonId;
    private int adultPrice;
    private int childPrice;
    private int hotelTypeId;
    private int hotelId;
    private int roomPrice;
    public enum RoomType {
        SINGLE_ROOM("Single Room"),
        DOUBLE_ROOM("Double Room"),
        JUNIOR_SUITE_ROOM("Junior Suite Room"),
        SUITE_ROOM("Suite Room");

        private final String displayName;

        RoomType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public Room() {
    }

    public Room(String roomType, int stock, int seasonId, int adultPrice, int childPrice, int hotelTypeId, int hotelId,int roomPrice) {

        this.roomType = roomType;
        this.stock = stock;
        this.seasonId = seasonId;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.hotelTypeId = hotelTypeId;
        this.hotelId = hotelId;
        this.roomPrice=roomPrice;
    }
    public Room(int id, String roomType, int stock, int seasonId, int adultPrice, int childPrice, int hotelTypeId, int hotelId) {
        this.id = id;
        this.roomType = roomType;
        this.stock = stock;
        this.seasonId = seasonId;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.hotelTypeId = hotelTypeId;
        this.hotelId = hotelId;
    }

    // Diğer getter ve setter metotları


    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public int getHotelTypeId() {
        return hotelTypeId;
    }

    public void setHotelTypeId(int hotelTypeId) {
        this.hotelTypeId = hotelTypeId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    // toString metodu (isteğe bağlı)
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", stock=" + stock +
                ", seasonId=" + seasonId +
                ", adultPrice=" + adultPrice +
                ", childPrice=" + childPrice +
                ", hotelTypeId=" + hotelTypeId +
                ", hotelId=" + hotelId +
                '}';
    }
}
