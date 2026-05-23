package pbo.springboot.project.model;

import jakarta.persistence.*;

@Entity
public class Lulusan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String pekerjaan;
    private String foto;
    private Integer tahunLulus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getTahunLulus() {
        return tahunLulus;
    }

    public void setTahunLulus(Integer tahunLulus) {
        this.tahunLulus = tahunLulus;
    }
}