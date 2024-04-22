package view;

import business.ReserManager;
import core.Helper;
import entity.Reser;

import javax.swing.*;


public class RevervationUpdateView extends  Layout{

    private JPanel container;
    private JPanel pnl_reservation;
    private JTextField fld_client_name;
    private JTextField fld_check_start_date;
    private JTextField fld_check_end_date;
    private JTextField fld_client_phone;
    private JTextField fld_client_mail;
    private JTextArea txtArea_client_note;
    private JTextField fld_reser_price;
    private JTextField fld_adult_num;
    private JTextField fld_child_num;
    private JButton btn_reser_update;
    private JPanel pnl_titile;
    private JPanel pnl_update;
    private Reser reser;
    private ReserManager reserManager;

    RevervationUpdateView(Reser reserSelectId){
        this.reser = reserSelectId;
        this.reserManager = new ReserManager();

        this.guiInitilaze(600,350);
        add(container);


        fld_adult_num.setText(reser.getAdultNumb());
        fld_child_num.setText(reser.getChildNumb());
        fld_check_start_date.setText(reser.getReserCheckInDdate());
        fld_check_end_date.setText(reser.getReserCheckOutDate());
        fld_reser_price.setText(reser.getTotalPrice());



        btn_reser_update.addActionListener(e -> {
            JTextField[] checkFieldList = {
                    this.fld_client_name,
                    this.fld_client_phone,
                    this.fld_client_mail,
            };

            if (Helper.isFieldListEmpty(checkFieldList) || Helper.isAreaEmpty(txtArea_client_note)) {
                Helper.showMsg("fill");
            } else {
                this.reser.setReserFllName(fld_client_name.getText());
                this.reser.setReserPhone(fld_client_phone.getText());
                this.reser.setReserEmail(fld_client_mail.getText());
                this.reser.setReserNote(txtArea_client_note.getText());

                if(this.reser.getId() != 0) {
                    boolean isUpdate = (this.reserManager.update(this.reser));
                    if (isUpdate) {
                        Helper.showMsg("done");
                        dispose();
                    }
                    else{Helper.showMsg("error");}
                }else{
                    Helper.showMsg("ID bulunamadı");
                }
            }
        });
    }

}
