package business;

import dao.HotelDao;
import core.Helper;
import entity.Hotel;

import java.util.ArrayList;

//Otelleri yöneten sınıf.
public class HotelManager {
    private final HotelDao hotelDao;

    //HotelManager sınıfının yapıcı metodu.
    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    //Tüm otelleri getirir.
    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    //Belirtilen otel ID'sine ait oteli getirir.
    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    /*
     * Otelleri belirtilen boyutta bir nesne dizisi olarak getirir.
     * @param size  Nesne dizisinin boyutu
     * @param hotels Otel listesi
     * @return Belirtilen boyutta bir nesne dizisi
     */
    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels) {
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for (Hotel obj : hotels) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getAddress();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getPhone();
            rowObject[i++] = obj.getStar();
            rowObject[i++] = obj.isCar_park();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isFitness();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isRoom_service();

            hotelList.add(rowObject);
        }
        return hotelList;
    }

     /*
     * Yeni bir otel kaydı oluşturur.
     * @param hotel Otel nesnesi
     * @return Kayıt işlemi başarılıysa true, aksi takdirde false
     */


    public boolean save(Hotel hotel) {
        if (hotel.getId() != 0) {
            Helper.showMsg("error");

        }
        return this.hotelDao.save(hotel);
    }

    // Belirli bir ID'ye sahip kullanıcıyı silen metod
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            // Eğer kullanıcı ID'si bulunamazsa, silme işlemi yapılmaz
            return false;
        }
        return this.hotelDao.delete(id);
    }

}