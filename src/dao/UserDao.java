package dao;

import core.Db;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    //Veri tabanı bağlantısını yapan nesne
    private final Connection con;

    //Kullanıcı özellikleri
    private int id;
    private String name;

    //Veritabanı bağlantısını kurucu metod aracılığıyla oluşturma
    public UserDao() {
        this.con = Db.getInstance();
    }

    // Tüm kullanıcıları getiren metot
    public ArrayList<User> findAll() {

        // Boş bir User listesi oluşturuluyor.
        ArrayList<User> userList = new ArrayList<>();
        // SQL sorgusu
        String sql = "SELECT * FROM public.user";
        try {
            // Veritabanı bağlantısından bir Statement nesnesi alınıyor ve SQL sorgusu çalıştırılıyor.
            ResultSet rs = this.con.createStatement().executeQuery(sql);

            // ResultSet üzerinde döngü ile sonuçlar okunuyor
            while (rs.next()) {

                // Her bir satır için "match" metodunu kullanarak bir User nesnesi oluşturulup userList'e ekleniyor.
                userList.add(this.match(rs));
            }
            // SQL isteği sırasında oluşan herhangi bir hata durumunda hata detayları yazdırılıyor.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Sonuç olarak, tüm kullanıcıları içeren User listesi döndürülüyor.
        return userList;
    }

    // DB user function
    // Veri tabanına bağlanıp kullanıcı var mı sorgusu ve daha sonrasında objeye atayıp döndürme
    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name =? AND user_pass =?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // seçilen id ile query oluşturan function
    public User getById(int id) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // veriyi modele çevirme
    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_pass"));
        obj.setRole(rs.getString("user_role"));
        return obj;
    }

    // Yeni bir kullanıcıyı veritabanına ekleyen metot
    public boolean save(User user) {
        String query = "INSERT INTO public.user (user_name, user_pass,user_role) VALUES(?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole());
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Var olan bir kullanıcıyı güncelleyen metot
    public boolean update(User user) {
        String query = "UPDATE public.user SET " +
                "user_name= ? , " +
                "user_pass = ? , " +
                "user_role = ? " +
                "WHERE user_id = ? ";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole());
            pr.setInt(4, user.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Belirli bir kullanıcıyı ID ile silen metot
    public boolean delete(int id) {
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Belirli bir sorguya göre kullanıcıları seçen metot
    public ArrayList<User> selectByQuery(String query) {
        ArrayList<User> userList = new ArrayList<>();

        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return userList;
    }
}
