package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel pnl_top;
    private JTabbedPane tabbedPane1;
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_admin_logout;
    //private JComboBox cmb_role_list;
    private JComboBox<User.Role> cmb_role_list;
    private JButton btn_rol_search;
    private JButton btn_user_list_clear;
    private JTable tbl_user;
    private JPopupMenu user_Menu;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private UserManager userManager;
    private User user;

    private Object[] col_user;


    public AdminView(User user) {
        this.userManager = new UserManager();
        add(container);
        this.guiInitilaze(1000, 500);
        this.user = user;
        if (this.user == null) {
            dispose();
        }
        btn_admin_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();

            }
        });

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getRole());
        loadAdminPanelComponent();

        loadRoleFilter();
        loadRoleTable(null);
        loadAdminPanelComponent();

    }

    public void loadRoleFilter() {
        this.cmb_role_list.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_role_list.setSelectedItem(null);

    }

    public void loadRoleTable(ArrayList<Object[]> userList) {
        this.col_user = new Object[]{"ID", "K.Adı", "K.Şifre", "K.Rol"};
        if (userList == null) {
            userList = this.userManager.getForTable(col_user.length, userManager.findAll());
        }
        this.createTable(this.tmdl_user, this.tbl_user, col_user, userList);
    }

    public void loadAdminPanelComponent() {


        btn_rol_search.addActionListener(e -> {


            if (this.cmb_role_list.getSelectedItem() != null) {
                ArrayList<User> rolListBySearch = this.userManager.searcForTable((User.Role) cmb_role_list.getSelectedItem());
                ArrayList<Object[]> modelRowListBySearch = this.userManager.getForTable(this.col_user.length, rolListBySearch);
                loadRoleTable(modelRowListBySearch);
            }
        });
        this.btn_user_list_clear.addActionListener(e -> {
            this.cmb_role_list.setSelectedItem(null);
            loadRoleTable(null);
        });

        this.tableRowSelect(this.tbl_user);
        this.user_Menu = new JPopupMenu();
        this.user_Menu.add("Yeni").addActionListener(e -> {
            UserManagementView managementView = new UserManagementView(null);
            managementView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadRoleTable(null);
                }
            });
        });
        this.user_Menu.add("Güncelle").addActionListener(e -> {
            int selectUserdId = this.getTableSelectedRow(tbl_user, 0);
            UserManagementView managementView = new UserManagementView(this.userManager.getById(selectUserdId));
            managementView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadRoleTable(null);
                }
            });
        });
        this.user_Menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectBrandId)) {
                    Helper.showMsg("done");

                    loadRoleTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_user.setComponentPopupMenu(this.user_Menu);
    }
}
