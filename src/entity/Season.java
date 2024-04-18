package entity;

import core.ComboItem;

import java.time.LocalDate;

public class Season {
    private int season_id;
    private int hotel_id;
    private LocalDate season_strt_date;
    private LocalDate season_fnsh_date;


    public Season() {
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public LocalDate getSeason_strt_date() {
        return season_strt_date;
    }

    public void setSeason_strt_date(LocalDate season_strt_date) {
        this.season_strt_date = season_strt_date;
    }

    public LocalDate getSeason_fnsh_date() {
        return season_fnsh_date;
    }

    public void setSeason_fnsh_date(LocalDate season_fnsh_date) {
        this.season_fnsh_date = season_fnsh_date;
    }

    //Sezon nesnesinin toString metodu
    @Override
    public String toString() {
        return "Season{" +
                "season_id=" + season_id +
                ", hotel_id=" + hotel_id +
                ", season_strt_date='" + season_strt_date + '\'' +
                ", season_fnsh_date='" + season_fnsh_date + '\'' +
                '}';
    }

    //ComboItem döndüren metot
    public ComboItem getComboItem() {
        return new ComboItem(this.getSeason_id(), this.getSeason_strt_date() + " - " + this.getSeason_fnsh_date());
    }
}
