package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    /*Açıklama: Kullanıcı arayüzü temasını ayarlayan metod.
    Detaylar: Nimbus temasını kullanmaktadır.*/
    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    // Kullanıcıya bilgi mesajları gösteren metod
    public static void showMsg(String str) {
        optionPaneTR();
        String msg;
        String title;
        switch (str) {
            // Tüm alanları doldurması gerektiğini belirten hata mesajı
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz.";
                title = "HATA!";
            }
            case "done" -> {
                // İşlemin başarıyla tamamlandığını belirten başarı mesajı
                msg = "İşlem Başarılı !";
                title = "Sonuç";
            }
            case "notFound" -> {
                // Kullanıcının bulunamadığını belirten hata mesajı
                msg = "Kullanıcı Bulunamadı !";
                title = "Bulunamadı";
            }
            case "error" -> {
                // Hatalı işlem yapıldığını belirten hata mesajı
                msg = "Hatalı İşlem Yaptınız!";
                title = "Hata!";
            }
            default -> {
                // Diğer durumlar: Verilen dizeyi içeren bir genel mesaj
                msg = str;
                title = "Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Kullanıcıya onay almak için bir iletişim kutusu gösteren metod
    public static boolean confirm(String str) {
        optionPaneTR();
        String msg;
        if (str.equals("sure")) {
            msg = "Bu işlemi yapmak istediğine emin misin ? ";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Emin misin ?", JOptionPane.YES_NO_OPTION) == 0;
    }

    // Verilen JTextField'in boş olup olmadığını kontrol eden metod
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    // Verilen JTextField dizisinin herhangi bir elemanının boş olup olmadığını kontrol eden metod
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    // Ekranın belirli bir boyutta pencere için merkez konumunu belirleyen metod
    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    // İletişim kutusu düğmelerinin Türkçeleştirilmiş metinlerini ayarlayan metod
    public static void optionPaneTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }
}
