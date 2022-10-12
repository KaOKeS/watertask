package com.akvelon.watertask.entity;

import com.akvelon.watertask.entity.contracts.LivingBeeingFactory;
import org.springframework.stereotype.Component;

@Component
public class LivingBeeingFactoryImpl implements LivingBeeingFactory {
    @Override
    public LivingBeeing createLivingBeeing(String type, double stomachCapacity) {
        LivingBeeing livingBeeing = null;
        if(type.equalsIgnoreCase("human"))
            livingBeeing = new Human(stomachCapacity);
        else if(type.equalsIgnoreCase("cat"))
            livingBeeing = new Cat(stomachCapacity);
        return livingBeeing;
    }
}
