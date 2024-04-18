package entity;

import java.time.LocalDate;
import java.util.Date;

//Rezervasyon ile ilişkili tanımlamalar
public class Reservation {
    private int id;
    private int reservation_room_id;
    private LocalDate reservation_start_date;
    private LocalDate reservation_end_date;
    private int reservation_total_price;
    private int reservation_guest_number;
    private String reservation_guest_name;
    private int reservation_guest_id;
    private String reservation_mail;
    private int reservation_phone;
    private Room room;

    //Rezervasyon parametresiz kurucu metodu
    public Reservation() {
    }

    //getter setterlar
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservation_room_id() {
        return reservation_room_id;
    }

    public void setReservation_room_id(int reservation_room_id) {
        this.reservation_room_id = reservation_room_id;
    }

    public LocalDate getReservation_start_date() {
        return reservation_start_date;
    }

    public void setReservation_start_date(LocalDate reservation_start_date) {
        this.reservation_start_date = reservation_start_date;
    }

    public LocalDate getReservation_end_date() {
        return reservation_end_date;
    }

    public void setReservation_end_date(LocalDate reservation_end_date) {
        this.reservation_end_date = reservation_end_date;
    }

    public int getReservation_total_price() {
        return reservation_total_price;
    }

    public void setReservation_total_price(int reservation_total_price) {
        this.reservation_total_price = reservation_total_price;
    }

    public int getReservation_guest_number() {
        return reservation_guest_number;
    }

    public void setReservation_guest_number(int reservation_guest_number) {
        this.reservation_guest_number = reservation_guest_number;
    }

    public String getReservation_guest_name() {
        return reservation_guest_name;
    }

    public void setReservation_guest_name(String reservation_guest_name) {
        this.reservation_guest_name = reservation_guest_name;
    }

    public int getReservation_guest_id() {
        return reservation_guest_id;
    }

    public void setReservation_guest_id(int reservation_guest_id) {
        this.reservation_guest_id = reservation_guest_id;
    }

    public String getReservation_mail() {
        return reservation_mail;
    }

    public void setReservation_mail(String reservation_mail) {
        this.reservation_mail = reservation_mail;
    }

    public int getReservation_phone() {
        return reservation_phone;
    }

    public void setReservation_phone(int reservation_phone) {
        this.reservation_phone = reservation_phone;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservation_room_id=" + reservation_room_id +
                ", reservation_start_date=" + reservation_start_date +
                ", reservation_end_date=" + reservation_end_date +
                ", reservation_total_price=" + reservation_total_price +
                ", reservation_guest_number=" + reservation_guest_number +
                ", reservation_guest_name='" + reservation_guest_name + '\'' +
                ", reservation_guest_id=" + reservation_guest_id +
                ", reservation_mail='" + reservation_mail + '\'' +
                ", reservation_phone=" + reservation_phone +
                '}';
    }
}
