package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;

// RoomAddView sınıfı, yeni bir oda eklemek için kullanılan arayüzü temsil eder ve Layout sınıfından türetilmiştir.
public class RoomAddView extends Layout {

    // Arayüzdeki bileşenlerin tanımlandığı değişkenler.
    private JPanel container;
    private JButton btn_room_add;
    private JComboBox cmb_hotel_add;
    private JComboBox cmb_pencion_add;
    private JComboBox cmb_season_add;
    private JComboBox cmb_room_type_add;
    private JTextField fld_stock_add;
    private JTextField fld_square_meter_add;
    private JTextField fld_bed_capacity_add;
    private JRadioButton rdb_tv_add;
    private JRadioButton rdb_minibar_add;
    private JRadioButton rdb_game_console_add;
    private JRadioButton rdb_case_box_add;
    private JRadioButton rdb_projection_add;
    private JLabel fld_oda;
    private JTextField fld_adult_add;
    private JTextField fld_child_add;
    private JPanel pnl_room_add;
    private JPanel pnl_add_room_right;
    private JPanel pnl_add_room_left;
    private RoomAddView roomAddView;
    private RoomManager roomManager;
    private Room room;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pencionManager;

    // RoomAddView sınıfının constructor'ı.
    public RoomAddView(Object o) {
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager(null);
        this.pencionManager = new PensionManager(null);
        this.hotelManager = new HotelManager();
        this.room = room;
        this.add(container);
        this.guiInitilaze(1000, 600);
        this.cmb_room_type_add.setModel(new DefaultComboBoxModel<>(Room.RoomType.values()));

        // Otel ComboBox'ı için otel bilgilerini doldur.
        int counter = 0;
        for (Hotel hotel : this.hotelManager.findAll()) {
            if (counter == 0) {
                getPencionByHotel(hotel.getId());
                getSeasonByHotel(hotel.getId());
            }
            cmb_hotel_add.addItem(hotel.getComboItem());
            counter++;
        }

        // "btn_room_add" butonuna ActionListener eklenir.
        btn_room_add.addActionListener(e -> {

            // Gerekli text alanları kontrol edilir ve gerekli işlemler yapılır.
            JTextField[] checkFieldList = {this.fld_stock_add, this.fld_adult_add, this.fld_child_add, this.fld_bed_capacity_add, this.fld_square_meter_add};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                boolean result = true;
                Room roomNew = new Room();

                // Seçilen otel bilgisi alınır.
                ComboItem selectedOtelInfo = (ComboItem) cmb_hotel_add.getSelectedItem();
                roomNew.setHotel_id(selectedOtelInfo.getKey());

                // Seçilen sezon bilgisi alınır.
                ComboItem selectedSeasonInfo = (ComboItem) cmb_season_add.getSelectedItem();
                roomNew.setSeason_id(selectedSeasonInfo.getKey());

                // Seçilen pansiyon bilgisi alınır.
                ComboItem selectedPensionInfo = (ComboItem) cmb_pencion_add.getSelectedItem();
                roomNew.setPencion_id(selectedPensionInfo.getKey());

                // Diğer bilgiler alınır ve setlemeler
                roomNew.setRoom_stock(Integer.parseInt(fld_stock_add.getText()));
                roomNew.setRoom_adult_price(Integer.parseInt(fld_adult_add.getText()));
                roomNew.setRoom_child_price(Integer.parseInt(fld_child_add.getText()));
                roomNew.setRoom_bed_capacity(Integer.parseInt(fld_bed_capacity_add.getText()));
                roomNew.setRoom_square_meter(Integer.parseInt(fld_square_meter_add.getText()));
                roomNew.setRoom_type(String.valueOf((Room.RoomType) cmb_room_type_add.getSelectedItem()));
                roomNew.setRoom_television(rdb_tv_add.isSelected());
                roomNew.setRoom_minibar(rdb_minibar_add.isSelected());
                roomNew.setRoom_game_console(rdb_game_console_add.isSelected());
                roomNew.setRoom_projection(rdb_projection_add.isSelected());


                // Yeni bir oda ekleniyorsa, odanın bilgileri kaydedilir.
                if (roomNew.getRoom_id() == 0) {
                    result = this.roomManager.save(roomNew);
                    dispose();

                } else {

                }

                if (result) {
                    Helper.showMsg("done");


                } else {
                    Helper.showMsg("error");
                }

            }
        });


        // Otel ComboBox'ında bir değişiklik olduğunda çalışacak ActionListener.
        cmb_hotel_add.addActionListener(e -> {
            ComboItem item = (ComboItem) cmb_hotel_add.getSelectedItem();

            // Seçilen otel bilgisine göre pansiyon ve sezon bilgileri güncellenir.
            getPencionByHotel(item.getKey());
            getSeasonByHotel(item.getKey());

        });
    }

    // Seçilen otel bilgisine göre pansiyonları ComboBox'a ekler.
    private void getPencionByHotel(int hotel_id) {
        for (Pension pencion : this.pencionManager.findByHotelId(hotel_id)) {
            cmb_pencion_add.addItem((pencion.getComboItem()));
        }
    }

    // Seçilen otel bilgisine göre sezonları ComboBox'a ekler.
    private void getSeasonByHotel(int hotel_id) {
        for (Season season : this.seasonManager.findByHotelId(hotel_id)) {
            cmb_season_add.addItem((season.getComboItem()));
        }
    }

}

