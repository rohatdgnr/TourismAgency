package dao;

import core.Db;
import entity.Hotel;
import entity.Property;
import entity.Season;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeasonDao {
    private Connection con;

    public SeasonDao() {
        this.con = Db.getInstance();
    }


    public Season getById(int hotelId) {
        Season season = null;
        String query = "SELECT season_id, hotel_id, start_date, end_date, season_type FROM hotel_seasons WHERE hotel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                season = match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return season;
    }
    public Season getSeasonDate(int seasonId) {
        Season season = null;
        String query = "SELECT season_id, hotel_id,start_date, end_date, season_type FROM hotel_seasons WHERE season_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, seasonId);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                season = match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return season;
    }


    public ArrayList<Season> findAll(int hotelId) {
        ArrayList<Season> seasonArrayList = new ArrayList<>();

        String query = "SELECT season_id,hotel_id, start_date, end_date ,season_type FROM hotel_seasons WHERE hotel_id = ? ORDER BY season_id ASC";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Season season = match(resultSet);
                // Sezon türüne göre başlangıç ve bitiş tarihlerini ayarla

                seasonArrayList.add(season);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasonArrayList;
    }
    public List<Season> getBySeasonList(int hotelId) {
        List<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM hotel_seasons WHERE hotel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season); // Her sezonu listeye ekle
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }



    private Season match(ResultSet rs) throws SQLException {
        Season season = new Season();
        season.setSeasonId(rs.getInt("season_id"));
        season.setHotel_id(rs.getInt("hotel_id"));
        season.setSeason_start(LocalDate.parse(rs.getString("start_date")));
        season.setSeason_end(LocalDate.parse(rs.getString("end_date")));
        season.setSeasonName(rs.getString("season_type"));
        String seasonType = season.getSeasonName();

        return season;
    }



  public boolean save(List<Season> seasons, int hotelId) {
      String insertQuery = "INSERT INTO hotel_seasons (hotel_id, start_date, end_date, season_type) VALUES (?, ?, ?, ?)";
      boolean success = true;

      try (PreparedStatement pr = con.prepareStatement(insertQuery)) {
          for (Season season : seasons) {
              pr.setInt(1, hotelId);
              pr.setDate(2, Date.valueOf(season.getSeason_start()));
              pr.setDate(3, Date.valueOf(season.getSeason_end()));
              pr.setString(4, season.getSeasonName());

              int rowsAffected = pr.executeUpdate();
              if (rowsAffected != 1) {
                  success = false;
                  break;
              }
          }
      } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("An error occurred while inserting new seasons: " + e.getMessage());
          success = false;
      }

      return success;
  }


    public boolean update(Season season) {
        String updateQuery = "UPDATE hotel_seasons "
                + "SET start_date = ?, end_date = ? "
                + "WHERE hotel_id = ? AND season_type = ?";
        try (PreparedStatement pr = con.prepareStatement(updateQuery)) {
            pr.setDate(1, Date.valueOf(season.getSeason_start()));
            pr.setDate(2, Date.valueOf(season.getSeason_end()));
            pr.setInt(3, season.getHotel_id());
            pr.setString(4, season.getSeasonName());

            int rowsAffected = pr.executeUpdate();
            return rowsAffected > 0; // Güncelleme başarılı ise true döndür

        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda hatayı yazdır
            return false; // Güncelleme başarısız ise false döndür
        }
    }

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> seasonArrayList = new ArrayList<>();

        try (ResultSet rs = con.createStatement().executeQuery(query)) {
            while (rs.next()) {
                Season season = match(rs);
                seasonArrayList.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasonArrayList;
    }

    public Season getBySeason(int id) {
        Season obj = new Season();
        String query ="SELECT * FROM hotel_seasons WHERE season_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {

                obj.setSeason_start(LocalDate.parse(rs.getString("start_date")));
                obj.setSeason_end(LocalDate.parse(rs.getString("end_date")));
                obj.setSeasonName(rs.getString("season_type"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;

    }
}
