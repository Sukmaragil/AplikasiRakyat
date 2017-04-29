package com.example.user.aplikasirakyat;

/**
 * Created by USER on 11/05/2016.
 */
public class ListAllPermohonanRT {

    private String jenisPelayanan, thumbnailUrl;
    private String namaDepan;
    private String nik;

    public ListAllPermohonanRT(){
    }

    public ListAllPermohonanRT(String jenisPelayanan, String thumbnailUrl, String namaDepan, String nik){
        this.jenisPelayanan  = jenisPelayanan;
        this.thumbnailUrl = thumbnailUrl;
        this.namaDepan = namaDepan;
        this.nik = nik;
    }

    public String getJenisPelayanan(){
        return jenisPelayanan;
    }

    public void setJenisPelayanan(String jenisPelayanan){
        this.jenisPelayanan = jenisPelayanan;
    }

    public String getNamaDepan(){
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan){
        this.namaDepan = namaDepan;
    }

    public String getThumbnailUrl(){
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl){
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getNik(){
        return nik = nik;
    }

    public void setNik(String nik){
        this.nik = nik;
    }
}