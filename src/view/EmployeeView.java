package view;

import dao.HotelDao;
import dao.ReservationDao;
import dao.RoomDao;
import business.*;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private User user;
    private JTabbedPane tbp_room;
    private JButton btn_emp_logout;
    private JButton btn_add_hotel;
    private JLabel lbl_welcome;
    private JPanel pnl_hotel;
    private JPanel pnl_room;
    private JPanel pnl_reserv;
    private JPanel pnl_inside_room;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JFormattedTextField fld_hotel_strt_date;
    private JFormattedTextField fld_hotel_fnsh_date;
    private JTextField fld_hotel_adult;
    private JTextField fld_hotel_child;
    private JButton btn_room_search;
    private JButton btn_room_add;
    private JButton btn_room_reset;
    private JTable tbl_room_att;
    private JLabel lbl_hotel_name;
    private JLabel lbl_hotel_city;
    private JLabel lbl_hotel_strt_date;
    private JLabel lbl_hotel_fnsh_date;
    private JLabel lbl_hotel_adult;
    private JLabel lbl_hotel_child;
    private JTable tbl_reserv;
    private JTable tbl_hotel;
    private JTable tbl_season;
    private JTable tbl_pencion;
    private JTable tbl_reservasion;
    private JScrollPane scrl_hotel;
    private JScrollPane scrl_pension;
    private JScrollPane scrl_season;
    private JPopupMenu hotelMenu;
    private JPopupMenu roomMenu;
    private JPopupMenu reservationMenu;
    Object[] col_hotel;
    Object[] col_pencion;
    Object[] col_season;
    Object[] col_room;
    Object[] col_reservation;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_pencion = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private PensionManager pencionManager;
    private SeasonManager seasonManager;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private HotelDao hotelDao;
    private RoomDao roomDao;
    private ReservationDao reservationDao;
    private ReservationAddView reservationAddView;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    // EmployeeView sınıfının constructor metodu
    public EmployeeView(User loginUser) {

        // Bağlı sınıfları ve bileşenleri oluşturma
        this.hotelManager = new HotelManager();
        this.reservationManager = new ReservationManager();
        this.pencionManager = new PensionManager(null);
        this.seasonManager = new SeasonManager(null);
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilaze(1200, 600);
        this.user = loginUser;
        this.hotelDao = new HotelDao();
        this.roomDao = new RoomDao();
        this.reservationDao = new ReservationDao();
        loadHotelAddView(null);
        loadPencionTable(null);
        loadRoomTable(null);
        loadRoomAddComponent();
        loadReservationTable(null);
        loadReservationComponent();

        this.lbl_welcome.setText("HOŞ GELDİNİZ :  " + this.user.getUsername().toUpperCase());

        // Otel tablosunu oluşturup güncelleme
        Object[] col_hotel = {"Otel ID", "Otel Adı", "Otel Şehri", "Otel Maili", "Otel Telefonu", "Otel Yıldızı", "Otel Otoparkı", "Otel Wifi", "Otel Havuzu", "Otel Spor Salonu", "Otel Kapı Hizmeti", "Otel Spa", "Otel Oda Servisi"};
        ArrayList<Hotel> hotelList = this.hotelManager.findAll();
        tmdl_hotel.setColumnIdentifiers(col_hotel);
        for (Hotel hotel : hotelList) {
            Object[] obj = {hotel.getId(), hotel.getName(), hotel.getAddress(), hotel.getMail(), hotel.getPhone(), hotel.getStar(), hotel.isCar_park(), hotel.isWifi(), hotel.isPool(), hotel.isFitness(), hotel.isConcierge(), hotel.isSpa(), hotel.isRoom_service()};
            tmdl_hotel.addRow(obj);
        }

        this.tbl_hotel.setModel(tmdl_hotel);
        this.tbl_hotel.getTableHeader().setReorderingAllowed(false);
        this.tbl_hotel.setEnabled(false);

        this.tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        // Otel sağ tık menüsü oluşturma ve ekleme
        this.hotelMenu = new JPopupMenu();
        this.hotelMenu.add("Pansiyon Tipi Ekle").addActionListener(e -> {
            int selectedId = getTableSelectedRow(tbl_hotel, 0);
            PensionView pensionView = new PensionView(hotelManager.getById(selectedId));
            pensionView.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadPencionTable(null);

                }

            }));
            loadPencionTable(null);
        });

        // "Sezon Ekle" menü öğesini ekleyen ve ona bir ActionListener ekleyen kod bloğu
        this.hotelMenu.add("Sezon Ekle").addActionListener(e -> {

            // tbl_hotel tablosundan seçilen satırın ilk sütunundaki ID'yi alır.
            int selectedId = getTableSelectedRow(tbl_hotel, 0);

            // SezonView sınıfından bir nesne oluşturulur ve seçilen otel ID'si ile ilişkilendirilir.
            SeasonView seasonView = new SeasonView(hotelManager.getById(selectedId));

            // SezonView penceresi kapatıldığında çalışacak olan WindowAdapter sınıfını oluşturur.
            seasonView.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Pencere kapatıldığında, loadSeasonTable metodu çağrılarak tabloyu günceller.
                    loadSeasonTable(null);
                }
            }));
            // Sezon tablosunu günceller.
            loadSeasonTable(null);
        });

        // "Sil" işlevini içeren bir ActionListener eklenir.**
        hotelMenu.add("Otel Sil").addActionListener(e -> {

            // Kullanıcıya emin olup olmadığını soran bir onay penceresi gösterilir.
            if (Helper.confirm("sure")) {

                // Seçilen kullanıcının ID'si alınır.
                int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);

                // Kullanıcı ID'sine göre kullanıcı silinir.
                if (this.hotelManager.delete(selectHotelId)) {

                    // Başarılı bir şekilde silindiğinde kullanıcıya mesaj gösterilir.
                    Helper.showMsg("done");

                    loadHotelTable(null);
                    loadPencionTable(null);
                    loadSeasonTable(null);
                    loadRoomTable(null);
                    loadReservationTable(null);

                } else {

                    // Silme işlemi başarısızsa kullanıcıya hata mesajı gösterilir.
                    Helper.showMsg("error");
                }
            }
        });


        this.tbl_hotel.setComponentPopupMenu(hotelMenu);
        loadPencionTable(null);
        loadSeasonTable(null);

        // Çıkış butonuna ActionListener ekleme
        btn_emp_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        tbl_room_att.addComponentListener(new ComponentAdapter() {
        });
    }

    public void loadHotelAddView(Object o) {

        // "btn_add_hotel" butonuna bir ActionListener ekleniyor.
        btn_add_hotel.addActionListener(e -> {

            // Yeni bir HotelAddView penceresi oluşturuluyor ve null parametresi ile başlatılıyor.
            HotelAddView hotelAddView = new HotelAddView(null);

            // Yeni pencere kapatıldığında çalışacak olan WindowAdapter sınıfını oluşturuyor.
            hotelAddView.addWindowListener(new WindowAdapter() { //yeni açılan pencereyi izler
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null); //kapandıktan sonra tabloyu günceller
                }
            });
        });

    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {

        // Otel tablosunun sütun başlıkları belirleniyor.
        col_hotel = new Object[]{"Otel ID", "Otel Adı", "Otel Şehri", "Otel Maili", "Otel Telefonu", "Otel Yıldızı", "Otel Otoparkı", "Otel Wifi", "Otel Havuzu", "Otel Spor Salonu", "Otel Kapı Hizmeti", "Otel Spa", "Otel Oda Servisi"};

        // Eğer hotelList null ise, tüm otelleri içeren bir liste oluşturuluyor.
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        }

        // Tabloyu oluşturan createTable metodu çağrılıyor.
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadPencionTable(ArrayList<Object[]> pancionList) {

        // Pansiyon tablosunun sütun başlıkları belirleniyor.
        col_pencion = new Object[]{"id", "hotel_id", "pencion_type"};

        // Eğer pancionList null ise, tüm pansiyonları içeren bir liste oluşturuluyor.
        if (pancionList == null) {
            pancionList = this.pencionManager.getForTable(col_pencion.length, this.pencionManager.findAll());
        }
        // Tabloyu oluşturan createTable metodu çağrılıyor.
        createTable(this.tmdl_pencion, this.tbl_pencion, col_pencion, pancionList);

    }


    public void loadSeasonTable(ArrayList<Object[]> seasonList) {

        // Sezon tablosunun sütun başlıkları belirleniyor.
        col_season = new Object[]{"id", "hotel_id", "start_date", "finish_date"};

        // Eğer seasonList null ise, tüm sezonları içeren bir liste oluşturuluyor.
        if (seasonList == null) {
            seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        }
        // Tabloyu oluşturan createTable metodu çağrılıyor.
        createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);
    }

    public void loadRoomTable(ArrayList<Object[]> roomListe) {

        // Oda tablosunun sütun başlıkları belirleniyor.
        col_room = new Object[]{"Id", "Otel Adı", "Pansiyon", "Oda Tipi", "Stok", "Yetişkin Fiyat", "Çocuk Fiyat", "Yatak Kapasitesi", "m2", "Tv", "Minibar", "Konsol", "Projeksiyon", "Kasa"};

        // Eğer roomListe null ise, tüm odaları içeren bir liste oluşturuluyor.
        if (roomListe == null) {
            roomListe = this.roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }

        // Tabloyu oluşturan createTable metodu çağrılıyor.
        createTable(this.tmdl_room, this.tbl_room_att, col_room, roomListe);
    }

    private boolean isNumeric(String strNum) {

        // Verilen string bir sayıya dönüştürülebilir mi kontrol ediliyor.
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void loadReservationTable(ArrayList<Object[]> reservationList) {

        // Rezervasyon tablosunun sütun başlıkları belirleniyor.
        col_reservation = new Object[]{"ID", "Oda ID", "Giriş Tarihi", "Çıkış Tarihi", "Toplam Tutar", "Misafir Sayısı", "Misafir Adı", "Misafir Kimlik No", "Mail", "Telefon"};

        // Eğer reservationList null ise, tüm rezervasyonları içeren bir liste oluşturuluyor.
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(col_reservation.length, this.reservationManager.findAll());
        }

        // Tabloyu oluşturan createTable metodu çağrılıyor.
        this.createTable(this.tmdl_reservation, this.tbl_reserv, col_reservation, reservationList);
    }

    public void loadReservationComponent() {

        // Rezervasyon tablosunda fareye sağ tıklandığında seçilen satırı vurgulamak için bir MouseListener ekleniyor.
        this.tbl_reserv.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_reserv.rowAtPoint(e.getPoint());
                tbl_reserv.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        // tableRowSelect metodunu çağırarak rezervasyon tablosundaki satırları seçme işlemleri gerçekleştiriliyor.
        tableRowSelect(tbl_reserv);

        // Sağ tık menüsü (JPopupMenu) oluşturuluyor.
        this.reservationMenu = new JPopupMenu();

        // "Güncelle" öğesine tıklandığında çalışacak olan ActionListener ekleniyor.
        this.reservationMenu.add("Güncelle").addActionListener(e -> {

            // Seçilen rezervasyonun ID'si alınıyor.
            int selectReservationId = this.getTableSelectedRow(tbl_reserv, 0);

            // Seçilen rezervasyon ve odanın bilgileri alınıyor.
            Reservation selectReservation = this.reservationManager.getById(selectReservationId);
            int selectRoomId = selectReservation.getReservation_room_id();
            Room selectRoom = this.roomManager.getById(selectRoomId);

            // ReservationAddView penceresi oluşturuluyor.
            ReservationAddView reservationAddView = new ReservationAddView(
                    selectReservation,
                    selectRoom,
                    this.fld_hotel_strt_date,
                    this.fld_hotel_fnsh_date,
                    this.fld_hotel_adult,
                    this.fld_hotel_child);

            // Pencere kapatıldığında çalışacak olan WindowAdapter sınıfını ekliyor.
            reservationAddView.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    // Pencere kapatıldığında, rezervasyon tablosunu güncelleyen loadReservationTable metodu çağrılıyor.
                    loadReservationTable(null);
                }
            }));

            // Rezervasyon tablosunu güncelliyor.
            loadReservationTable(null);
        });

        this.tbl_reserv.setComponentPopupMenu(reservationMenu);
        // "Sil" öğesine tıklandığında çalışacak olan ActionListener ekleniyor.
        this.reservationMenu.add("Sil").addActionListener(e -> {

            // Kullanıcıya bir onay mesajı gösteriliyor.
            if (Helper.confirm("sure")) {

                // Seçilen rezervasyonun ID'si ve oda ID'si alınıyor.
                int selectResId = this.getTableSelectedRow(tbl_reserv, 0);
                int selectRoomId = reservationManager.getById(selectResId).getReservation_room_id();
                Room selectRoom = roomManager.getById(selectRoomId);

                // Odanın stok miktarı bir arttırılıyor.
                selectRoom.setRoom_stock(selectRoom.getRoom_stock() + 1);
                roomManager.updateStock(selectRoom);
                if (this.reservationManager.delete(selectResId)) {

                    // Rezervasyon siliniyor.
                    Helper.showMsg("done");

                    // Rezervasyon ve oda tablolarını güncelliyor.
                    loadReservationTable(null);
                    loadRoomTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        // Rezervasyon tablosuna sağ tık menüsünü ekliyor.
        this.tbl_reserv.setComponentPopupMenu(reservationMenu);

        // Rezervasyon tablosunu güncelliyor.
        loadReservationTable(null);

    }

    public void loadRoomAddComponent() {

        // Oda ekleme butonuna ActionListener ekleniyor.
        btn_room_add.addActionListener(e -> {

            // Yeni bir RoomAddView penceresi oluşturuluyor.
            RoomAddView roomAddView = new RoomAddView(null);

            // Pencere kapatıldığında çalışacak olan WindowAdapter sınıfını ekliyor.
            roomAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    // Pencere kapatıldığında, otel, oda ve rezervasyon tablolarını güncelleyen metodlar çağrılıyor.
                    loadHotelTable(null);
                    loadRoomTable(null);
                    loadReservationTable(null);
                }
            });

            // Rezervasyon tablosunu güncelliyor.
            loadReservationTable(null);
        });

        // Tablonun modeli ve bazı özellikleri ayarlanıyor.
        this.tbl_room_att.setModel(tmdl_room);
        this.tbl_room_att.getTableHeader().setReorderingAllowed(false);
        this.tbl_room_att.setEnabled(false);

        // Tabloya fare ile tıklanma olayını dinleyen MouseListener ekleniyor.
        this.tbl_room_att.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_room_att.rowAtPoint(e.getPoint());
                tbl_room_att.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        // Seçili satırı vurgulamak için tableRowSelect metodu çağrılıyor.
        tableRowSelect(tbl_room_att);

        // Sağ tık menüsü (JPopupMenu) oluşturuluyor.
        this.roomMenu = new JPopupMenu();

        // "Rezervasyon Ekle" öğesine tıklandığında çalışacak olan ActionListener ekleniyor.
        this.roomMenu.add("Rezervasyon Ekle").addActionListener(e -> {

            // Tablodan seçilen oda ID'si alınıyor.
            int selectedId = getTableSelectedRow(tbl_room_att, 0);

            ReservationAddView reservationAddView = new ReservationAddView(
                    new Reservation(),
                    roomManager.getById(selectedId),
                    this.fld_hotel_strt_date,
                    this.fld_hotel_fnsh_date,
                    this.fld_hotel_adult,
                    this.fld_hotel_child);

            // Pencere kapatıldığında çalışacak olan WindowAdapter sınıfını ekliyor.
            reservationAddView.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    // Pencere kapatıldığında, oda ve rezervasyon tablolarını güncelleyen metodlar çağrılıyor.
                    loadRoomTable(null);
                    loadReservationTable(null);

                }

            }));

            // Oda ve rezervasyon tablolarını güncelliyor.
            loadRoomTable(null);
            loadReservationTable(null);
        });

        // Tabloya sağ tık menüsünü ekliyor.
        this.tbl_room_att.setComponentPopupMenu(roomMenu);

        // Pansiyon ve sezon tablolarını güncelliyor.
        loadPencionTable(null);
        loadSeasonTable(null);

        // Oda arama butonuna ActionListener ekleniyor.
        btn_room_search.addActionListener(e -> {

            // Formdaki metin alanlarından değerler alınıyor.
            String adultPriceText = (fld_hotel_adult.getText());
            String childPriceText = (fld_hotel_child.getText());


            if (!childPriceText.isEmpty() && !adultPriceText.isEmpty()) {
                if (!isNumeric(childPriceText)) {
                    Helper.showMsg(childPriceText + " Geçerli bir sayı değil.");
                    return;
                }

                if (!isNumeric(adultPriceText)) {
                    // Eğer adultNumText bir sayı değilse, kullanıcıya bir hata mesajı göster
                    Helper.showMsg(adultPriceText + " Geçerli bir sayı değil.");
                    return; // Fonksiyonu burada sonlandır, çünkü devam etmek anlamsız olacaktır.
                }
            }


            ArrayList<Room> roomList = this.roomManager.searchForRoom(
                    // Oda arama işlemi yapılıyor.
                    fld_hotel_name.getText(),
                    fld_hotel_city.getText(),
                    fld_hotel_strt_date.getText(),
                    fld_hotel_fnsh_date.getText(),
                    fld_hotel_adult.getText(),
                    fld_hotel_child.getText()
            );
            System.out.println(roomList);
            ArrayList<Object[]> roomRow = this.roomManager.getForTable(this.col_room.length, roomList);

            // Oda tablosu güncelleniyor.
            loadRoomTable(roomRow);

            if (Helper.isValidDate(fld_hotel_strt_date.getText(), ("dd/MM/yyyy")) && Helper.isValidDate(fld_hotel_fnsh_date.getText(), ("dd/MM/yyyy"))) {
                reservationStartDate = (LocalDate.parse(fld_hotel_strt_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                reservationEndDate = (LocalDate.parse(fld_hotel_fnsh_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));


            } else {
                Helper.showMsg("Geçersiz tarih girildi");
            }
        });


        // Oda sıfırlama butonuna ActionListener ekleniyor.
        this.btn_room_reset.addActionListener(e -> {

            // Formdaki metin alanları sıfırlanıyor.
            this.fld_hotel_name.setText("");
            this.fld_hotel_strt_date.setText("");
            this.fld_hotel_fnsh_date.setText("");
            this.fld_hotel_city.setText("");
            this.fld_hotel_adult.setText("");
            this.fld_hotel_child.setText("");
            loadRoomTable(null);
        });


    }


    // JFormattedTextField bileşenleri oluşturuluyor.
    private void createUIComponents() throws ParseException {
        this.fld_hotel_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_hotel_strt_date.setText("01/01/2024");
        this.fld_hotel_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_hotel_fnsh_date.setText("01/06/2024");
    }
}

