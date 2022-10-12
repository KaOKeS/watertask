package com.akvelon.watertask.entity.contracts;

import com.akvelon.watertask.entity.LivingBeeing;

public interface LivingBeeingFactory {
    LivingBeeing createLivingBeeing(String type,double stomachCapacity);
}
