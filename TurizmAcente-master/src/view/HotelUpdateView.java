package view;

import business.HotelManager;
import business.PropertyManager;
import business.SeasonManager;
import business.TypeManager;
import core.Helper;
import entity.Hotel;
import entity.Property;
import entity.Season;
import entity.Types;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HotelUpdateView extends Layout {
    private JPanel container;
    private JList<String> lst_left_pro;
    private JList<String> lst_right_pro;
    private JList<String> lst_left_type;
    private JList<String> lst_right_type;
    private JButton btn_single_right;
    private JButton btn_multi_right;
    private JButton btn_multi_left;
    private JButton btn_single_left;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_district;
    private JTextField fld_hoteml_fllAdres;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phone;
    private JComboBox<Integer> cmb_hotel_star;
    private JButton btn_hotel_save;
    private JButton btn_single_right_type;
    private JButton btn_multi_right_type;
    private JButton btn_multi_left_type;
    private JRadioButton rd_btn_sum;
    private JRadioButton rd_btn_win;
    private JFormattedTextField fld_season_start;
    private JFormattedTextField fld_season_end;
    private JPanel pnl_hotel;
    private JPanel pntl_top;
    private JPanel pnl_property;
    private JPanel pnl_type;
    private JPanel pnl_season;

    private DefaultListModel<String> leftListPropertyModel;
    private DefaultListModel<String> rightListPropertyModel;

    private DefaultListModel<String> leftListTypeModel;
    private DefaultListModel<String> rightListTypeModel;

    private HotelManager hotelManager;
    private Hotel hotel;
    private PropertyManager propertyManager;
    private Property property;
    private TypeManager typeManager;
    private Types types;
    private SeasonManager seasonManager;
    private Season season;

    List<String> listRightType;

    // HotelManegementGUI yapıcı metodu
    public HotelUpdateView(Hotel hotel, Property property, Types types , Season season) {
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.propertyManager = new PropertyManager();
        this.property = property;
        this.typeManager = new TypeManager();
        this.types = types;
        this.seasonManager = new SeasonManager();
        this.season = season;
        add(container);
        this.guiInitilaze(700, 650);


        Integer[] integers = {1, 2, 3, 4, 5};
        // DefaultComboBoxModel oluşturun ve tam sayıları modele ekleyin
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>(integers);
        cmb_hotel_star.setModel(model);

        // GUI bileşenlerini oluşturma işlemleri
        hotelPropertyListComponent();
        hotelTypeListComponent();
        hotelSetHotelInfo();



        // Kaydetme butonuna ActionListener ekleme
        btn_hotel_save.addActionListener(e -> {
            boolean isHotel= false;
            boolean isProper= false;
            boolean isType= false;
            boolean isSeason= false;

            // Yeni bir Property nesnesi oluşturarak propertyNames listesini set etme
            List<String> propertyNames = Helper.getListFromJList(lst_right_pro);

            JTextField[] checkFieldList = {
                    this.fld_hotel_name,
                    this.fld_hotel_city,
                    this.fld_hotel_district,
                    this.fld_hoteml_fllAdres,
                    this.fld_hotel_mail,
                    this.fld_hotel_phone,
                    this.fld_season_start,
                    this.fld_season_end
            };
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("Otel Bilgi Kısmı Eksik"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
            }else{

                this.hotel.setHotel_name(fld_hotel_name.getText());
                this.hotel.setHotel_city(fld_hotel_city.getText());
                this.hotel.setHotel_district(fld_hotel_district.getText());
                this.hotel.setHotel_fllAdres(fld_hoteml_fllAdres.getText());
                this.hotel.setHotel_email(fld_hotel_mail.getText());
                this.hotel.setHotel_phone(fld_hotel_phone.getText());
                this.hotel.setHotel_star((Integer)cmb_hotel_star.getSelectedItem());



                if (rd_btn_sum.isSelected()) {
                    this.season.setSeasonName("Summer");
                } else if (rd_btn_win.isSelected()) {
                    this.season.setSeasonName("Winter");
                }

                if(this.hotel.getHotel_id() != 0){

                    if(Helper.isValidDate(fld_season_start.getText(),("dd-MM-yyyy")) && Helper.isValidDate(fld_season_end.getText(),("dd-MM-yyyy")))
                    {this.season.setSeason_start(LocalDate.parse(fld_season_start.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        this.season.setSeason_end(LocalDate.parse(fld_season_end.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        isSeason = this.seasonManager.update(this.season);
                    }else{
                        Helper.showMsg("Geçersiz tarih girildi"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                    }
                    isHotel = this.hotelManager.update(this.hotel);
                    this.property.setPropertyNames(propertyNames);
                    isProper = (this.propertyManager.update(this.property));

                    List<String> listRightType = Helper.getListFromJList(lst_right_type);
                    isType = this.typeManager.update(this.hotel.getHotel_id(),listRightType);
 //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                    if (isHotel){
                        Helper.showMsg("Hotel bilgi güncelleme başarılı");
                    }else {
                        Helper.showMsg("Hotel bilgi güncelleme başarısız");
                    }
                    if (isProper){
                        Helper.showMsg("Ozellık güncelleme başarılı");
                    }else{
                        Helper.showMsg("Ozellilk  güncelleme başarısız");
                    }
                    if (isType) {
                        Helper.showMsg("Type  güncelleme başarılı");
                    } else {
                        Helper.showMsg("Type  güncelleme başarısız");
                    }
                    if (isSeason) {
                        Helper.showMsg("Season  güncelleme başarılı");
                    } else {
                        Helper.showMsg("Season  güncelleme başarısız");
                    }

                }else {

                }
            }

        });

        rd_btn_sum.addActionListener(e -> {
            showSeasonDates("Summer");
        });

        rd_btn_win.addActionListener(e -> {
            showSeasonDates("Winter");
        });

    }

// Sezon türüne göre tarihleri gösteren metod
        private void showSeasonDates(String seasonType) {
            List<Season> seasons = seasonManager.getBySeasonList(season.getHotel_id());
            if (seasons != null && !seasons.isEmpty()) {
                for (Season seasonList : seasons) {
                    if (seasonType.equals(seasonList.getSeasonName())) {
                        // İlgili sezon türüne göre tarihleri ekrana yazdır
                        this.fld_season_start.setText(seasonList.getSeason_start().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        this.fld_season_end.setText(seasonList.getSeason_end().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        break; // İlgili sezon bulunduğunda döngüden çık
                    }
                }
            }
        }


     public void hotelSetHotelInfo(){
         rightListTypeModel = new DefaultListModel<>();
         rightListPropertyModel = new DefaultListModel<>();
       // if(this.hotel.getHotel_id() != 0 && this.property.getHotel_id() != 0 && this.types.getHotel_id() != 0  && this.season.getHotel_id() != 0 ){
            this.fld_hotel_name.setText(this.hotel.getHotel_name());
            this.fld_hotel_city.setText(this.hotel.getHotel_city());
            this.fld_hotel_district.setText(this.hotel.getHotel_district());
            this.fld_hoteml_fllAdres.setText(this.hotel.getHotel_fllAdres());
            this.fld_hotel_mail.setText(this.hotel.getHotel_email());
            this.fld_hotel_phone.setText(this.hotel.getHotel_phone());
            this.cmb_hotel_star.setSelectedItem(this.hotel.getHotel_star());
            this.rd_btn_sum.setSelected(true);
            this.fld_season_start.setText(this.season.getSeason_start().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            this.fld_season_end.setText(this.season.getSeason_end().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

///sag lsite ozellılkerı otel bılgısını getırıyor

         List<String> hotelPropertyList = property.getPropertyNames();
         for (String property : hotelPropertyList) {
             rightListPropertyModel.addElement(property);  // Her bir özelliği model içine ekle
         }
         lst_right_pro.setModel(rightListPropertyModel);
         // sol listedeki otel tıp bılgısnı getırıyor
            ArrayList<Object[]> hotelTypesList = typeManager.getForTable(2, types.getHotel_id());

         // ArrayList'ten alınan verileri model üzerine ekle
             for (Object[] rowObject : hotelTypesList) {
                 rightListTypeModel.addElement((String) rowObject[1]); // Sadece type_name ekleniyor
             }
             lst_right_type.setModel(rightListTypeModel);
        //}
    }


    public void hotelPropertyListComponent() {
        leftListPropertyModel = new DefaultListModel<>();
        String[] propertyArray = {"Ücretsiz Otopark",
                "Ücretsiz WiFi",
                "Yüzme Havuzu",
                "Fitness Center",
                "Hotel Concierge",
                "SPA",
                "7/24 Oda Servisi"};

        for (int i = 0; i < propertyArray.length; i++) {
            leftListPropertyModel.addElement(propertyArray[i]);
        }
        lst_left_pro.setModel(leftListPropertyModel);

        btn_single_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getLeftListValue = lst_left_pro.getSelectedValue();
                int value = lst_left_pro.getSelectedIndex();
                // getLeftListValue != null
                if (value >= 0 && !rightListPropertyModel.contains(getLeftListValue)) {
                    rightListPropertyModel.addElement(getLeftListValue);
                    lst_right_pro.setModel(rightListPropertyModel);
                } else if (getLeftListValue == null) {
                    Helper.showMsg("Özellik Seçiniz"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                } else {
                    Helper.showMsg("Listede Mevcut"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                }
            }
        });




        btn_multi_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftListPropertyModel.getSize() == rightListPropertyModel.getSize()) {
                    Helper.showMsg("Listenin tümü mevcut"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                } else {
                    // Sol listedeki tüm öğeleri al
                    List<String> allItems = new ArrayList<>();
                    for (int i = 0; i < leftListPropertyModel.getSize(); i++) {
                        allItems.add(leftListPropertyModel.getElementAt(i));
                    }
                    // Sağ listedeki mevcut öğeleri bir List'e al
                    List<String> existingItems = new ArrayList<>();
                    for (int i = 0; i < rightListPropertyModel.getSize(); i++) {
                        existingItems.add(rightListPropertyModel.getElementAt(i));

                    }
                    // Sol listedeki tüm öğeleri sağ listeye ekle (ancak sağ listede olmayanları)
                    for (String item : allItems) {
                        if (!existingItems.contains(item)) {
                            rightListPropertyModel.addElement(item);
                            lst_right_pro.setModel(rightListPropertyModel);

                        }
                    }
                }
            }
        });
        btn_multi_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Seçilen öğeleri al
                Object[] selectedValues = lst_right_pro.getSelectedValues();

                if (selectedValues != null && selectedValues.length > 0) {
                    // Tüm seçilen öğelerin, listenin boyutundan küçük olup olmadığını kontrol et
                    if (selectedValues.length < rightListPropertyModel.getSize()) {
                        // Seçilen öğeleri listeden kaldır
                        for (Object selectedValue : selectedValues) {
                            rightListPropertyModel.removeElement(selectedValue);
                        }
                    } else {
                        Helper.showMsg("Tüm özellikleri silemezsiniz. En az bir özellik olmalı."); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                    }
                } else {
                    Helper.showMsg("Özellik Seçiniz"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                }
            }

        });
    }
    public void hotelTypeListComponent() {

        leftListTypeModel = new DefaultListModel<>();

        String[] typeArray = {
                "Ultra Her Şey Dahil",
                "Her Şey Dahil",
                "Oda Kahvaltı",
                "Tam Pansiyon",
                "Yarım Pansiyon",
                "Sadece Yatak",
                "Alkol Hariç"
        };

        for (int i = 0; i < typeArray.length; i++) {
            leftListTypeModel.addElement(typeArray[i]);
        }
        lst_left_type.setModel(leftListTypeModel);

        btn_single_right_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getLeftListValue = lst_left_type.getSelectedValue();
                int value = lst_left_type.getSelectedIndex();
                // getLeftListValue != null
                if (value >= 0 && !rightListTypeModel.contains(getLeftListValue)) {
                    rightListTypeModel.addElement(getLeftListValue);
                    lst_right_type.setModel(rightListTypeModel);
                } else if (getLeftListValue == null) {
                    Helper.showMsg("Özellik Seçiniz"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                } else {
                    Helper.showMsg("Listede Mevcut"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                }
            }
        });

        btn_multi_right_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftListTypeModel.getSize() == rightListTypeModel.getSize()) {
                    Helper.showMsg("Listenin tümü mevcut"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                } else {
                    // Sol listedeki tüm öğeleri al
                    List<String> allItems = new ArrayList<>();
                    for (int i = 0; i < leftListTypeModel.getSize(); i++) {
                        allItems.add(leftListTypeModel.getElementAt(i));
                    }

                    // Sağ listedeki mevcut öğeleri bir List'e al
                    List<String> existingItems = new ArrayList<>();
                    for (int i = 0; i < rightListTypeModel.getSize(); i++) {
                        existingItems.add(rightListTypeModel.getElementAt(i));
                    }

                    // Sol listedeki tüm öğeleri sağ listeye ekle (ancak sağ listede olmayanları)
                    for (String item : allItems) {
                        if (!existingItems.contains(item)) {
                            rightListTypeModel.addElement(item);
                            lst_right_type.setModel(rightListTypeModel);
                        }
                    }
                }


            }
        });
        btn_multi_left_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Seçilen öğeleri al
                Object[] selectedValues = lst_right_type.getSelectedValues();

                if (selectedValues != null && selectedValues.length > 0) {
                    // Tüm seçilen öğelerin, listenin boyutundan küçük olup olmadığını kontrol et
                    if (selectedValues.length < rightListTypeModel.getSize()) {
                        // Seçilen öğeleri listeden kaldır
                        for (Object selectedValue : selectedValues) {
                            rightListTypeModel.removeElement(selectedValue);
                        }
                            deletTypelistMetot(selectedValues);
                    } else {
                        Helper.showMsg("Tüm özellikleri silemezsiniz. En az bir özellik olmalı."); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                    }
                } else {
                    Helper.showMsg("Özellik Seçiniz"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                }
            }



        });



    }
    public void deletTypelistMetot(Object[] selectedValues) {


        listRightType=Helper.convertObjectListToStringList(selectedValues);
        if (this.typeManager.deleteType(hotel.getHotel_id(), listRightType)) {
            Helper.showMsg("done"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
        } else {
            Helper.showMsg("error"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
        }
    }

}
