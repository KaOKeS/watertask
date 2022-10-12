package com.akvelon.watertask.dto;

public class LivingBeeingDTO {
    private double stomachMaxVolume;
    private double stomachCurrentVolume;
    private String livingBeeingType;

    public LivingBeeingDTO() {
    }

    public LivingBeeingDTO(double stomachMaxVolume, String livingBeeingType) {
        this.stomachMaxVolume = stomachMaxVolume;
        this.livingBeeingType = livingBeeingType;
    }

    public LivingBeeingDTO(double stomachMaxVolume, double stomachCurrentVolume, String livingBeeingType) {
        this.stomachMaxVolume = stomachMaxVolume;
        this.stomachCurrentVolume = stomachCurrentVolume;
        this.livingBeeingType = livingBeeingType;
    }

    public double getStomachMaximumVolume() {
        return stomachMaxVolume;
    }

    public void setStomachMaximumVolume(double stomachMaximumVolume) {
        this.stomachMaxVolume = stomachMaximumVolume;
    }

    public double getStomachCurrentVolume() {
        return stomachCurrentVolume;
    }

    public void setStomachCurrentVolume(double stomachCurrentVolume) {
        this.stomachCurrentVolume = stomachCurrentVolume;
    }


    public String getLivingBeeingType() {
        return livingBeeingType;
    }

    public void setLivingBeeingType(String livingBeeingType) {
        this.livingBeeingType = livingBeeingType;
    }
}
