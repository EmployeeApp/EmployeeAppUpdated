package com.example.itprototypezero;

public class AssignedTaskList {
    private String name;
    private String dateTime;
    private int thumbnail;

    public AssignedTaskList(String name, String dateTime, int thumbnail) {
        this.name = name;
        this.dateTime = dateTime;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getDateTime() { return dateTime; }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setName(String name){ this.name = name; };

    public void setDateTime(String dateTime){ this.dateTime = dateTime; };

    public void setThumbnail(int thumbnail){ this.thumbnail = thumbnail; };
}
