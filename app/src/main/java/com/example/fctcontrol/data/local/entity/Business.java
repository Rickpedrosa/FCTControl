package com.example.fctcontrol.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "business",
        indices = {@Index(value = {"name"},
                unique = true)})
public class Business {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "cif")
    private String cif;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "headquarters")
    private String headquarters;

    @ColumnInfo(name = "phone")
    private int phone;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "logo")
    private String url_logo;

    @ColumnInfo(name = "contact")
    private String contact;

    public Business(long id, String name, String cif, String address,
                    String headquarters, int phone, String email,
                    String url_logo, String contact) {
        this.id = id;
        this.name = name;
        this.cif = cif;
        this.address = address;
        this.headquarters = headquarters;
        this.phone = phone;
        this.email = email;
        this.url_logo = url_logo;
        this.contact = contact;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl_logo() {
        return url_logo;
    }

    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
