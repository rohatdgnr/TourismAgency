package dao;

import core.Db;
import core.Helper;
import entity.Hotel;
import entity.Reser;
import entity.Room;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomDao {

    private Connection con;
    public RoomDao() {
        this.con = Db.getInstance();

    }
    public  Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public ArrayList<Room> findAll() {
        String sql = "SELECT * FROM public.room ORDER BY id ASC";
        return this.selectByQuery(sql);
    }
    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> modelList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                modelList.add(this.match(rs));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return modelList;
    }


    public Room match(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setRoomType(rs.getString("room_type"));
        room.setStock(rs.getInt("stock"));
        room.setSeasonId(rs.getInt("season_id"));
        room.setAdultPrice(rs.getInt("adult_price"));
        room.setChildPrice(rs.getInt("child_price"));
        room.setHotelTypeId(rs.getInt("type_id"));
        room.setHotelId(rs.getInt("hotel_id"));
        room.setRoomPrice(rs.getInt("room_price"));
        return room;
    }
    public ArrayList<Room> searchForRooms(String strt_date, String fnsh_date,String searchCity,String hotelName, int adultNum,int childNum) {
        ArrayList<Room> searchedRooms = new ArrayList<>();

        // SQL sorgusunu oluştur
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT r.* FROM room r ");
        queryBuilder.append("INNER JOIN hotels h ON r.hotel_id = h.hotel_id ");
        queryBuilder.append("INNER JOIN hotel_seasons s ON r.season_id = s.season_id ");
        queryBuilder.append("LEFT JOIN room_properties rp ON r.id = rp.room_id "); // room_properties tablosunu LEFT JOIN ile ekleyin
        queryBuilder.append("WHERE 1 = 1 "); // Başlangıç

        ArrayList<String> where = new ArrayList<>();

        if (searchCity != null && !searchCity.isEmpty()) {
            where.add("h.city = '" + searchCity + "'");
        }

        if (hotelName != null && !hotelName.isEmpty()) {
            where.add("h.hotel_name = '" + hotelName + "'");
        }

        if (strt_date != null && !strt_date.isEmpty() && fnsh_date != null && !fnsh_date.isEmpty()) {
            // Tarihleri LocalDate olarak dönüştür
            LocalDate startDate = LocalDate.parse(strt_date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate endDate = LocalDate.parse(fnsh_date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            // Tarih aralığını WHERE koşuluna ekle
            where.add("s.start_date <= '" + endDate + "'");
            where.add("s.end_date >= '" + startDate + "'");
        }
        if ( adultNum > 0) {
            where.add("rp.adultd_bed_num = " + adultNum);
        }
        if ( childNum > 0) {
            where.add("rp.child_bed_num = " + childNum);
        }


        // WHERE koşullarını SQL sorgusuna ekle
        if (!where.isEmpty()) {
            queryBuilder.append(" AND ");
            queryBuilder.append(String.join(" AND ", where));
        }

        // SQL sorgusunu tamamla
        String query = queryBuilder.toString();

        // Oluşturulan sorguyu kullanarak odaları seç
        searchedRooms = selectByQuery(query);

        // Sonuçları döndür
        return searchedRooms;
    }

    public int save(Room room) {
        String query = "INSERT INTO room (room_type, stock, season_id, adult_price, child_price, type_id, hotel_id,room_price) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pr.setString(1,room.getRoomType());
            pr.setInt(2,room.getStock());
            pr.setInt(3,room.getSeasonId());
            pr.setInt(4,room.getAdultPrice());
            pr.setInt(5,room.getChildPrice());
            pr.setInt(6,room.getHotelTypeId());
            pr.setInt(7,room.getHotelId());
            pr.setInt(8,room.getRoomPrice());

            int rowsAffected = pr.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = pr.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Yeni otel ID'sini döndür
                }
            }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
        return -1; // Hata durumunda veya başarısız eklemede -1 döndür
}
    public boolean stockUpdate(Room room,int num){
        String query = "UPDATE public.room SET " +
                "stock = ? " +
                "WHERE id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query); {
                pr.setInt(1,room.getStock()+num);
                pr.setInt(2,room.getId());
                return pr.executeUpdate() != -1;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return true;
        }

    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.room WHERE id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

}
