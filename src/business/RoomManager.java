package business;

import Dao.RoomDao;
import core.Db;
import core.Helper;
import entity.Hotel;
import entity.Room;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {
    private Connection con;
    private final RoomDao roomDao;
    private Hotel hotel;
    private HotelManager hotelManager;
    private Room room;

    // Constructor: Bağlantıyı başlatır ve diğer nesneleri oluşturur
    public RoomManager() {
        this.con = Db.getInstance();
        this.roomDao = new RoomDao();
        this.hotel = new Hotel();
        this.room = room;
        this.hotelManager = new HotelManager();
    }

    // Tablo için uygun veri yapısını oluşturan metod
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room obj : rooms) {
            Object[] rowObject = new Object[size];
            int i = 0;
            // Oda özellikleri tablo için bir satır oluşturulur
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getPencion().getPencionType();
            rowObject[i++] = obj.getRoom_type();
            rowObject[i++] = obj.getRoom_stock();
            rowObject[i++] = obj.getRoom_adult_price();
            rowObject[i++] = obj.getRoom_child_price();
            rowObject[i++] = obj.getRoom_bed_capacity();
            rowObject[i++] = obj.getRoom_square_meter();
            rowObject[i++] = obj.isRoom_television();
            rowObject[i++] = obj.isRoom_minibar();
            rowObject[i++] = obj.isRoom_game_console();
            rowObject[i++] = obj.isRoom_projection();
            roomObjList.add(rowObject);
        }
        return roomObjList;
    }

    // Tüm odaları getiren metod
    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    // Oda kaydı yapma metod
    public boolean save(Room room) {
        if (room.getRoom_id() != 0) {
            Helper.showMsg("error");

        }
        return this.roomDao.save(room);
    }

    // Oda arama metod
    public ArrayList<Room> searchForRoom(String hotel_name, String hotel_address, String start_date, String finish_date, String adult_num, String child_num) {

        // SQL sorgusunu oluştur
        String query = "SELECT * FROM room r " +
                "JOIN hotel h ON r.hotel_id = h.id " +
                "LEFT JOIN hotel_season s ON r.season_id = s.id";

        // WHERE ve JOIN ON şartlarını tutacak ArrayList'ler
        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();

        // Tarih formatını uygun formata çevir
        start_date = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        finish_date = LocalDate.parse(finish_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        // Otel adı şartı ekle
        if (hotel_name != null) {
            where.add("h.name ILIKE '%" + hotel_name + "%'");
        }

        // Otelin şehirlerini şartı ekle
        if (hotel_address != null) {
            where.add("h.address ILIKE '%" + hotel_address + "%'");
        }

        // Yetişkin ve çocuk sayısına göre şartları ekle
        if (adult_num != null && !adult_num.isEmpty() && child_num != null && !child_num.isEmpty()) {
            try {
                int adultNum = Integer.parseInt(adult_num);
                int childNum = Integer.parseInt(child_num);
                int total_person = adultNum + childNum;
                where.add("r.bed_capacity >= '" + (total_person) + "'");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Sezon tarihleri şartlarını ekle
        where.add("(s.start_date <= '" + start_date + "')");
        where.add("(s.finish_date >= '" + finish_date + "')");

        // Oda stoğu kontrolü şartını ekle
        where.add("r.stock > 0");

        // WHERE şartlarını birleştir
        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        // JOIN ON şartını sorguya ekle
        if (joinStr.length() > 0) {
            query += " ON " + joinStr;
        }

        // WHERE şartını sorguya ekle
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }

        // Oluşturulan sorguyu ekrana yazdır (opsiyonel, sorguyu kontrol etmek için)
        System.out.println(query);

        // Oluşturulan sorguya göre odaları getir
        return this.roomDao.selectByQuery(query);
    }

    // Belirli bir oda ID'si ile odayı getiren metod
    public Room getById(int id) {
        return roomDao.getById(id);
    }

    // Oda stok güncelleme metod
    public boolean updateStock(Room room) {
        return this.roomDao.updateStock(room);
    }

}
