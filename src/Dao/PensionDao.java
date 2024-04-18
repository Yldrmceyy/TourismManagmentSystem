package Dao;

import core.Db;
import entity.Hotel;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {

    // Veritabanı bağlantısını temsil eden nesne
    private final Connection con;

    // Kurucu metod: Veritabanı bağlantısını kur
    public PensionDao() {
        this.con = Db.getInstance();
    }

    // Belirli bir otel için yeni bir pansiyon tipi ekleyen metot
    public boolean savePencion(Hotel hotel, String val) {
        String query = "INSERT INTO public.hotel_pension" +
                " (hotel_id, pension_type)" +
                " VALUES ( ?, ? )";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotel.getId());
            pr.setString(2, val);

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Tüm pansiyon tiplerini getiren metot
    public ArrayList<Pension> findAll() {
        ArrayList<Pension> pencionList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_pension";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                pencionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pencionList;
    }


    // Belirli bir otel için pansiyon tiplerini getiren metot
    public ArrayList<Pension> findByHotelId(int hotelId) {
        ArrayList<Pension> pencionList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_pension WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                pencionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pencionList;
    }

    // ResultSet'ten alınan verileri bir Pencion nesnesine eşleyen metot
    public Pension match(ResultSet rs) throws SQLException {
        Pension obj = new Pension();
        obj.setPencionId(rs.getInt("id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setPencionType(rs.getString("pension_type"));
        return obj;
    }

    // Belirli bir panisyon tipi ID'si ile yemek tipini getiren metot
    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.hotel_pension WHERE id = ?";
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