package business;

import core.Helper;
import dao.RoomDao;
import entity.Room;

import java.util.ArrayList;


public class RoomManager {
    private HotelManager hotelManager;
    private SeasonManager seasonManager ;
    private  final RoomDao roomDao ;
    public RoomManager() {
        // this.con = Db.getInstance();
        this.roomDao = new RoomDao();
        this.hotelManager =new HotelManager();
        this.seasonManager = new SeasonManager();

    }
    public Room getById(int id) {
        return this.roomDao.getById(id);
    }


    public ArrayList<Room> findAll(){return this.roomDao.findAll();}

    public int save(Room room){
        return this.roomDao.save(room);
    }

    public boolean stockUpdate(Room room,int num){
        return this.roomDao.stockUpdate(room,num);
    }

    public boolean delete(int id ){

        if(this.getById(id)==null){
            Helper.showMsg(id+" ID kayıtlı model bulunamadı");
            return false;
        }
        return this.roomDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room>roomList){
        ArrayList<Object[]> hotelArrayList = new ArrayList<>();
        for(Room room : roomList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = room.getId();
            rowObject[i++] = hotelManager.getById(room.getHotelId()).getHotel_name();
            rowObject[i++] = hotelManager.getById(room.getHotelId()).getHotel_city();
            rowObject[i++] = room.getRoomType();
            rowObject[i++] = room.getStock();
            rowObject[i++] = seasonManager.getSeasonDate(room.getSeasonId()).getSeason_start()+ " - " + seasonManager.getSeasonDate(room.getSeasonId()).getSeason_end();
            rowObject[i++] = room.getAdultPrice();
            rowObject[i++] = room.getChildPrice();
            rowObject[i++] = room.getHotelTypeId();
            hotelArrayList.add(rowObject);

        }
        return hotelArrayList;
    }

    public ArrayList<Room> searchForRooms(String strt_date, String fnsh_date,String searchCity,String hotelName, int adultNum,int childNum){

        return this.roomDao.searchForRooms(strt_date,fnsh_date,searchCity,hotelName,adultNum,childNum);}
}
