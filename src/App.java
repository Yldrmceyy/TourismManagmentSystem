import business.UserManager;
import core.Db;
import core.Helper;
import entity.User;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;
import view.UserView;

import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {
        // Uygulama temasını ayarlamak için Helper.setTheme() fonksiyonu kullanılır.
        Helper.setTheme();

        // LoginView sınıfından bir örnek oluşturulur.
        LoginView loginView = new LoginView();

        // Kullıcı girişi için kullanlır
        //EmployeeView employeeView = new EmployeeView(new User());

    }
}
