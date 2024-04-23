package view;


import business.UserManager;
import core.Helper;
import entity.User;
import javax.swing.*;


public class UserManagementView extends Layout {
    private JPanel container;
    private JTextField fld_mng_name;
    private JTextField fld_mng_pass;
    private JComboBox<User.Role> cmb_mng_role;
    private JButton btn_mng_save;

    private User user;
    private UserManager userManager;

    /**
     * Yapılandırıcı metot.
     * Constructor method.
     * @param user Giriş yapan kullanıcı bilgisi
     *             Information of the logged-in user
     */
    public UserManagementView(User user) {
        this.userManager = new UserManager();
        this.user = user;

        add(container);
        this.guiInitilaze(400, 400);

        // Eğer kullanıcı varsa, bilgileri doldur
        // If there is a user, fill the information
        if (user != null) {
            fld_mng_name.setText(user.getUsername());
            fld_mng_pass.setText(user.getPassword());
            this.cmb_mng_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        }
        // Kullanıcı rollerini açılır menüye ekle
        // Add user roles to the combo box
        this.cmb_mng_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        // Kaydet butonuna tıklandığında
        // When the save button is clicked
        btn_mng_save.addActionListener(e -> {
            // Alanlar boş mu diye kontrol et
            // Check if the fields are empty
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_mng_name, this.fld_mng_pass})) {
                Helper.showMsg("fill");  //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
            } else {
                boolean result = false;
                if (this.user != null) {
                    this.user.setUsername(fld_mng_name.getText());
                    this.user.setPassword(fld_mng_pass.getText());
                    this.user.setRole((User.Role) cmb_mng_role.getSelectedItem());
                    result = this.userManager.update(this.user);
                } else {
                    //result=this.userManager.save(this.user);
                    User obj = new User(fld_mng_name.getText(), fld_mng_pass.getText(), (User.Role) cmb_mng_role.getSelectedItem());
                    result = this.userManager.save(obj);
                }
                if (result) {
                    Helper.showMsg("done");  //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                    dispose();
                } else {
                    Helper.showMsg("error");  //Değerlendirme Formu 24-25 Kullanıcıya başarılı işlemler için uygun pop up mesajları veriliyor ve kullanıcıya hatalı işlemler için uygun hata mesajları veriliyor
                }
            }
        });
    }
}