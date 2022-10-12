package com.akvelon.watertask.service;

import com.akvelon.watertask.entity.Container;
import com.akvelon.watertask.entity.LivingBeeing;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DrinkService implements Drink{
    private Map<String, Set<String>> drinkingPossibilities = new HashMap<>();

    {
        drinkingPossibilities.put("human",new HashSet<>(Arrays.asList("bottle","bowl")));
        drinkingPossibilities.put("cat",new HashSet<>(Arrays.asList("bowl")));
    }

    @Override
    public void drink(LivingBeeing livingBeeing, Container container) {
        isLivingBeeingAbletoDrink(livingBeeing,container);

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

    public void addDrinkRestriction(LivingBeeing livingBeeing, Container container){
        String livingBeeingString = livingBeeing.getClass().getSimpleName().toLowerCase();
        String containerString = container.getClass().getSimpleName().toLowerCase();
        if(drinkingPossibilities.containsKey(livingBeeingString))
            drinkingPossibilities.get(livingBeeingString).add(containerString);
        else
            drinkingPossibilities.put(livingBeeingString,new HashSet(Arrays.asList(containerString)));
    }

    private boolean isLivingBeeingAbletoDrink(LivingBeeing livingBeeing, Container container){
        String livingBeeingString = livingBeeing.getClass().getSimpleName().toLowerCase();
        String containerString = container.getClass().getSimpleName().toLowerCase();
        if(livingBeeing == null || container==null)
            throw new NullPointerException("Objects passed to drink() were null");
        else if(livingBeeing.isStomachFull())
            throw new IllegalArgumentException("Stomach is already full.");
        else if(container.isContainerEmpty())
            throw new IllegalArgumentException("Container is already empty.");
        else if(drinkingPossibilities.containsKey(livingBeeingString) && !drinkingPossibilities.get(livingBeeingString).contains(containerString))
            throw new IllegalArgumentException(livingBeeingString + " can not drink from " + containerString);
        return true;
    }
}
