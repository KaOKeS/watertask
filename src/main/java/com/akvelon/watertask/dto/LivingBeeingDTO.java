package com.akvelon.watertask.dto;

public class LivingBeeingDTO {
    private double STOMACH_MAX_VOLUME;
    private double stomachCurrentVolume;
    private String livingBeeingType;

    public LivingBeeingDTO() {
    }

    public LivingBeeingDTO(double STOMACH_MAX_VOLUME, String livingBeeingType) {
        this.STOMACH_MAX_VOLUME = STOMACH_MAX_VOLUME;
        this.livingBeeingType = livingBeeingType;
    }

    public LivingBeeingDTO(double STOMACH_MAX_VOLUME, double stomachCurrentVolume, String livingBeeingType) {
        this.STOMACH_MAX_VOLUME = STOMACH_MAX_VOLUME;
        this.stomachCurrentVolume = stomachCurrentVolume;
        this.livingBeeingType = livingBeeingType;
    }

    public double getStomachMaximumVolume() {
        return STOMACH_MAX_VOLUME;
    }

    public void setStomachMaximumVolume(double STOMACH_MAX_VOLUME) {
        this.STOMACH_MAX_VOLUME = STOMACH_MAX_VOLUME;
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
