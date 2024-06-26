package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginView extends Layout {
    private JPanel container;
    private JTextField fld_user_name;
    private JPasswordField fld_password;
    private JButton btn_login;

    private final UserManager userManager;


    public LoginView() {

        this.userManager = new UserManager();


        add(container);
        this.guiInitilaze(550, 300);
        this.setVisible(true);

        btn_login.addActionListener(e -> {

            JTextField[] checkFieldList = {this.fld_user_name, this.fld_password};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_user_name.getText(), this.fld_password.getText());
                System.out.println("kulanıcı bilgisi: " + loginUser);
                if (loginUser == null) {
                    Helper.showMsg("notFound"); //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                } else {
                    if (loginUser.getRole().toString().equals("admin")) { //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                        AdminView adminView = new AdminView(loginUser);

                        dispose();

                    }
                    if (loginUser.getRole().toString().equals("employee")) { //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                        HotelView employee = new HotelView(loginUser);

                        dispose();
                    }
                }
            }

        });

    }
}
