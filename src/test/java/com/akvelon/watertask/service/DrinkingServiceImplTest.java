package com.akvelon.watertask.service;

import com.akvelon.watertask.entity.Bottle;
import com.akvelon.watertask.entity.Cat;
import com.akvelon.watertask.entity.Container;
import com.akvelon.watertask.entity.LivingBeeing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DrinkingServiceImplTest {
    DrinkService drinkingService = new DrinkService();

    @Test
    void checkIfAddingDrinkingPossibilitiesForLivingBeeingWorks(){
        LivingBeeing livingBeeing = new Cat(3.0);
        Container container = new Bottle(1.0);
        drinkingService.addDrinkRestriction(livingBeeing,container);
        Assertions.assertDoesNotThrow(()->drinkingService.drink(livingBeeing,container));
    }
}
