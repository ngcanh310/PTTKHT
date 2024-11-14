package model;

public class Member {
    private String userName;
    private String passWord;
    private String name;
    private String dob;
    private String address;
    private String number;
    private String role;
    private String note;

    // Constructor
    public Member(String userName, String passWord, String name, String dob, String address, String number, String role, String note) {
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.number = number;
        this.role = role;
        this.note = note;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

