package com.leper182.fitnesskit.data;

public class DayOfWeek {
    private int id;
    private String name;

    public DayOfWeek(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
