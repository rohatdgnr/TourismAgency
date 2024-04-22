package entity;

public class Hotel {
    private int hotel_id;
    private String hotel_name;
    private String hotel_city;
    private String hotel_district;
    private String hotel_fllAdres;
    private String hotel_phone;
    private String hotel_email;
    private int hotel_star;


    public Hotel() {
    }

    public Hotel(int hotel_id, String hotel_name, String hotel_city, String hotel_district, int hotel_star, String hotel_fllAdres, String hotel_phone,
                 String hotel_email) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_star = hotel_star;
        this.hotel_city = hotel_city;
        this.hotel_district = hotel_district;
        this.hotel_fllAdres = hotel_fllAdres;
        this.hotel_phone = hotel_phone;
        this.hotel_email = hotel_email;

    }


    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }
    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getHotel_district() {
        return hotel_district;
    }

    public void setHotel_district(String hotel_district) {
        this.hotel_district = hotel_district;
    }

    public int getHotel_star() {
        return hotel_star;
    }

    public void setHotel_star(int hotel_star) {
        this.hotel_star = hotel_star;
    }



    public String getHotel_fllAdres() {
        return hotel_fllAdres;
    }

    public void setHotel_fllAdres(String hotel_fllAdres) {
        this.hotel_fllAdres = hotel_fllAdres;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_email() {
        return hotel_email;
    }

    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", hotel_name='" + hotel_name + '\'' +
                ", hotel_city='" + hotel_city + '\'' +
                ", hotel_district='" + hotel_district + '\'' +
                ", hotel_address='" + hotel_fllAdres + '\'' +
                ", hotel_phone='" + hotel_phone + '\'' +
                ", hotel_email='" + hotel_email + '\'' +
                ", hotel_star='" + hotel_star + '\'' +
                '}';
    }
}
