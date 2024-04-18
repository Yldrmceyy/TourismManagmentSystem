package business;

import Dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    // Constructor: UserDao nesnesini başlatır
    public UserManager() {
        this.userDao = new UserDao();
    }

    // Kullanıcı girişi yapılırken kullanılan metod
    public User findByLogin(String username, String password) {
        // farklı işlemler yapabiliriz
        return this.userDao.findByLogin(username, password);
    }

    // Tüm kullanıcıları getiren metod
    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    // Tablo için gerekli veri yapısını oluşturan metod
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList) {
        ArrayList<Object[]> userObjList = new ArrayList<>();
        for (User obj : userList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();
            userObjList.add(rowObject);
        }
        return userObjList;
    }

    // Kullanıcı ekleyen veya güncelleyen metod
    public boolean save(User user) {
        if (user.getId() != 0) {
            // Eğer kullanıcının ID'si varsa (0'dan farklı), güncelleme işlemi yapılır
        }
        return this.userDao.save(user);
    }

    // Belirli bir ID'ye sahip kullanıcıyı getiren metod
    public User getById(int id) {
        return this.userDao.getById(id);
    }

    // Kullanıcı güncelleyen metod
    public boolean update(User user) {
        // Eğer kullanıcı ID'si bulunamazsa, güncelleme yapılmaz
        if (this.getById(user.getId()) == null) {
        }
        return this.userDao.update(user);
    }

    // Belirli bir ID'ye sahip kullanıcıyı silen metod
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            // Eğer kullanıcı ID'si bulunamazsa, silme işlemi yapılmaz
            return false;
        }
        return this.userDao.delete(id);
    }

    // Belirli bir role sahip kullanıcıları getiren metod
    public ArrayList<User> searchForTable(User.Role role) {
        String select = "SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();

        if (role != null) {
            whereList.add("user_role = '" + role.toString() + "'");
        }
        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }
        return this.userDao.selectByQuery(query);
    }
}
