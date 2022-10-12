package com.akvelon.watertask.service;

import com.akvelon.watertask.entity.Bottle;
import com.akvelon.watertask.entity.Container;
import com.akvelon.watertask.entity.Human;
import com.akvelon.watertask.entity.LivingBeeing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DrinkServiceTest {
    Drink drinkService = new DrinkService();

    @Test
    void nullObjectsPassedToDrinkMethodThrowsNullPointerException(){
        LivingBeeing human = null;
        Container bottle = null;
        Assertions.assertThrows(NullPointerException.class,() -> drinkService.drink(human,new Bottle(2.0)));
        Assertions.assertThrows(NullPointerException.class,() -> drinkService.drink(new Human(3.0),bottle));
    }

    @Test
    void emptyContainerThrowsExeption(){
        LivingBeeing human = new Human(3.5);
        Container bottle = new Bottle(2.0);
        bottle.setCurrentVolume(0);
        Assertions.assertThrows(IllegalArgumentException.class,() -> drinkService.drink(human,bottle));
    }

    @Test
    void fullStomachThrowsExeption(){
        LivingBeeing human = new Human(3.5);
        Container bottle = new Bottle(2.0);
        human.setStomachCurrentVolume(human.getStomachMaximumVolume());
        Assertions.assertThrows(IllegalArgumentException.class,() -> drinkService.drink(human,bottle));
    }

    @Test
    void livingBeeingHasEnoughSpaceInStomachToDrinkFullContainer(){
        LivingBeeing livingBeeing = new Human(3.0);
        Container container = new Bottle(2.0);
        drinkService.drink(livingBeeing,container);
        Assertions.assertTrue(container.isContainerEmpty());
        Assertions.assertEquals(livingBeeing.getStomachCurrentVolume(),2.0);
    }

    @Test
    void livingBeeingHasNotEnoughSpaceForFullContainerCapacity(){
        LivingBeeing livingBeeing = new Human(3.0);
        Container container = new Bottle(5.0);
        drinkService.drink(livingBeeing,container);
        Assertions.assertTrue(livingBeeing.isStomachFull());
        Assertions.assertEquals(container.getCurrentVolume(),2.0);
    }
}
