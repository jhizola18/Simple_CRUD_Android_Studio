package com.example.finalexam_hizola;

public class UserModelClass {
    Integer id;
    String name;
    String password;

    public UserModelClass(String name, String password){
        this.name = name;
        this.password = password;
    }

    public UserModelClass(Integer id,String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
