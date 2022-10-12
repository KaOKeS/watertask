package com.akvelon.watertask.entity;

public abstract class LivingBeeing {
    private final double stomachMaxVolume;
    private double stomachCurrentVolume;

    protected LivingBeeing(double stomachMaximumVolume) {
        if(stomachMaximumVolume<=0)
            throw new IllegalArgumentException("Stomach maximum volume should be bigger than 0");
        this.stomachMaxVolume = stomachMaximumVolume;
    }

    protected LivingBeeing(double stomachMaxVolume, double stomachCurrentVolume) {
        this.stomachMaxVolume = stomachMaxVolume;
        this.stomachCurrentVolume = stomachCurrentVolume;
    }

    public double getStomachCurrentVolume() {
        return stomachCurrentVolume;
    }

    public void setStomachCurrentVolume(double stomachCurrentVolume) {
        if(stomachCurrentVolume<0)
            throw new IllegalArgumentException("Stomach current volume can not be less than 0");
        else if(stomachCurrentVolume> stomachMaxVolume)
            throw new IllegalArgumentException("Stomach current capacity can not exeed its maximum capacity");
        this.stomachCurrentVolume = stomachCurrentVolume;
    }

    public double getStomachMaximumVolume() {
        return stomachMaxVolume;
    }

    public boolean isStomachFull(){
        return (Math.abs(stomachCurrentVolume- stomachMaxVolume)<0.0001);
    }
}
