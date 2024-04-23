package dao;

import core.Db;
import core.Helper;
import entity.Hotel;
import entity.Property;

import java.sql.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class HotelDao {

    private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }

    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotels WHERE hotel_id = ?";
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

    public ArrayList<Hotel> findAll() {
        String sql = "SELECT * FROM public.hotels ORDER BY hotel_id ASC";
        return this.selectByQuery(sql);
    }

    // Metot, otel bilgilerini güncellemek için kullanılır
    public boolean update(Hotel hotel) {
        String updateQuery = "UPDATE public.hotels " +
                "SET hotel_name = ?, " +
                "city = ?, " +
                "district = ?, " +
                "full_address = ?, " +
                "email = ?, " +
                "phone_number = ?, " +
                "star = ? " +
                "WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(updateQuery);
            // PreparedStatement'e parametreleri set et
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_city());
            pr.setString(3, hotel.getHotel_district());
            pr.setString(4, hotel.getHotel_fllAdres());
            pr.setString(5, hotel.getHotel_email());
            pr.setString(6, hotel.getHotel_phone());
            pr.setInt(7, hotel.getHotel_star());
            pr.setInt(8, hotel.getHotel_id());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public int saveAndGetHotelId(Hotel hotel) {
        String insertQuery = "INSERT INTO public.hotels (" +
                "hotel_name, city, district, full_address, email, phone_number, star) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Önce veritabanında aynı e-posta adresiyle kayıt olup olmadığını kontrol et
            if (!isEmailExists(hotel.getHotel_email())) {
                PreparedStatement pr = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                pr.setString(1, hotel.getHotel_name());
                pr.setString(2, hotel.getHotel_city());
                pr.setString(3, hotel.getHotel_district());
                pr.setString(4, hotel.getHotel_fllAdres());
                pr.setString(5, hotel.getHotel_email());
                pr.setString(6, hotel.getHotel_phone());
                pr.setInt(7, hotel.getHotel_star());

                int rowsAffected = pr.executeUpdate();
                if (rowsAffected == 1) {
                    ResultSet generatedKeys = pr.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Yeni otel ID'sini döndür
                    }
                }
            } else {
                Helper.showMsg("Bu e-posta ile daha önce kayıt yapılmış"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1; // Hata durumunda veya başarısız eklemede -1 döndür
    }

    // Veritabanında verilen e-posta adresiyle kayıtlı otel var mı diye kontrol eden metod
    private boolean isEmailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM public.hotels WHERE email = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Eğer count 0'dan büyükse e-posta adresiyle kayıtlı otel var demektir
            }
        }
        return false;
    }


    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> modelList = new ArrayList<>();
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

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setHotel_name(rs.getString("hotel_name"));
        obj.setHotel_city(rs.getString("city"));
        obj.setHotel_district((rs.getString("district")));
        obj.setHotel_fllAdres((rs.getString("full_address")));
        obj.setHotel_email((rs.getString("email")));
        obj.setHotel_phone((rs.getString("phone_number")));
        obj.setHotel_star((rs.getInt("star")));
        return obj;
    }
    public ArrayList<Hotel> getHotelCity(String hotelName){
        ArrayList<Hotel> roomPropertiesList = new ArrayList<>();
        Hotel obj;
        String query = "SELECT hotel_id,city FROM public.hotels WHERE hotel_name = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, hotelName);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hotel();
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setHotel_city(rs.getString("city"));

                roomPropertiesList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomPropertiesList;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.hotels WHERE hotel_id =?";
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
