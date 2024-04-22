package entity;

public class Types {
    private  int typeId;
    private int hotel_id;
    private String typeName;


    public Types() {
    }

    public Types(int typeId,String typeName, int hotelId) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.hotel_id = hotelId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Types{" +
                "typeId=" + typeId +
                ", hotel_id=" + hotel_id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}