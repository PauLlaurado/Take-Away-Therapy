package com.example.proba3;

import android.graphics.Bitmap;

public class Infermeros {

    String name,email,password,price,desc;
    Bitmap bitmap;

    public Infermeros(String name, String email, String password, String price) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.price = price;
    }

    public Infermeros(String name, String price, String desc) {
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public Infermeros() {
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Infermeros{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
