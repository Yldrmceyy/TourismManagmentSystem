package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminView extends Layout {
    private User user;
    private JPanel container;
    private JButton btn_logout;
    private JComboBox<User.Role> cmb_user_search;
    private JButton btn_save;
    private JButton btn_search;
    private JLabel lbl_welcome;
    private JTable tbl_user;
    private JButton btn_clear;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private UserManager userManager;
    private JPopupMenu userMenu;
    private Object[] col_user;

    // AdminView sınıfının constructor metodu
    public AdminView(User loggedInUser) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(700, 500);
        this.user = loggedInUser;
        if (loggedInUser == null) {
            dispose(); // Pencereyi kapatma
        }
        this.lbl_welcome.setText("HOŞ GELDİNİZ :  " + this.user.getUsername().toUpperCase());

        /*
        Kullanıcı tablosunu yükleme
        Kullanıcı bileşenlerini yükleme
        Kullanıcı filtresini yükleme
        */

        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();

        this.tbl_user.setComponentPopupMenu(userMenu);

        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.exit(0);
                dispose();
                LoginView loginView=new LoginView();
            }
        });

        // Kullanıcı temizle butonu için ActionListener ekleme
        btn_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmb_user_search.setSelectedItem(null);
                loadUserTable(null);
            }
        });


        // Kullanıcı arama butonu için ActionListener ekleme
        this.btn_search.addActionListener(e -> {
            ArrayList<User> userListBySearch = this.userManager.searchForTable((User.Role) cmb_user_search.getSelectedItem());
            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(col_user.length, userListBySearch);
            loadUserTable(userRowListBySearch);
        });

    }



    // Kullanıcı tablosunu yükleme metodu
    public void loadUserTable(ArrayList<Object[]> usersList) {
        this.col_user = new Object[]{"Kullanıcı ID", "Kullanıcı Adı", "Parola", "Kullanıcı Rolü"};
        if (usersList == null) {
            usersList = this.userManager.getForTable(col_user.length, userManager.findAll());
        }
        this.createTable(this.tmdl_user, tbl_user, col_user, usersList);
    }




    // Kullanıcı bileşenlerini yükleme metodu
    public void loadUserComponent() {
        //Tabloda tıklanan satırın seçilmesi
        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //int selected_row = tbl_user.rowAtPoint(e.getPoint());
                //tbl_user.setRowSelectionInterval(selected_row, selected_row);
                tableRowSelect(tbl_user);
            }
        });

        // ekleme,güncelle,sil butonları ekleme
        this.userMenu = new JPopupMenu();

        // ekleme
        this.userMenu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(null);
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });

        // seçili olan userid yi databaseden alıp buraya gönderme
        userMenu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });


        // "Sil" işlevini içeren bir ActionListener eklenir.
        userMenu.add("Sil").addActionListener(e -> {

            // Kullanıcıya emin olup olmadığını soran bir onay penceresi gösterilir.
            if (Helper.confirm("sure")) {

                // Seçilen kullanıcının ID'si alınır.
                int selectUserId = this.getTableSelectedRow(tbl_user, 0);

                // Kullanıcı ID'sine göre kullanıcı silinir.
                if (this.userManager.delete(selectUserId)) {

                    // Başarılı bir şekilde silindiğinde kullanıcıya mesaj gösterilir.
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {

                    // Silme işlemi başarısızsa kullanıcıya hata mesajı gösterilir.
                    Helper.showMsg("error");
                }
            }
        });


    }

    // Kullanıcı filtresini yükleme metodu
    public void loadUserFilter() {
        this.cmb_user_search.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_user_search.setSelectedItem(null);
    }
}
