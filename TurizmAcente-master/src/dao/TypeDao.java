package dao;

import core.Db;
import entity.Hotel;
import entity.Property;
import entity.Types;
import entity.User;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class TypeDao {

    private Connection con;


    public TypeDao() {
        this.con = Db.getInstance();
    }

    public Types getById(int id) {
        Types obj = null;
        String query = "SELECT * FROM public.type_hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs != null && !rs.isClosed() && rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;

    }
    public String getByTypeId(int id) {
        String typeName = null;  // Geri dönüş değeri için başlangıçta null

        String query = "SELECT type_name FROM public.type_hotel WHERE type_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, id);
            try (ResultSet rs = pr.executeQuery()) {
                if (rs.next()) {
                    typeName = rs.getString("type_name");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata durumunda typeName zaten null olarak kalacak
        }

        return typeName;
    }

    public ArrayList<Types> findAll(int hotelId) {
        ArrayList<Types> typesList = new ArrayList<>();

        String query = "SELECT type_id,hotel_id,type_name FROM public.type_hotel WHERE hotel_id = ?";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotelId);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Types types = match(resultSet);
                typesList.add(types);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typesList;
    }


    // Method to create Types object from ResultSet
    private Types match(ResultSet rs) throws SQLException {
        Types obj = new Types();
        obj.setTypeId(rs.getInt("type_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setTypeName(rs.getString("type_name"));
        return obj;
    }
    public boolean save(int hotelId, List<String> rightList) {
        try {
            // Sağ listedeki özellikleri kontrol et ve ekle
            for (String feature : rightList) {
                // Özellik veritabanında bulunmuyorsa, ekle
                String insertQuery = "INSERT INTO type_hotel (hotel_id, type_name) VALUES (?, ?)";
                PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                insertStatement.setInt(1, hotelId);
                insertStatement.setString(2, feature);
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ekleme işlemi başarısız olduğunda false döndür
        }
        return true; // Ekleme işlemi başarılıysa true döndür
    }

    public boolean update(int hotelId, List<String> rightList) {
        try {
            // Var olan tüm özellikleri veritabanından al
            String selectQuery = "SELECT type_name FROM type_hotel WHERE hotel_id = ?";
            PreparedStatement selectStatement = con.prepareStatement(selectQuery);
            selectStatement.setInt(1, hotelId);
            ResultSet resultSet = selectStatement.executeQuery();

            HashSet<String> existingFeatures = new HashSet<>();
            while (resultSet.next()) {
                existingFeatures.add(resultSet.getString("type_name"));
            }

            // Sağ listedeki özellikleri kontrol et ve ekle
            for (String feature : rightList) {
                if (!existingFeatures.contains(feature)) {
                    // Özellik veritabanında bulunmuyorsa, ekle
                    String insertQuery = "INSERT INTO type_hotel (hotel_id, type_name) VALUES (?, ?)";
                    PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                    insertStatement.setInt(1, hotelId);
                    insertStatement.setString(2, feature);
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }

    public boolean deleteType(int hotelId, List<String> rightList) {
        try {
            // Var olan tüm özellikleri veritabanından al
            String selectQuery = "SELECT type_name FROM type_hotel WHERE hotel_id = ?";
            PreparedStatement selectStatement = con.prepareStatement(selectQuery);
            selectStatement.setInt(1, hotelId);
            ResultSet resultSet = selectStatement.executeQuery();

            HashSet<String> existingFeatures = new HashSet<>();
            while (resultSet.next()) {
                existingFeatures.add(resultSet.getString("type_name"));
            }

            // Sağ listedeki özellikleri kontrol et ve sil
            for (String feature : rightList) {
                if (existingFeatures.contains(feature)) {
                    // Özellik veritabanında bulunmuyorsa, sil
                    String deleteQuery = "DELETE FROM type_hotel WHERE hotel_id = ? AND type_name = ?";
                    PreparedStatement insertStatement = con.prepareStatement(deleteQuery);
                    insertStatement.setInt(1, hotelId);
                    insertStatement.setString(2, feature);
                    insertStatement.executeUpdate();
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}




