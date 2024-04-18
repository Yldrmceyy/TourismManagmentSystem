package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    // Singleton tasarım deseni kullanılarak oluşturulmuş olan instance
    private static Db instance = null;
    // Veritabanı bağlantısı için kullanılan Connection nesnesi
    private Connection connection = null;
    // Veritabanı URL'si
    private final String DB_URL = "jdbc:postgresql://localhost:5432/tourism";
    // Veritabanı kullanıcı adı
    private final String DB_USERNAME = "postgres";
    // Veritabanı şifresi
    private final String DB_PASS = "postgres";

    //Database connection
    private Db() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // Veritabanı bağlantısını döndüren metod
    public Connection getConnection() {
        return this.connection;
    }

    // Singleton tasarım deseni gereği oluşturulmuş olan veya mevcut instance'ı döndüren metod
    public static Connection getInstance() {
        try {
            // Eğer instance null ise veya bağlantı kapalı ise yeni bir instance oluşturulur
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Db();
            }

        } catch (SQLException e) {
            // Bağlantı durumu kontrolünde hata olması durumunda hata mesajı yazdırılır
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }
}
