package entity;

import java.time.LocalDate;

public class Season {

    private int seasonId;
    private LocalDate season_start;
    private LocalDate season_end;
    private int hotel_id;
    private String seasonName;



    public Season() {
    }

    public Season(int seasonId, LocalDate season_start, LocalDate season_end, String seasonName , int hotel_id) {
        this.seasonId = seasonId;
        this.season_start = season_start;
        this.season_end = season_end;
        this.seasonName = seasonName;
        this.hotel_id = hotel_id;
    }


    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public LocalDate getSeason_start() {
        return season_start;
    }

    public void setSeason_start(LocalDate season_start) {
        this.season_start = season_start;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }



    public LocalDate getSeason_end() {
        return season_end;
    }

    public void setSeason_end(LocalDate season_end) {
        this.season_end = season_end;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonId=" + seasonId +
                ", season_start=" + season_start +
                ", season_end=" + season_end +
                ", hotel_id=" + hotel_id +
                ", seasonName='" + seasonName + '\'' +

                '}';
    }
}
