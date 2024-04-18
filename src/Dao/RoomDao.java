package Dao;

import core.Db;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    // Veritabanı bağlantısını temsil eden nesne
    private final Connection con;

    // İlişkili DAO sınıfları
    private PensionDao pencionDao;
    private SeasonDao seasonDao;
    private HotelDao hotelDao;


    public RoomDao() {
        // Kurucu metod: DAO sınıflarını oluştur ve veritabanı bağlantısını kur
        this.hotelDao = new HotelDao();
        this.pencionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
        this.con = Db.getInstance();
    }


    // ResultSet'ten alınan verileri bir Room nesnesine eşleyen metot
    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setRoom_id(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPencion_id(rs.getInt("pension_id"));
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setRoom_type(rs.getString("type"));
        obj.setRoom_stock(rs.getInt("stock"));
        obj.setRoom_adult_price(rs.getInt("adult_price"));
        obj.setRoom_child_price(rs.getInt("child_price"));
        obj.setRoom_bed_capacity(rs.getInt("bed_capacity"));
        obj.setRoom_square_meter(rs.getInt("square_meter"));
        obj.setRoom_television(rs.getBoolean("television"));
        obj.setRoom_minibar(rs.getBoolean("minibar"));
        obj.setRoom_game_console(rs.getBoolean("game_console"));
        obj.setRoom_projection(rs.getBoolean("projection"));

        // İlişkili DAO sınıflarını kullanarak ilgili nesneleri set et
        obj.setPencion(this.pencionDao.getById(rs.getInt("pension_id")));
        obj.setSeason(this.seasonDao.getById(rs.getInt("season_id")));
        obj.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        return obj;
    }

    // Yeni bir oda ekleyen metot
    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id, " +
                "pension_id, " +
                "season_id, " +
                "type, " +
                "stock, " +
                "adult_price, " +
                "child_price, " +
                "bed_capacity, " +
                "square_meter, " +
                "television, " +
                "minibar, " +
                "game_console, " +
                "projection " +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPencion_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getRoom_type());
            pr.setInt(5, room.getRoom_stock());
            pr.setInt(6, room.getRoom_adult_price());
            pr.setInt(7, room.getRoom_child_price());
            pr.setInt(8, room.getRoom_bed_capacity());
            pr.setInt(9, room.getRoom_square_meter());
            pr.setBoolean(10, room.isRoom_television());
            pr.setBoolean(11, room.isRoom_minibar());
            pr.setBoolean(12, room.isRoom_game_console());
            pr.setBoolean(13, room.isRoom_projection());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Tüm odaları getiren metot
    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    // Belirli bir sorguya göre odaları getiren metot
    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return roomList;
    }

    // Belirli bir oda ID'si ile odayı getiren metot
    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ?";
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

    // Oda stok miktarını güncelleyen metot
    public boolean updateStock(Room room){
        String query = "UPDATE public.room SET stock = ? WHERE id = ?";
        try {PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,room.getRoom_stock());
            pr.setInt(2,room.getRoom_id());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
