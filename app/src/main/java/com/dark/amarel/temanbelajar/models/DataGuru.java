package com.dark.amarel.temanbelajar.models;

public class DataGuru {

    private String id;
    private String nama;
    private String pendidikan;
    private String nama_mapel;
    private String deskripsi;
    private String pengalaman;
    private String prestasi;
    private String foto_profil;
    private String telpon;
    private String id_mengajar;
    private String tarif;

    public DataGuru() {
    }

    public DataGuru(String id, String nama, String pendidikan, String nama_mapel, String deskripsi, String pengalaman, String prestasi, String foto_profil, String telpon, String id_mengajar, String tarif) {
        this.id = id;
        this.nama = nama;
        this.pendidikan = pendidikan;
        this.nama_mapel = nama_mapel;
        this.deskripsi = deskripsi;
        this.pengalaman = pengalaman;
        this.prestasi = prestasi;
        this.foto_profil = foto_profil;
        this.telpon = telpon;
        this.id_mengajar = id_mengajar;
        this.tarif = tarif;
    }


    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public String getNama_mapel() {
        return nama_mapel;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getPengalaman() {
        return pengalaman;
    }

    public String getPrestasi() {
        return prestasi;
    }

    public String getFoto_profil() {
        return foto_profil;
    }

    public String getTelpon() {
        return telpon;
    }

    public String getId_mengajar() {
        return id_mengajar;
    }

    public String getTarif() {
        return tarif;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public void setNama_mapel(String nama_mapel) {
        this.nama_mapel = nama_mapel;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setPengalaman(String pengalaman) {
        this.pengalaman = pengalaman;
    }

    public void setPrestasi(String prestasi) {
        this.prestasi = prestasi;
    }

    public void setFoto_profil(String foto_profil) {
        this.foto_profil = foto_profil;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public void setId_mengajar(String id_mengajar) {
        this.id_mengajar = id_mengajar;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }
}
