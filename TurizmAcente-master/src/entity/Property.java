package entity;

import java.util.List;

public class Property {

    private int propertyID;
    private List<String> propertyNames; // Özellik isimlerini liste olarak tutmak için List<String> kullanıyoruz
    private int hotel_id; // Otelin kimliğini (hotel_id) tutmak için daha genel bir isim olan hotelId kullanabiliriz


    private int roomPropertyId;
    private String roomProperty;
    private int roomId;
    private int roomAdultBedNum;
    private int roomChildBedNum;
    private int roomArea;

    private Room room;

    public Property() {
    }
    public Property(int roomPropertyId, String roomProperty, int roomId, int roomAdultBedNum,int roomChildBedNum, int roomArea) {
        this.roomPropertyId = roomPropertyId;
        this.roomProperty = roomProperty;
        this.roomId = roomId;
        this.roomAdultBedNum = roomAdultBedNum;
        this.roomChildBedNum = roomAdultBedNum;
        this.roomArea = roomArea;

    }
    public Property(List<String> propertyNames) {
        this.propertyNames = propertyNames;
    }
    // Kurucu metod (constructor)
    public Property(int propertyID, List<String> propertyNames, int hotel_id) {
        this.propertyID = propertyID;
        this.propertyNames = propertyNames;
        this.hotel_id = hotel_id;
    }


    public int getRoomPropertyId() {
        return roomPropertyId;
    }

    public void setRoomPropertyId(int roomPropertyId) {
        this.roomPropertyId = roomPropertyId;
    }

    public int getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(int roomArea) {
        this.roomArea = roomArea;
    }

    public int getRoomAdultBedNum() {
        return roomAdultBedNum;
    }

    public void setRoomAdultBedNum(int roomAdultBedNum) {
        this.roomAdultBedNum = roomAdultBedNum;
    }

    public int getRoomChildBedNum() {
        return roomChildBedNum;
    }

    public void setRoomChildBedNum(int roomChildBedNum) {
        this.roomChildBedNum = roomChildBedNum;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomProperty() {
        return roomProperty;
    }

    public void setRoomProperty(String roomProperty) {
        this.roomProperty = roomProperty;
    }

    // Getter ve setter metotları
    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public List<String> getPropertyNames() {
        return propertyNames;
    }

    public void setPropertyNames(List<String> propertyNames) {
        this.propertyNames = propertyNames;
    }
    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyID=" + propertyID +
                ", propertyNames=" + propertyNames +
                ", hotel_id=" + hotel_id +
                ", roomPropertyId=" + roomPropertyId +
                ", roomProperty='" + roomProperty + '\'' +
                ", roomId=" + roomId +
                ", roomBed='" + roomAdultBedNum + '\'' +
                ", roomArea=" + roomArea +
                ", room=" + room +
                '}';
    }
}
