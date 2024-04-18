package business;

import Dao.ReservationDao;
import Dao.RoomDao;
import core.Db;
import core.Helper;
import entity.*;

import java.sql.Connection;
import java.util.ArrayList;

//Rezervasyon yönetim sınıfı.
public class ReservationManager {
    private Connection con;
    private final RoomDao roomDao;
    private Hotel hotel;
    private HotelManager hotelManager;
    private Room room;
    private final ReservationDao reservationDao;
    private RoomManager roomManager;

    //ReservationManager sınıfının metodu
    public ReservationManager(){
        reservationDao = new ReservationDao();
        this.con = Db.getInstance();
        this.roomDao = new RoomDao();
        this.hotel = new Hotel();
        this.room = room;
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
    }

    //Belirli bir rezervasyonun bilgilerini getirir.
    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    //Belirtilen ID'ye sahip rezervasyonu siler.
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            return false;
        }
        return this.reservationDao.delete(id);
    }

    //Rezervasyon bilgilerini günceller.
    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getId()) == null) {
        }
        return this.reservationDao.update(reservation);
    }

    //Rezervasyon listesini belirtilen boyutta bir nesne dizisi olarak getirir.
    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations) {
        ArrayList<Object[]> reservationList = new ArrayList<>();
        for (Reservation obj : reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getReservation_room_id();
            rowObject[i++] = obj.getReservation_start_date();
            rowObject[i++] = obj.getReservation_end_date();
            rowObject[i++] = obj.getReservation_total_price();
            rowObject[i++] = obj.getReservation_guest_number();
            rowObject[i++] = obj.getReservation_guest_name();
            rowObject[i++] = obj.getReservation_guest_id();
            rowObject[i++] = obj.getReservation_mail();
            rowObject[i++] = obj.getReservation_phone();
            reservationList.add(rowObject);
        }
        return reservationList;
    }

    //Tüm rezervasyonları getirir.
    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    //Yeni bir rezervasyon kaydı oluşturur.
    public boolean save(Reservation reservation) {
        if (reservation.getId() != 0) {
            // ID değeri 0 olmayan bir rezervasyonun kaydedilmeye çalışılması hatası.
            Helper.showMsg("error");

        }
        return this.reservationDao.save(reservation);
    }
}
