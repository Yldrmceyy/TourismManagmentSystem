package core;

// Constructor: Öğenin anahtar ve değerini alarak bir ComboItem oluşturur
public class ComboItem {
    private int key ;
    private String value;

    public ComboItem(int key, String value) {
        // Öğenin anahtar değeri
        this.key = key;

        // Öğenin görünen değeri
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}