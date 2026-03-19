package com.example.hospitalservicerequestmobileapplication;

public class Request {
    private int id;
    private int userId;
    private int serviceId;
    private String notes;
    private String wardNumber;
    private String bedNumber;
    private String status;
    private String timestamp;

    public Request() {}

    // Constructor with 7 parameters (no id)
    public Request(int userId, int serviceId, String notes, String wardNumber, String bedNumber, String status, String timestamp) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.notes = notes;
        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Constructor with 8 parameters (including id)
    public Request(int id, int userId, int serviceId, String notes, String wardNumber, String bedNumber, String status, String timestamp) {
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
        this.notes = notes;
        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getServiceId() { return serviceId; }
    public void setServiceId(int serviceId) { this.serviceId = serviceId; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getWardNumber() { return wardNumber; }
    public void setWardNumber(String wardNumber) { this.wardNumber = wardNumber; }

    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
