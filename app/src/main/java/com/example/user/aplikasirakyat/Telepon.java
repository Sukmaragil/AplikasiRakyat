package com.example.user.aplikasirakyat;

/**
 * Created by sukmaragil on 24-May-17.
 */

public class Telepon extends Movie {
    private String Name, Keterangan, Number;

    public Telepon() {

    }

    public Telepon(String Name, String Keterangan, String Number) {
        this.Name = Name;
        this.Number = Number;
        this.Keterangan = Keterangan;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.Keterangan = keterangan;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }
}
