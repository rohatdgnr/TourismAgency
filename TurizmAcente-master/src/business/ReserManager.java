package business;

import core.Helper;
import dao.HotelDao;
import dao.ReserDao;
import entity.Hotel;
import entity.Property;
import entity.Reser;
import entity.Room;

import java.util.ArrayList;
import java.util.List;

public class ReserManager {
    private ReserDao reserDao;
    private Room room;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    public ReserManager(){

        this.reserDao = new ReserDao();
        this.hotelManager =new HotelManager();
        this.roomManager = new RoomManager();
        this.room=new Room();
    }
    public Reser getById(int id) {
        return this.reserDao.getById(id);
    }
    public boolean update(Reser reser) {
        if (this.getById(reser.getId()) == null) {
            Helper.showMsg(reser.getId() + " ID bulunamadı");
            return false;
        }
        return this.reserDao.update(reser);
    }
    public boolean delete(int id ){

        if(this.getById(id)==null){
            Helper.showMsg(id+" ID kayıtlı model bulunamadı");
            return false;
        }
        return this.reserDao.delete(id);
    }

    public int saveAndGetReserlId(Reser reser){
        return this.reserDao.saveAndGetReserlId(reser);
    }
    public boolean saveGuestInfoList(int reservationId){
        return this.reserDao.saveGuestInfoList(reservationId);
    }
    public ArrayList<Reser> findAll(){return this.reserDao.findAll();}
    public ArrayList<Reser> getListByGuestInfo(int id){
        return this.reserDao.getListByGuestInfo(id);
    }
    public ArrayList<Object[]>getForTableGuestInfo(int size,int id){
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for(Reser obj : this.getListByGuestInfo(id)){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getGuestFllName();
            rowObject[i++] = obj.getGuestnationalNumber();
            rowObject[i++] = obj.getGuestCountry();
            rowObject[i++] = obj.getGuestClass();
            brandRowList.add(rowObject);

        }
        return brandRowList;
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Reser>resersList){
        ArrayList<Object[]> reserArrayList = new ArrayList<>();
        for(Reser reser : resersList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = reser.getId();
            rowObject[i++] = reser.getRoom_id();
            rowObject[i++] = hotelManager.getById(roomManager.getById(reser.getRoom_id()).getHotelId()).getHotel_name();
            rowObject[i++] = roomManager.getById(reser.getRoom_id()).getRoomType();
            rowObject[i++] = reser.getReserFllName();
            rowObject[i++] = reser.getReserPhone();
            rowObject[i++] = reser.getReserEmail();
            rowObject[i++] = reser.getReserNote();
            rowObject[i++] = reser.getReserCheckInDdate();
            rowObject[i++] = reser.getReserCheckOutDate();
            rowObject[i++] = reser.getAdultNumb();
            rowObject[i++] = reser.getChildNumb();
            rowObject[i++] = reser.getTotalPrice();
            reserArrayList.add(rowObject);

        }
        return reserArrayList;
    }
}
