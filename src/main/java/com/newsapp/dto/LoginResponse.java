package com.newsapp.dto;

public class LoginResponse {
    
    private Integer userID;
    private Integer roleID;
    private String token;
    private String message;
    
    // Constructors
    public LoginResponse() {}
    
    public LoginResponse(Integer userID, Integer roleID, String token, String message) {
        this.userID = userID;
        this.roleID = roleID;
        this.token = token;
        this.message = message;
    }
    
    // Getters and Setters
    public Integer getUserID() {
        return userID;
    }
    
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    
    public Integer getRoleID() {
        return roleID;
    }
    
    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
