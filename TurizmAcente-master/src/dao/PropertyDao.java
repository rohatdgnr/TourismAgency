package dao;

import core.Db;
import entity.Property;
import org.postgresql.jdbc.PgArray;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDao {
    private Connection con;

    private final HotelDao hotelDao = new HotelDao();

    public PropertyDao() {
        this.con = Db.getInstance();

    }
    public Property getById(int id) {
        Property obj = null;
        String query = "SELECT * FROM public.hotel_property WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            } else {
                // ResultSet boş ise veya rs.next() false dönerse, yeni bir Types objesi oluşturarak id'yi set edin
                obj = new Property();
                obj.setHotel_id(id); // Dışarıdan gelen id'yi set et
                obj.setPropertyNames(List.of(new String[] {"Ücretsiz Otopark",
                        "Ücretsiz WiFi",
                        "Yüzme Havuzu",
                        "Fitness Center",
                        "Hotel Concierge",
                        "SPA",
                        "7/24 Oda Servisi"}));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;

    }



    public List<String[]> getPropertyList(int hotelId) {
        List<String[]> propertyList = new ArrayList<>();

        String query = "SELECT * FROM public.hotel_property WHERE hotel_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Array propertyNamesArray = rs.getArray("property_names");
                if (propertyNamesArray != null) {
                    // PostgreSQL array'i PgArray olarak elde et
                    PgArray pgArray = (PgArray) propertyNamesArray;

                    // PgArray'den Java dizisine dönüşüm
                    Object[] pgElements = (Object[]) pgArray.getArray();

                    // Java dizisini String dizisine dönüştür
                    String[] propertyNames = new String[pgElements.length];
                    for (int i = 0; i < pgElements.length; i++) {
                        propertyNames[i] = (String) pgElements[i];
                    }

                    // Listeye ekle
                    propertyList.add(propertyNames);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return propertyList;
    }

    public Property match(ResultSet rs) throws SQLException {
        Property property = new Property();
        property.setPropertyID(rs.getInt("property_id"));
        property.setHotel_id(rs.getInt("hotel_id"));

        // Get the java.sql.Array from the ResultSet
        Array propertyNamesArray = rs.getArray("property_names");

        if (propertyNamesArray != null) {
            // Convert java.sql.Array to Object array
            Object[] propertyNamesData = (Object[]) propertyNamesArray.getArray();

            // Convert Object array to List<String>
            List<String> propertyNamesList = new ArrayList<>();
            for (Object element : propertyNamesData) {
                if (element != null) {
                    propertyNamesList.add(element.toString());
                }
            } // Set the propertyNames list in the Property object
            property.setPropertyNames(propertyNamesList);
        }

        return property;
    }

    public ArrayList<Property> findAll() {
        String sql = "SELECT * FROM public.hotel_property ORDER BY property_id ASC";
        return this.selectByQuery(sql);

    }

    public ArrayList<Property> selectByQuery(String query) {
        ArrayList<Property> modelList = new ArrayList<>();
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


    public boolean save(Property property, int hotelId) {
        String insertQuery = "INSERT INTO public.hotel_property (property_names, hotel_id) VALUES (?, ?)";

        try {
            PreparedStatement pr = con.prepareStatement(insertQuery);
            Array propertyNamesArray = con.createArrayOf("text", property.getPropertyNames().toArray());
            pr.setArray(1, propertyNamesArray);
            pr.setInt(2, hotelId);

            int rowsAffected = pr.executeUpdate();
            return rowsAffected > 0; // Ekleme başarılıysa true döndür
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false; // Hata durumunda veya başarısız eklemede false döndür
    }


    public boolean update(Property property) {
        String query = "UPDATE public.hotel_property SET " +
                "property_id = ?," +
                "property_names = ? " +  // Düzeltme: Virgül gereksizdi
                "WHERE hotel_id = ?";


        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, property.getPropertyID());
            if (!property.getPropertyNames().contains("Otel Özellikleri")) {
                Array propertyNamesArray = con.createArrayOf("text", property.getPropertyNames().toArray());
                pr.setArray(2, propertyNamesArray);
            }
            pr.setInt(3, property.getHotel_id());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public boolean saveRoomProperty(Property property){
        String query = "INSERT INTO room_properties (property, room_id, adultd_bed_num, child_bed_num, area ) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1,property.getRoomProperty());
            pr.setInt(2,property.getRoomId());
            pr.setInt(3, property.getRoomAdultBedNum());
            pr.setInt(4, property.getRoomChildBedNum());
            pr.setInt(5, property.getRoomArea());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.hotel_property WHERE hotel_id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }
    public ArrayList<Property> getListByRoomID( int id){
        ArrayList<Property> roomPropertiesList = new ArrayList<>();
        Property obj;
        Property property =new Property();
        String query = "SELECT * FROM room_properties WHERE room_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Property();
                obj.setRoomProperty(rs.getString("property"));
                obj.setRoomAdultBedNum(rs.getInt("adultd_bed_num"));
                obj.setRoomChildBedNum(rs.getInt("child_bed_num"));
                obj.setRoomArea(rs.getInt("area"));
                roomPropertiesList.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomPropertiesList;
    }

    public Property getByBedNum(int id) {
        Property obj = new Property();
        String query = "SELECT adultd_bed_num,child_bed_num FROM public.room_properties WHERE room_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj.setRoomAdultBedNum(rs.getInt("adultd_bed_num"));
                obj.setRoomChildBedNum(rs.getInt("child_bed_num"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;

    }

}

