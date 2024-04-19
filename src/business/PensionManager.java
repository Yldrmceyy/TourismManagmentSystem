package business;

import core.Helper;
import dao.PensionDao;
import entity.Hotel;
import entity.Pension;

import java.util.ArrayList;

//Pansiyonları yöneten sınıf.
public class PensionManager {
    private final PensionDao pensionDao;
    private Hotel hotel;

    //PencionManager sınıfının yapıcı metodu.
    public PensionManager(Hotel hotel) {
        this.hotel = hotel;
        this.pensionDao = new PensionDao();
    }

    /*
    Yeni bir pansiyon kaydı oluşturur.
    * @param hotel Otel nesnesi
    * @param val   Pansiyon tipi
    * @return Kayıt işlemi başarılıysa true, aksi takdirde false
    */
    public boolean savePencion(Hotel hotel, String val) {
        if (hotel.getId() != 0) {
            // Otel ID'si 0 olmayan bir pansiyon kaydı oluşturulduğunda başarılı mesajı gösterilir.
            Helper.showMsg("done");

        }
        return this.pensionDao.savePencion(hotel, val);
    }


    /*
     * Pansiyon listesini belirtilen boyutta bir nesne dizisi olarak getirir.
     * @param size    Nesne dizisinin boyutu
     * @param pencions -->Pansiyon listesi
     * @return Belirtilen boyutta bir nesne dizisi
     */
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pencions) {
        ArrayList<Object[]> pencionList = new ArrayList<>();
        for (Pension obj : pencions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getPencionId();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getPencionType();

            pencionList.add(rowObject);
        }
        return pencionList;
    }


    /*
     * Tüm pansiyonları getirir.
     * @return Tüm pansiyonlar
     */
    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }


    /*
     * Belirtilen otel ID'sine ait pansiyonları getirir.
     * @param hotel_id Otel ID'si
     * @return Belirtilen otel ID'sine ait pansiyonlar
     */
    public ArrayList<Pension> findByHotelId(int hotel_id) {
        return this.pensionDao.findByHotelId(hotel_id);
    }
}