package view;

import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


// ReservationAddView sınıfı, rezervasyon ekleme ve güncelleme arayüzünü temsil eder ve Layout sınıfından türetilmiştir.
public class ReservationAddView extends Layout {

    // Arayüzdeki bileşenlerin tanımlandığı değişkenler.
    private JPanel container;
    private JTextField fld_reservation_hotel_name;
    private JTextField fld_reservation_address;
    private JTextField fld_reservation_star;
    private JRadioButton radio_carpark;
    private JRadioButton radio_concierge;
    private JRadioButton radio_wifi;
    private JRadioButton radio_spa;
    private JRadioButton radio_pool;
    private JRadioButton radio_room_service;
    private JRadioButton radio_fitness;
    private JTextField fld_roomType;
    private JTextField fld_pansiyonType;
    private JTextField fld_startDate;
    private JTextField fld_endDate;
    private JTextField fld_totalPrice;
    private JTextField fld_bedCapacity;
    private JTextField fld_meter;
    private JRadioButton radio_tv;
    private JRadioButton radio_gameConsole;
    private JRadioButton radio_projection;
    private JRadioButton radio_cashBox;
    private JRadioButton radio_minibar;
    private JTextField fld_guestName;
    private JTextField fld_guestPhone;
    private JTextField fld_guest_total_person;
    private JTextField fld_guestMail;
    private JButton btn_reservationSve;
    private JTextField fld_guestID;
    private JTextField fld_hotel_adult;
    private JTextField fld_hotel_child;
    private Reservation reservation;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    private Room room;
    private EmployeeView employeeView;

    // ReservationAddView sınıfının constructor'ı. Rezervasyon ve oda bilgileri ile başlatılır.
    public ReservationAddView(Reservation reservation, Room room, JFormattedTextField startDate, JFormattedTextField endDate, JTextField adult, JTextField child) {
        //Reservation reservation = convertRoomToReservation(room);
        this.room = room;
        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitilaze(1000, 700);
        this.roomManager = new RoomManager();
        String strGuestNumber;
        LocalDate from;
        LocalDate to;
        double totalPrice;

        // Rezervasyon ID'si 0 değilse (yani mevcut bir rezervasyon güncelleniyorsa), mevcut rezervasyon bilgileri kullanılır.
        if (this.reservation.getId() != 0) {
            from = this.reservation.getReservation_start_date();
            to = this.reservation.getReservation_end_date();
            strGuestNumber = String.valueOf(this.reservation.getReservation_guest_number());
            this.fld_guest_total_person.setText(strGuestNumber);
            this.fld_guestName.setText(this.reservation.getReservation_guest_name());
            this.fld_guestID.setText(String.valueOf(this.reservation.getReservation_guest_id()));
            this.fld_guestMail.setText(this.reservation.getReservation_mail());
            this.fld_guestPhone.setText(String.valueOf(this.reservation.getReservation_phone()));
            totalPrice = this.reservation.getReservation_total_price();

        } else {

            // Yeni bir rezervasyon ekleniyorsa, giriş ve çıkış tarihleri, yetişkin ve çocuk sayısı gibi bilgiler kullanılır.
            from = LocalDate.parse(startDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            to = LocalDate.parse(endDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (child.getText().isEmpty()) {
                child.setText("0");
            }
            if (adult.getText().isEmpty()) {
                adult.setText("0");
            }
            int adultCount = Integer.parseInt(adult.getText());
            int childCount = Integer.parseInt(child.getText());
            int totalGuestNumber = adultCount + childCount;
            strGuestNumber = String.valueOf(totalGuestNumber);
            this.fld_guest_total_person.setText(strGuestNumber);
            long daysBetween = ChronoUnit.DAYS.between(from, to);
            double adultPrice = room.getRoom_adult_price();
            double childPrice = room.getRoom_child_price();
            totalPrice = (adultPrice * adultCount + childPrice * childCount) * daysBetween;
        }

        // Arayüzdeki bileşenlere otel ve oda bilgileri set edilir.
        this.fld_reservation_hotel_name.setText(room.getHotel().getName());
        this.fld_reservation_address.setText(room.getHotel().getAddress());
        this.fld_reservation_star.setText(room.getHotel().getStar());
        this.fld_roomType.setText(room.getRoom_type());
        this.fld_pansiyonType.setText(room.getPencion().getPencionType());
        this.fld_startDate.setText(from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.fld_endDate.setText(to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.fld_totalPrice.setText(String.valueOf(totalPrice));
        this.fld_meter.setText(String.valueOf(room.getRoom_square_meter()));
        this.fld_bedCapacity.setText(String.valueOf(room.getRoom_bed_capacity()));

        // Otel özellikleri için radio button'lar kontrol edilir ve set edilir.
        this.radio_concierge.setSelected(room.getHotel().isConcierge());
        this.radio_carpark.setSelected(room.getHotel().isCar_park());
        this.radio_fitness.setSelected(room.getHotel().isFitness());
        this.radio_wifi.setSelected(room.getHotel().isWifi());
        this.radio_spa.setSelected(room.getHotel().isSpa());
        this.radio_pool.setSelected(room.getHotel().isPool());
        this.radio_room_service.setSelected(room.getHotel().isRoom_service());
        this.radio_tv.setSelected(room.isRoom_television());
        this.radio_gameConsole.setSelected(room.isRoom_game_console());
        this.radio_projection.setSelected(room.isRoom_projection());
        this.radio_minibar.setSelected(room.isRoom_minibar());


        // "btn_reservationSve" butonuna ActionListener eklenir.
        btn_reservationSve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Gerekli text alanları kontrol edilir ve gerekli işlemler yapılır.
                JTextField[] checkFieldList = {fld_guestName, fld_guestID, fld_guestMail, fld_guestPhone, fld_startDate, fld_endDate};
                if (Helper.isFieldListEmpty(checkFieldList)) {
                    Helper.showMsg("fill");
                } else {
                    boolean result = true;
                    reservation.setReservation_room_id(room.getRoom_id());
                    reservation.setReservation_guest_name(fld_guestName.getText());
                    reservation.setReservation_guest_id(Integer.parseInt((String.valueOf(fld_guestID.getText()))));
                    reservation.setReservation_mail(fld_guestMail.getText());
                    reservation.setReservation_phone((Integer.parseInt(String.valueOf(fld_guestPhone.getText()))));
                    reservation.setReservation_start_date(LocalDate.parse(startDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    reservation.setReservation_end_date(LocalDate.parse(endDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    reservation.setReservation_total_price((int) totalPrice);
                    reservation.setReservation_guest_number(Integer.parseInt(strGuestNumber));

                    // Rezervasyon ID'si 0 değilse (yani mevcut bir rezervasyon güncelleniyorsa), güncelleme yapılır.
                    if (reservation.getId() != 0) {
                        result = reservationManager.update(reservation);
                        dispose();
                    } else {

                        // Yeni bir rezervasyon ekleniyorsa, rezervasyon kaydedilir ve oda stoku güncellenir.
                        result = reservationManager.save(reservation);
                        room.setRoom_stock(room.getRoom_stock() - 1);
                        roomManager.updateStock(room);
                        dispose();

                    }
                    if (result) {
                        Helper.showMsg("done");


                    } else {
                        Helper.showMsg("error");
                    }

                }
            }
        });
    }

}
