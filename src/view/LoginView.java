package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JPasswordField fld_pass;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_pass;
    private final UserManager userManager;

    // login kayot ekranı
    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(400, 400);

        // "btn_login" butonuna ActionListener ekleniyor.
        btn_login.addActionListener(e -> {

            // Kontrol edilecek text alanları bir diziye ekleniyor.
            JTextField[] checkFieldList = {this.fld_username, this.fld_pass};

            // Eğer kontrol edilecek alanlardan biri boşsa, kullanıcıya bir uyarı mesajı gösterilir.
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {

                // Girilen kullanıcı adı ve şifre ile kullanıcıyı bulan bir metot çağrılır.
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_pass.getText());

                // Eğer kullanıcı bulunamazsa, kullanıcıya bir uyarı mesajı gösterilir.
                if (loginUser == null) {
                    Helper.showMsg("notFound");

                    // Eğer kullanıcı admin ise, admin ekranını oluşturup, mevcut pencereyi kapat.
                } else if (loginUser.getRole().equals("admin")) {
                    System.out.println(loginUser.toString());
                    dispose();

                    // Yeni bir AdminView penceresi oluşturulur ve kullanıcı bilgisi ile başlatılır.
                    AdminView adminView = new AdminView(loginUser);

                } else if (loginUser.getRole().equals("employee")) {

                    // Eğer kullanıcı employee ise, employee ekranını oluşturup, mevcut pencereyi kapat.

                    EmployeeView employeeView = new EmployeeView(loginUser);
                    dispose();
                    System.out.println(loginUser.toString());
                }
            }
        });
    }
}
