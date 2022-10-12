package com.akvelon.watertask.entity;

import com.akvelon.watertask.entity.contract.LivingBeeingFactory;
import org.springframework.stereotype.Component;

@Component
public class LivingBeeingFactoryImpl implements LivingBeeingFactory {
    @Override
    public LivingBeeing createLivingBeeing(String livingBeeingType, double stomachCapacity) {
        LivingBeeing livingBeeing = null;
        if(livingBeeingType.equalsIgnoreCase("human"))
            livingBeeing = new Human(stomachCapacity);
        else if(livingBeeingType.equalsIgnoreCase("cat"))
            livingBeeing = new Cat(stomachCapacity);
        return livingBeeing;
    }

    @Override
    public LivingBeeing createLivingBeeing(String livingBeeingType, double stomachCapacity, double stomachCurrentVolume) {
        LivingBeeing livingBeeing = null;
        if(livingBeeingType.equalsIgnoreCase("human"))
            livingBeeing = new Human(stomachCapacity,stomachCurrentVolume);
        else if(livingBeeingType.equalsIgnoreCase("cat"))
            livingBeeing = new Cat(stomachCapacity,stomachCurrentVolume);
        return livingBeeing;
    }
}
