/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UCPPWS.ucppws.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author meuti
 */
@Entity
@Table(name = "bukan_manusia")
@NamedQueries({
    @NamedQuery(name = "BukanManusia.findAll", query = "SELECT b FROM BukanManusia b"),
    @NamedQuery(name = "BukanManusia.findById", query = "SELECT b FROM BukanManusia b WHERE b.id = :id"),
    @NamedQuery(name = "BukanManusia.findByNama", query = "SELECT b FROM BukanManusia b WHERE b.nama = :nama"),
    @NamedQuery(name = "BukanManusia.findByJumlah", query = "SELECT b FROM BukanManusia b WHERE b.jumlah = :jumlah")})
public class BukanManusia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Nama")
    private String nama;
    @Column(name = "Jumlah")
    private Integer jumlah;
    @Lob
    @Column(name = "Gambar")
    private byte[] gambar;

    public BukanManusia() {
    }

    public BukanManusia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BukanManusia)) {
            return false;
        }
        BukanManusia other = (BukanManusia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UCPPWS.ucppws.model.entity.BukanManusia[ id=" + id + " ]";
    }
    
}
