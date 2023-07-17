package com.example.finalexam_hizola;

public class AdminModelClass {

    int admin_id;
    String admin_name;
    String admin_password;

    public AdminModelClass(String admin_name, String admin_password){
        this.admin_name = admin_name;
        this.admin_password = admin_password;
    }

    public AdminModelClass(int admin_id, String admin_name, String admin_password) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
    }


    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }
}
