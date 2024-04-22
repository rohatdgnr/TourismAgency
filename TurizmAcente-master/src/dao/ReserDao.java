package dao;

import core.Db;
import core.Helper;
import entity.Property;
import entity.Reser;
import entity.Room;
import view.GuestInfoAddView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReserDao {
    private Connection con;
    public ReserDao() {
        this.con = Db.getInstance();
    }

    public  Reser getById(int id) {
        Reser obj = null;
        String query = "SELECT * FROM public.reservations WHERE id = ?";
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




    public ArrayList<Reser> findAll() {
        String sql = "SELECT * FROM public.reservations ORDER BY id ASC";
        return this.selectByQuery(sql);
    }
    public ArrayList<Reser> selectByQuery(String query) {
        ArrayList<Reser> modelList = new ArrayList<>();
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
    //    public Reser(int id, int room_id, String reserFllName, String reserPhone, String reserEmail, String reserNote, String reserCheckInDdate, String reserCheckOutDate, String adultNumb, String childNumb, String totalPrice) {
    public Reser match(ResultSet rs) throws SQLException {
        Reser reser = new Reser();
        reser.setId(rs.getInt("id"));
        reser.setRoom_id(rs.getInt("room_id"));
        reser.setReserFllName(rs.getString("reser_fll_name"));
        reser.setReserPhone(rs.getString("reser_phone"));
        reser.setReserEmail(rs.getString("reser_email"));
        reser.setReserNote(rs.getString("reser_note"));
        reser.setReserCheckInDdate(rs.getString("reser_check_in_date"));
        reser.setReserCheckOutDate(rs.getString("reser_check_out_date"));
        reser.setAdultNumb(rs.getString("adult_numb"));
        reser.setChildNumb(rs.getString("child_numb"));
        reser.setTotalPrice(rs.getString("total_price"));
        return reser;
    }





//int roomID, String fllName, String phone, String email, String notes, String checkIn,String checkOut, String adultNnumb, String childNumb, String totalPrice
    public int saveAndGetReserlId(Reser reser) {
        String insertQuery = "INSERT INTO public.reservations (room_id, reser_fll_name, reser_phone, reser_email, reser_note, reser_check_in_date, reser_check_out_date, adult_numb, child_numb, total_price) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pr = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pr.setInt(1, reser.getRoom_id());
            pr.setString(2, reser.getReserFllName());
            pr.setString(3, reser.getReserPhone());
            pr.setString(4, reser.getReserEmail());
            pr.setString(5, reser.getReserNote());
            pr.setString(6, reser.getReserCheckInDdate());
            pr.setString(7, reser.getReserCheckOutDate());
            pr.setString(8, reser.getAdultNumb());
            pr.setString(9, reser.getChildNumb());
            pr.setString(10, reser.getTotalPrice());

            int rowsAffected = pr.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = pr.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Yeni rezervasyon ID'sini döndür
                }
            } else {
                Helper.showMsg("Reservation could not be added."); // Ekleme başarısız olduğunda hata mesajı
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace(); // Hata durumunda istisna işleniyor
        }
        return -1; // Hata durumunda veya başarısız eklemede -1 döndürülüyor
    }

    public boolean update(Reser reser){
        String query = "UPDATE public.reservations SET " +
                "reser_fll_name = ?, " +
                "reser_phone = ?, " +
                "reser_email = ?, " +
                "reser_note = ? " +
                "WHERE id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query); {
                pr.setString(1,reser.getReserFllName());
                pr.setString(2,reser.getReserPhone());
                pr.setString(3,reser.getReserEmail());
                pr.setString(4,reser.getReserNote());
                pr.setInt(5,reser.getId());
                return pr.executeUpdate() != -1;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return true;
        }

    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.reservations WHERE id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }


    // Örnek: reservations listesini veritabanına eklemek
    public boolean saveGuestInfoList(int reservationId) {
        try {
            // reservations listesini GuestInfoAddView sınıfından al
            List<Reser> guestInfoList = GuestInfoAddView.reservations;

            // Her bir Reser nesnesi için guest_info tablosuna ekleme yap
            for (Reser reser : guestInfoList) {
                String insertQuery = "INSERT INTO public.guest_info (reservations_id, full_name, national_number, country, guest_class) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                insertStatement.setInt(1, reservationId);
                insertStatement.setString(2, reser.getGuestFllName());
                insertStatement.setString(3, reser.getGuestnationalNumber());
                insertStatement.setString(4, reser.getGuestCountry());
                insertStatement.setString(5, reser.getGuestClass());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while inserting new guest information: " + e.getMessage());
            return false; // Ekleme işlemi başarısız olduğunda false döndür
        }
        return true; // Ekleme işlemi başarılıysa true döndür
    }




    public ArrayList<Reser> getListByGuestInfo(int id){
        ArrayList<Reser> reserGuestInfoList = new ArrayList<>();
        String query = "SELECT * FROM guest_info WHERE reservations_id = ?";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                Reser resers =new Reser();
                resers.setGuestId(rs.getInt("guest_id"));
                resers.setId(rs.getInt("reservations_id"));
                resers.setGuestFllName(rs.getString("full_name"));
                resers.setGuestnationalNumber(rs.getString("national_number"));
                resers.setGuestCountry(rs.getString("country"));
                resers.setGuestClass(rs.getString("guest_class"));

                reserGuestInfoList.add(resers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reserGuestInfoList;
    }

}
