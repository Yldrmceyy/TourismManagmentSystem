package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {
    // Gui oluşturma
    public void guiInitilaze(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Turizm Acentası");
        this.setSize(width, height);

        // Pencereyi ekranın ortasına yerleştirme
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        this.setVisible(true);
    }

    // JTable için tablo oluşturan metot
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {

        // Sütun başlıklarını ayarla
        model.setColumnIdentifiers(columns);

        // Modeli tabloya ekle
        table.setModel(model);

        // Sütunların yer değiştirmesini engelle
        table.getTableHeader().setReorderingAllowed(false);

        // İlk sütunun genişliğini maksimuma ayarla (örneğin, ID sütunu)
        table.getColumnModel().getColumn(0).setMaxWidth(75);

        // Tabloyu düzenlenemez hale getir
        table.setEnabled(false);

        // Modeli temizle ve yeni satırları ekle,  tabloya yeni veriler ekleneceği veya tablonun güncelleneceği durumlarda
        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        // Eğer satır listesi null ise, boş bir liste oluştur
        if (rows == null) {
            rows = new ArrayList<>();
        }

        // Satırları modeldeki tabloya ekle
        for (Object[] row : rows) {
            model.addRow(row);
        }
    }

    // Tablodan seçilen satırın belirli bir indeksteki değerini getiren metot
    public int getTableSelectedRow(JTable table, int index) {
        //return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());
        return (int) table.getValueAt(table.getSelectedRow(), index);

    }

    // Tabloya tıklandığında satırı seçen metot
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }
}
