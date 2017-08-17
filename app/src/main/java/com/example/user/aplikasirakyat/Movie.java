package com.example.user.aplikasirakyat;

/**
 * Created by sukmaragil on 12-May-17.
 */

public class Movie {
    private String title, progress, numb, name, keterangan;
    private int status;
    public Movie() {
    }

    public Movie(String title, String name, String numb, String progress, String keterangan, int status) {
        this.title = title;
        this.name = name;
        this.progress = progress;
        this.numb = numb;
        this.keterangan = keterangan;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() { return status;}

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        this.numb = numb;
    }

    public String getKeterangan() { return keterangan;}

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
