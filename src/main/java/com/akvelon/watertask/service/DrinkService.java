package com.akvelon.watertask.service;

import com.akvelon.watertask.entity.Container;
import com.akvelon.watertask.entity.LivingBeeing;
import org.springframework.stereotype.Service;

@Service
public class DrinkService implements Drink{
    @Override
    public void drink(LivingBeeing livingBeeing, Container container) {
        if(livingBeeing == null || container==null)
            throw new NullPointerException("Objects passed to drink() were null");
        if(livingBeeing.isStomachFull())
            throw new IllegalArgumentException("Stomach is already full.");
        else if(container.isContainerEmpty())
            throw new IllegalArgumentException("Container is already empty.");

        double freeStomachVolume = livingBeeing.getStomachMaximumVolume() - livingBeeing.getStomachCurrentVolume();
        double currentStomachVolume = livingBeeing.getStomachCurrentVolume();
        double containerCurrentVolume = container.getCurrentVolume();

        if(freeStomachVolume>containerCurrentVolume){
            livingBeeing.setStomachCurrentVolume(currentStomachVolume+containerCurrentVolume);
            container.setCurrentVolume(0);
        }
        else {
            container.setCurrentVolume(containerCurrentVolume-freeStomachVolume);
            livingBeeing.setStomachCurrentVolume(livingBeeing.getStomachMaximumVolume());
        }
    }
}
