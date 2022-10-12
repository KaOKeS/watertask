package com.akvelon.watertask.entity;

public abstract class Container {
    private final double maxVolume;
    private double currentVolume;

    protected Container(double maxVolume) {
        if(maxVolume<=0)
            throw new IllegalArgumentException("Maximum volume should be bigger than 0");
        this.maxVolume = maxVolume;
        this.currentVolume = maxVolume;
    }

    public double getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(double currentVolume) {
        if(currentVolume<0)
            throw new IllegalArgumentException("Volume should be bigger or equal 0");
        this.currentVolume = currentVolume;
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public boolean isContainerEmpty(){
        return currentVolume<0.0001;
    }
}
