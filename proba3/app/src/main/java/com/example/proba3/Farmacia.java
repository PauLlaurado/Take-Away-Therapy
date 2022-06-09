package com.example.proba3;


public class Farmacia {

    public String email,adress,password;

    public Farmacia(String email, String adress, String password) {
        this.email = email;
        this.adress = adress;
        this.password = password;
    }

    public Farmacia() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Farmacia{" +
                "email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
