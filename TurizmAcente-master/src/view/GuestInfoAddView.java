package view;

import business.ReserManager;
import core.Helper;
import entity.Reser;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static core.Helper.isFieldListEmpty;

public class GuestInfoAddView  extends Layout{
    private JPanel container;
    private JTextField fld_adult_name_1;
    private JTextField fld_adult_nationalNo_1;
    private JTextField fld_adult_country_1;
    private JPanel pnl_adult;
    private JPanel pnl_child;
    private JButton guest_save;
    private JTextField fld_adult_name_2;
    private JTextField fld_adult_name_3;
    private JTextField fld_adult_nationalNo_2;
    private JTextField fld_adult_nationalNo_3;
    private JTextField fld_adult_country_2;
    private JTextField fld_adult_country_3;
    private JTextField fld_child_name_1;
    private JTextField fld_child_name_2;
    private JTextField fld_child_name_3;
    private JTextField fld_child_nationalNo_1;
    private JTextField fld_child_nationalNo_2;
    private JTextField fld_child_nationalNo_3;
    private JTextField fld_child_country_1;
    private JTextField fld_child_country_2;
    private JTextField fld_child_country_3;
    private JLabel Child2;
    private JLabel Child1;
    private JLabel Child3;
    private JLabel Adult1;
    private JLabel Adult2;
    private JLabel Adultr3;

    private Reser reser ;

    private ReserManager reserManager;
    private int adultNum = 0;
    private int childNum = 0;
    public static List<Reser> reservations =new ArrayList<>();

    GuestInfoAddView(int adultNum,int childNum){
        add(container);
        this.guiInitilaze(600, 450);

        this.reser= new Reser();
        this.reserManager = new ReserManager();

        this.adultNum = adultNum;
        this.childNum = childNum;


        for(int i =0 ; i<= this.adultNum ;i++){
            if(i==1){
                fld_adult_name_1.setEnabled(true);
                fld_adult_nationalNo_1.setEnabled(true);
                fld_adult_country_1.setEnabled(true);
            }else if(i==2){
                fld_adult_name_2.setEnabled(true);
                fld_adult_nationalNo_2.setEnabled(true);
                fld_adult_country_2.setEnabled(true);
            }else if(i==3){
                fld_adult_name_3.setEnabled(true);
                fld_adult_nationalNo_3.setEnabled(true);
                fld_adult_country_3.setEnabled(true);
            }
        }
        for(int i =0 ; i<= this.childNum ;i++){
            if(i==1){
                fld_child_name_1.setEnabled(true);
                fld_child_nationalNo_1.setEnabled(true);
                fld_child_country_1.setEnabled(true);
            }else if(i==2){
                fld_child_name_2.setEnabled(true);
                fld_child_nationalNo_2.setEnabled(true);
                fld_child_country_2.setEnabled(true);
            }else if(i==3){
                fld_child_name_3.setEnabled(true);
                fld_child_nationalNo_3.setEnabled(true);
                fld_child_country_3.setEnabled(true);
            }
        }

        guest_save.addActionListener(e -> {

            // Her bir grup için JTextField dizilerini tanımlayalım
            JTextField[] groupA1 = {fld_adult_name_1, fld_adult_nationalNo_1, fld_adult_country_1};
            JTextField[] groupA2 = {fld_adult_name_2, fld_adult_nationalNo_2, fld_adult_country_2};
            JTextField[] groupA3 = {fld_adult_name_3, fld_adult_nationalNo_3, fld_adult_country_3};
            JTextField[] groupC1 = {fld_child_name_1, fld_child_nationalNo_1, fld_child_country_1};
            JTextField[] groupC2 = {fld_child_name_2, fld_child_nationalNo_2, fld_child_country_2};
            JTextField[] groupC3 = {fld_child_name_3, fld_child_nationalNo_3, fld_child_country_3};

            boolean groupA1Valid = checkFields(groupA1);
            boolean groupA2Valid = checkFields(groupA2);
            boolean groupA3Valid = checkFields(groupA3);
            boolean group1Valid =  checkFields(groupC1);
            boolean group2Valid =   checkFields(groupC2);
            boolean group3Valid =  checkFields(groupC3);

            if (groupA1Valid && groupA2Valid && groupA3Valid &&group1Valid && group2Valid &&group3Valid) {
                Helper.showMsg("Kontroller Başarılı");

                // Her bir grup için JTextField dizilerini tanımlayalım
                JTextField[][] groups = {
                        {fld_adult_name_1, fld_adult_nationalNo_1, fld_adult_country_1},
                        {fld_adult_name_2, fld_adult_nationalNo_2, fld_adult_country_2},
                        {fld_adult_name_3, fld_adult_nationalNo_3, fld_adult_country_3},
                        {fld_child_name_1, fld_child_nationalNo_1, fld_child_country_1},
                        {fld_child_name_2, fld_child_nationalNo_2, fld_child_country_2},
                        {fld_child_name_3, fld_child_nationalNo_3, fld_child_country_3}
                };

                // Reser nesnelerini tutacak bir liste oluşturalım
                //List<Reser> resers = new ArrayList<>();

                // Her bir grup için döngüyü kullanarak kontrol yapalım
                for (int i = 0; i < groups.length; i++) {
                    JTextField[] group = groups[i];
                    if (isFieldListEmpty(group)) {
                        continue; // Grup boş ise atla
                    }

                    // Grup dolu ise Reser nesnesi oluşturup bilgileri ekleyelim
                    Reser reser = new Reser();
                    reser.setGuestFllName(group[0].getText());
                    reser.setGuestnationalNumber(group[1].getText());
                    reser.setGuestCountry(group[2].getText());

                    // Diğer özelliklerini de burada ayarlayabilirsiniz (örn. setGuestClass)
                    if (i < 3) {
                        reser.setGuestClass("Adult"); // İlk üç grup için Adult olarak ayarla
                    } else {
                        reser.setGuestClass("Child"); // Son üç grup için Child olarak ayarla
                    }

                    // Oluşturulan Reser nesnesini listeye ekleyelim
                    reservations.add(reser);
                }

                // Listede en az bir eleman varsa kontroller başarılıdır
                if (!reservations.isEmpty()) {
                    Helper.showMsg("Liste ekleme Başarılı");

                    // Daha sonra bu listeyi istediğiniz şekilde kullanabilirsiniz
                    // Örneğin, veritabanına eklemek için saveAndGetReserId fonksiyonunu kullanabilirsiniz
                } else {
                    Helper.showMsg("Lütfen en az bir alanı doldurun.");
                }

            } else {
                Helper.showMsg("Lütfen tüm alanları doldurun.");
            }

        });
    }
    private boolean checkFields(JTextField[] fields) {
        for (JTextField field : fields) {
            if (field.isEnabled()) {
                String text = field.getText().trim();
                if (text.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

}