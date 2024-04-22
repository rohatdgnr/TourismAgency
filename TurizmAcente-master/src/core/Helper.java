package core;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class Helper {
    public static void setTheme() {

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    public static void showMsg(String str) {
        optionPayneTR();
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz !";
                title = "Hata!";
            }
            case "done" -> {
                msg = "İşlem Başarılı !";
                title = "Sonuç";
            }
            case "notFound" -> {
                msg = "Kayıt bulunamadı !";
                title = "Bulunamadı";
            }
            case "error" -> {
                msg = "Hatalı işlem yaptınız !";
                title = "Hata!";
            }
            default -> {
                msg = str;
                title = "Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        optionPayneTR();
        String msg;
        if (str.equals("sure")) {
            msg = "Bu işlemi yapmak istediginize eminmisiniz !!";

        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Eminmisiniz", JOptionPane.YES_NO_OPTION) == 0;

    }

    public static void optionPayneTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");

    }

    //Jradio lara oda özellikleri metinleri için, oda ekleme ekranındaki
    public static String roomProperty(String number){
        String property="";
        switch (number){
            case "1":
                property = "Televizyon ";
                break;
            case "2":
                property = "Minibar ";
                break;
            case "3":
                property = "Oyun Konsolu";
                break;
            case "4":
                property = "Kasa";
                break;
            case "5":
                property = "Projeksiyon";
                break;
        }
        return property;
    }
    public static boolean isValidDate(String inputDate, String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        try {
            LocalDate date = LocalDate.parse(inputDate, formatter);
            return true; // Geçerli tarih formatı
        } catch (DateTimeParseException e) {
            return false; // Geçersiz tarih formatı
        }
    }
    public static LocalDate parseDate(String inputDate, String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        try {
            LocalDate date = LocalDate.parse(inputDate, formatter);
            return date; // Başarılı dönüşüm
        } catch (DateTimeParseException e) {
            return null; // Dönüşüm başarısız
        }
    }

    public static List<String> getListFromJList(JList<String> jList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jList.getModel().getSize(); i++) {
            list.add(jList.getModel().getElementAt(i));
        }
        return list;
    }
    // Nesne listesini String listesine dönüştüren metot
    public static List<String> convertObjectListToStringList(Object[] objectList) {
        List<String> stringList = new ArrayList<>();
        for (Object person : objectList) {
            // Her bir nesneyi String'e dönüştürerek listeye ekleyelim
            String str = person.toString(); // toString metodu çağrılır
            stringList.add(str);
        }
        return stringList;
    }
    public static boolean isList_J_Empty(JList<?> list) {
        ListModel<?> model = list.getModel();
        return model.getSize() == 0;
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();

    }
    public static boolean isAreaEmpty(JTextArea field) {
        return field.getText().trim().isEmpty();
    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }
}
