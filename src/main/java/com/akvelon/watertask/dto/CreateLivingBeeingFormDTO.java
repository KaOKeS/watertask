package com.akvelon.watertask.dto;

public class CreateLivingBeeingFormDTO {
    private String livingBeeing;
    private Double stomachCapacity;

    public String getLivingBeeing() {
        return livingBeeing;
    }

    public void setLivingBeeing(String livingBeeing) {
        this.livingBeeing = livingBeeing;
    }

    public Double getStomachCapacity() {
        return stomachCapacity;
    }

    public void setStomachCapacity(Double stomachCapacity) {
        this.stomachCapacity = stomachCapacity;
    }

    @Override
    public String toString() {
        return "CreateLivingBeeingFormDTO{" +
                "livingBeeing='" + livingBeeing + '\'' +
                ", stomachCapacity=" + stomachCapacity +
                '}';
    }
}
