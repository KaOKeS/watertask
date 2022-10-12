package com.akvelon.watertask.entity;

public class Human extends LivingBeeing{
    public Human(double stomachMaximumVolume) {
        super(stomachMaximumVolume);
    }

    public Human(double stomachMaxVolume, double stomachCurrentVolume) {
        super(stomachMaxVolume, stomachCurrentVolume);
    }
}
