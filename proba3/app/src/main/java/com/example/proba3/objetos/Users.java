package com.example.proba3.objetos;

public class Users {

    int id;
    String name, email, adress, phone, floor, door, password;

    public Users() {
    }

    public Users(int id, String name, String email, String adress, String phone, String floor, String door, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adress = adress;
        this.phone = phone;
        this.floor = floor;
        this.door = door;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", floor='" + floor + '\'' +
                ", door='" + door + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
