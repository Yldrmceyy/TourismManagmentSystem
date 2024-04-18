package entity;

import core.ComboItem;

//Pansiyon tanımlamaları
public class Pension {
    private int pencion_id;
    private int hotel_id;
    private String pencion_type;

    // Pansiyon parametresiz metodu
    public Pension() {

    }

    //getter setterlar
    public int getPencionId() {
        return pencion_id;
    }

    public void setPencionId(int pencion_id) {
        this.pencion_id = pencion_id;
    }

    public int getHotelId() {
        return hotel_id;
    }

    public void setHotelId(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getPencionType() {
        return pencion_type;
    }

    public void setPencionType(String pencion_type) {
        this.pencion_type = pencion_type;
    }

    //toString metodu
    @Override
    public String toString() {
        return "Pencion{" +
                "pencion_id=" + pencion_id +
                ", hotel_id=" + hotel_id +
                ", pencion_type='" + pencion_type + '\'' +
                '}';
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getPencionId(), this.getPencionType());
    }
}