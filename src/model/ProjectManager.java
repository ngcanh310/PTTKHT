package model;

public class ProjectManager extends Member {
    private int id;

    // Constructor
    public ProjectManager(String userName, String passWord, String name, String dob, String address, String number, String role, String note, int id) {
        super(userName, passWord, name, dob, address, number, role, note);
        this.id = id;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

