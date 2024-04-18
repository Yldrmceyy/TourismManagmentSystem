package view;

import business.HotelManager;
import business.PensionManager;
import entity.Hotel;

import javax.swing.*;

// PencionView sınıfı, pansiyon eklemek için kullanılan arayüzü temsil eder ve Layout sınıfından türetilmiştir.
public class PensionView extends Layout {

    // Arayüzdeki bileşenlerin tanımlandığı değişkenler.
    private JPanel container;
    private JComboBox cmb_pencion;
    private JButton btn_pencion_save;
    private JLabel lbl_hotel_id;
    private Hotel hotel;
    private HotelManager hotelManager;
    private PensionManager pencionManager;

    // PencionView sınıfının constructor'ı. Arayüz bileşenleri, otel yöneticisi ve pansiyon yöneticisi başlatılır.
    public PensionView(Hotel hotel) {
        this.pencionManager = new PensionManager(hotel);
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.add(container);
        this.guiInitilaze(400, 350);
        this.lbl_hotel_id.setText(String.valueOf(this.hotel.getId()));
        lbl_hotel_id.setText("OTEL ID : " + hotel.getId());


        // "btn_pencion_save" butonuna ActionListener ekleniyor.
        btn_pencion_save.addActionListener(e -> {

            // Seçilen pansiyon tipi alınıp ekrana yazdırılıyor.
            this.cmb_pencion.getSelectedItem().toString();
            System.out.println(this.cmb_pencion.getSelectedItem().toString());

            // Pansiyon yöneticisi aracılığıyla pansiyon kaydediliyor ve pencere kapatılıyor.
            pencionManager.savePencion(hotel, String.valueOf(cmb_pencion.getSelectedItem()));
            dispose();
        });
    }
}