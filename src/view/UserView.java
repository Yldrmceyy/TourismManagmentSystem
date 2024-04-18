package view;

import business.HotelManager;
import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class UserView extends Layout {
    private JPanel container;
    private JLabel lbl_user;
    private JLabel lbl_user_name;
    private JTextField fld_user_name;
    private JPasswordField fld_user_pass;
    private JComboBox<User.Role> cmb_user_role;
    private JButton btn_userSave;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user;

    // UserView sınıfının constructor'ı.
    public UserView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(300, 300);

        // Eğer user null ise, yeni bir User oluşturulur.
        if (user == null) {
            this.user = new User();
        } else {
            this.user = user;
        }

        // Eğer user varsa, mevcut kullanıcı bilgileri görüntülenir.
        if (user != null) {
            this.fld_user_name.setText(user.getUsername());
            this.fld_user_pass.setText(user.getPassword());
            this.cmb_user_role.setSelectedItem(user.getRole());
        }

        // ComboBox'a kullanıcı rollerini ekler.
        this.cmb_user_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));

        // "btn_userSave" butonuna ActionListener eklenir.
        btn_userSave.addActionListener(e -> {

            // Kullanıcı adı veya şifre alanı boş ise uyarı mesajı gösterilir.
            if (Helper.isFieldEmpty(this.fld_user_name) || (Helper.isFieldEmpty(this.fld_user_pass))) {
                Helper.showMsg("fill");
            } else {
                boolean result = true;

                // Kullanıcı bilgileri alınır.
                this.user.setUsername(fld_user_name.getText());
                this.user.setPassword(fld_user_pass.getText());
                this.user.setRole(String.valueOf(cmb_user_role.getSelectedItem()));

                if (this.user.getId() != 0) {
                    // Kullanıcı daha önce kaydedilmişse güncellenir, değilse yeni kayıt yapılır.
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.save(this.user);
                }
                if (result) {

                    // İşlem sonucuna göre mesaj gösterilir ve pencere kapatılır.
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}