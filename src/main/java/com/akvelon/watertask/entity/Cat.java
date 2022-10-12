package com.akvelon.watertask.entity;

public class Cat extends LivingBeeing{
    public Cat(double stomachMaximumVolume) {
        super(stomachMaximumVolume);
    }

    public Cat(double stomachMaxVolume, double stomachCurrentVolume) {
        super(stomachMaxVolume, stomachCurrentVolume);
    }
}
