package business;

import Dao.SeasonDao;
import core.Helper;
import entity.Hotel;
import entity.Season;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SeasonManager {
    private final SeasonDao seasonDao;
    private Hotel hotel;

    // Constructor: Otel ve SeasonDao nesnelerini başlatır
    public SeasonManager(Hotel hotel) {
        this.hotel = hotel;
        this.seasonDao = new SeasonDao();
    }

    // Belirli bir otel için sezon kaydını yapar
    public boolean saveSeason(Hotel hotel, LocalDate strDate, LocalDate endDate) {
        if (hotel.getId() != 0) {
            Helper.showMsg("done");
        }
        return this.seasonDao.saveSeason(hotel, strDate, endDate);
    }

    // Tablo için uygun veri yapısını oluşturan metod
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season obj : seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getSeason_strt_date();
            rowObject[i++] = obj.getSeason_fnsh_date();

            seasonList.add(rowObject);
        }
        return seasonList;
    }

    // Tüm sezonları getiren metod
    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    // Belirli bir otel için sezonları getiren metod
    public ArrayList<Season> findByHotelId(int hotel_id) {
        return this.seasonDao.findByHotelId(hotel_id);
    }

}

