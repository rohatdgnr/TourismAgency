package business;


import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HotelManager {
    private HotelDao hotelDao;
    public HotelManager(){
        this.hotelDao = new HotelDao();
    }
    public ArrayList<Hotel> findAll(){return this.hotelDao.findAll();}

    public ArrayList<Hotel> getHotelCity(String hotelName){return this.hotelDao.getHotelCity(hotelName);}

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }
    public int saveAndGetHotelId(Hotel hotel){
        return this.hotelDao.saveAndGetHotelId(hotel);
    }

    public boolean update (Hotel hotel){
        if(this.getById(hotel.getHotel_id())==null){
            Helper.showMsg(hotel.getHotel_id()+" ID bulunamadı");
            return false;
        }
        return this.hotelDao.update(hotel);
    }
    public boolean delete(int id ){

        if(this.getById(id)==null){
            Helper.showMsg(id+" ID kayıtlı model bulunamadı");
            return false;
        }
        return this.hotelDao.delete(id);
    }


    //Otel ID", "Otel Adı", "Şehir", "Bölge", "Adres", "E-posta", "Telefon", "Yıldız Derecesi
    public ArrayList<Object[]> getForTable(int size,ArrayList<Hotel>hotelList){
        ArrayList<Object[]> hotelArrayList = new ArrayList<>();
        for(Hotel hotel : hotelList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = hotel.getHotel_id();
            rowObject[i++] = hotel.getHotel_name();
            rowObject[i++] = hotel.getHotel_city();
            rowObject[i++] = hotel.getHotel_district();
            rowObject[i++] = hotel.getHotel_fllAdres();
            rowObject[i++] = hotel.getHotel_email();
            rowObject[i++] = hotel.getHotel_phone();
            rowObject[i++] = hotel.getHotel_star();
            hotelArrayList.add(rowObject);

        }
        return hotelArrayList;
    }

}
