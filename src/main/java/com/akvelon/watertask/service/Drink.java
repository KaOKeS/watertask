package com.akvelon.watertask.service;

import com.akvelon.watertask.entity.Container;
import com.akvelon.watertask.entity.LivingBeeing;

public interface Drink {
    void drink(LivingBeeing livingBeeing, Container container);
}
