package Dao;

import core.Db;
import entity.Hotel;
import entity.Season;

import java.sql.Date;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    // Veritabanı bağlantısını temsil eden nesne
    private final Connection con;

    // Veritabanı bağlantısını kurucu metod aracılığıyla oluşturma
    public SeasonDao() {
        this.con = Db.getInstance();
    }

    // Tüm sezonları getiren metot
    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_season";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    // Belirli bir otel için sezonları getiren metot
    public ArrayList<Season> findByHotelId(int hotelId) {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_season WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    // ResultSet'ten alınan verileri bir Season nesnesine eşleyen metot
    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setSeason_id(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setSeason_strt_date(LocalDate.parse(rs.getString("start_date")));
        obj.setSeason_fnsh_date(LocalDate.parse(rs.getString("finish_date")));
        return obj;
    }

    // Belirli bir otel için yeni bir sezon ekleyen metot
    public boolean saveSeason(Hotel hotel, LocalDate strDate, LocalDate endDate) {
        String query = "INSERT INTO public.hotel_season" +
                "(hotel_id, start_date, finish_date)" +
                " VALUES ( ?, ?, ?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotel.getId());
            pr.setDate(2, Date.valueOf(strDate));
            pr.setDate(3, Date.valueOf(endDate));

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Belirli bir sezonu ID ile getiren metot
    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.hotel_season WHERE id = ?";
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
}
