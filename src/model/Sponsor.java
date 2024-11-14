package model;

public class Sponsor {
    private int id;
    private String name;
    private String address;
    private String rank;
    private String note;

    // Constructor
    public Sponsor(int id, String name, String address, String rank, String note) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rank = rank;
        this.note = note;
    }

    // Getters and Setters
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
