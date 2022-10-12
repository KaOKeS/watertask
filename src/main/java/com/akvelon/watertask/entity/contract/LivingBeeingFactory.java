package com.akvelon.watertask.entity.contract;

import com.akvelon.watertask.entity.LivingBeeing;

public interface LivingBeeingFactory {
    LivingBeeing createLivingBeeing(String livingBeeingType,double stomachCapacity);
    LivingBeeing createLivingBeeing(String livingBeeingType,double stomachCapacity,double currentStomachVolume);
}
