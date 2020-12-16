package com.realshovanshah.restroapi.user;

public class User {
    private int id;
    private String username;
    private  String password;
    private int blocked;
    private String roles = "";
    private String permissions = "";

    public User(String username, String password, int blocked, String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.blocked = 0;
        this.roles = roles;
        this.permissions = permissions;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public String getPermissions() {
        return permissions;
    }


}
