package com.akvelon.watertask.dto;

public class CreateContainerFormDTO {
    private double capacity;
    private String type;

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
