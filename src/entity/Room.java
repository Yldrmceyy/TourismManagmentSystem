package entity;

public class Room {
    //Oda ile ilişkilendirilmiş tanımlamalar
    private int room_id;
    private int hotel_id;
    private int pencion_id;
    private int season_id;
    private String room_type;
    private int room_stock;
    private int room_adult_price;
    private int room_child_price;
    private int room_bed_capacity;
    private int room_square_meter;
    private boolean room_television;
    private boolean room_minibar;
    private boolean room_game_console;
    private boolean room_projection;
    private Hotel hotel;
    private Season season;
    private Pension pencion;

    //Oda tiplerinin enum tanımlayan metot
    public enum RoomType {
        Single_room,
        Double_room,
        junior_suite_room,
        suite_room
    }

    //Parametresiz kurucu metot
    public Room() {
    }

    //Hotel getter ve setterlarının olduğu metotlar
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Pension getPencion() {
        return pencion;
    }

    public void setPencion(Pension pencion) {
        this.pencion = pencion;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getPencion_id() {
        return pencion_id;
    }

    public void setPencion_id(int pencion_id) {
        this.pencion_id = pencion_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getRoom_stock() {
        return room_stock;
    }

    public void setRoom_stock(int room_stock) {
        this.room_stock = room_stock;
    }

    public int getRoom_adult_price() {
        return room_adult_price;
    }

    public void setRoom_adult_price(int r_adult_price) {
        this.room_adult_price = r_adult_price;
    }

    public int getRoom_child_price() {
        return room_child_price;
    }

    public void setRoom_child_price(int r_child_price) {
        this.room_child_price = r_child_price;
    }

    public int getRoom_bed_capacity() {
        return room_bed_capacity;
    }

    public void setRoom_bed_capacity(int room_bed_capacity) {
        this.room_bed_capacity = room_bed_capacity;
    }

    public int getRoom_square_meter() {
        return room_square_meter;
    }

    public void setRoom_square_meter(int room_square_meter) {
        this.room_square_meter = room_square_meter;
    }

    public boolean isRoom_television() {
        return room_television;
    }

    public void setRoom_television(boolean room_television) {
        this.room_television = room_television;
    }

    public boolean isRoom_minibar() {
        return room_minibar;
    }

    public void setRoom_minibar(boolean room_minibar) {
        this.room_minibar = room_minibar;
    }

    public boolean isRoom_game_console() {
        return room_game_console;
    }

    public void setRoom_game_console(boolean room_game_console) {
        this.room_game_console = room_game_console;
    }


    public boolean isRoom_projection() {
        return room_projection;
    }

    public void setRoom_projection(boolean room_projection) {
        this.room_projection = room_projection;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", hotel_id=" + hotel_id +
                ", pencion_id=" + pencion_id +
                ", season_id=" + season_id +
                ", room_type='" + room_type + '\'' +
                ", room_stock=" + room_stock +
                ", room_adult_price=" + room_adult_price +
                ", room_child_price=" + room_child_price +
                ", room_bed_capacity=" + room_bed_capacity +
                ", room_square_meter=" + room_square_meter +
                ", room_television=" + room_television +
                ", room_minibar=" + room_minibar +
                ", room_game_console=" + room_game_console +
                ", room_projection=" + room_projection +
                '}';
    }


}
