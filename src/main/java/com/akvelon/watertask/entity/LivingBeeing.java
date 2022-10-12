package com.akvelon.watertask.entity;

public abstract class LivingBeeing {
    private final double STOMACH_MAX_VOLUME;
    private double stomachCurrentVolume;

    public LivingBeeing(double stomachMaximumVolume) {
        if(stomachMaximumVolume<=0)
            throw new IllegalArgumentException("Stomach maximum volume should be bigger than 0");
        this.STOMACH_MAX_VOLUME = stomachMaximumVolume;
    }

    public double getStomachCurrentVolume() {
        return stomachCurrentVolume;
    }

    public void setStomachCurrentVolume(double stomachCurrentVolume) {
        if(stomachCurrentVolume<0)
            throw new IllegalArgumentException("Stomach current volume can not be less than 0");
        else if(stomachCurrentVolume>STOMACH_MAX_VOLUME)
            throw new IllegalArgumentException("Stomach current capacity can not exeed its maximum capacity");
        this.stomachCurrentVolume = stomachCurrentVolume;
    }

    public double getStomachMaximumVolume() {
        return STOMACH_MAX_VOLUME;
    }

    public boolean isStomachFull(){
        return (Math.abs(stomachCurrentVolume-STOMACH_MAX_VOLUME)<0.0001);
    }
}
