package business;

import core.Helper;
import dao.HotelDao;
import dao.PropertyDao;
import entity.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyManager {

    private HotelDao hotelDao;
    private PropertyDao propertyDao;
    private RoomManager roomManager;
    private Room room;



    public PropertyManager() {

        this.propertyDao = new PropertyDao();
        this.room= new Room();
        this.roomManager = new RoomManager();
    }

    public Property getById(int id) {
        return this.propertyDao.getById(id);
    }

    public ArrayList<Property> getListByRoomID(int id){
        return this.propertyDao.getListByRoomID(id);
    }
    public Property getByBedNum(int id) {
        return this.propertyDao.getByBedNum(id);
    }




    public ArrayList<Object[]>getForTableRoomProperty(int size,int id){
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for(Property obj : this.getListByRoomID(id)){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getRoomProperty();
            rowObject[i++] = obj.getRoomAdultBedNum();
            rowObject[i++] = obj.getRoomChildBedNum();
            rowObject[i++] = roomManager.getById(id).getAdultPrice();
            rowObject[i++] = roomManager.getById(id).getChildPrice();
            rowObject[i++] = obj.getRoomArea();
            rowObject[i++] = roomManager.getById(id).getRoomPrice();

            brandRowList.add(rowObject);

        }
        return brandRowList;
    }

    public boolean update(Property property) {
        if (this.getById(property.getPropertyID()) == null) {
            Helper.showMsg(property.getPropertyID() + " ID bulunamadı");
            return false;
        }
        return this.propertyDao.update(property);
    }

    public boolean save(Property property,int hotelId){
        return this.propertyDao.save(property,hotelId);
    }
    public boolean saveRoomProperty(Property property){
        return this.propertyDao.saveRoomProperty(property);
    }

    public List<String[]> getPropertyList(int hotelId) {
        return this.propertyDao.getPropertyList(hotelId);
    }
}
