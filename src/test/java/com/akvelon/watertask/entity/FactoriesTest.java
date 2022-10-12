package com.akvelon.watertask.entity;

import com.akvelon.watertask.entity.contract.ContainerFactory;
import com.akvelon.watertask.entity.contract.LivingBeeingFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactoriesTest {
    ContainerFactory containerFactory = new ContainerFactoryImpl();
    LivingBeeingFactory livingBeeingFactory = new LivingBeeingFactoryImpl();

    @Test
    void checkIfContainerFactoryReturnsBootleWhenCalledWithBottleString(){
        Assertions.assertTrue(containerFactory.createContainer("BoTTle",5) instanceof Bottle);
    }

    @Test
    void checkIfContainerFactoryReturnsBowlWhenCalledWithBottleString(){
        Assertions.assertTrue(containerFactory.createContainer("BowL",5) instanceof Bowl);
    }

    @Test
    void checkIfLivingBeeingFactoryReturnsHumanWhenCalledWithHumanString(){
        Assertions.assertTrue(livingBeeingFactory.createLivingBeeing("HumAn",5) instanceof Human);
        Assertions.assertTrue(livingBeeingFactory.createLivingBeeing("HumAn",5,0) instanceof Human);
    }

    @Test
    void checkIfLivingBeeingFactoryReturnsCatWhenCalledWithCatString(){
        Assertions.assertTrue(livingBeeingFactory.createLivingBeeing("CaT",5) instanceof Cat);
        Assertions.assertTrue(livingBeeingFactory.createLivingBeeing("CaT",5,0) instanceof Cat);
    }
}
