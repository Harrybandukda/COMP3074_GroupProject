package com.example.comp3074_groupproject.database;

public class User {
    private int id;
    private String name;
    private String profilePic;
    private int heightFt;
    private int heightIn;
    private long weight;
    private String activityLvl;
    private int activityLvlPosition;
    private int yearJoined;

    public User(int id,
                String name,
                int heightFt,
                int heightIn,
                long weight,
                String activityLvl,
                int activityLvlPosition,
                int yearJoined) {
        this.id = id;
        this.activityLvlPosition = activityLvlPosition;
        this.activityLvl = activityLvl;
        this.weight = weight;
        this.heightFt = heightFt;
        this.heightIn = heightIn;
        this.name = name;
        this.yearJoined = yearJoined;
    }

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

    public int getHeightFt() {
        return heightFt;
    }

    public int getHeightIn() {
        return heightIn;
    }

    public String getWeight() {
        return Long.toString(weight);
    }

    public int getActivityLvlPosition() {
        return activityLvlPosition;
    }

    public int getYearJoined() {
        return yearJoined;
    }

    public String getActivityLvl() {
        return activityLvl;
    }

}
