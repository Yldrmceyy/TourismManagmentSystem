package entity;

import javax.swing.*;

public class User {
    private int Role;
    private int id;
    private String username;
    private String password;
    private String role;

    public User(String text, String text1, Object selectedItem) {
    }

    public enum Role {
        admin,
        employee
    }

    public User() {
    }

    //User(Kullanıcı) oluşturmak için kullanılan parametre verdiğim kurucu metot
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //Kullanıcı getter setterları get eden ve set eden metotlar
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //String olarak gösteren metot
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
