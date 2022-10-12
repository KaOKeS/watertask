package com.akvelon.watertask.entity;

import com.akvelon.watertask.entity.contracts.ContainerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContainerFactoryImpl implements ContainerFactory {

    @Override
    public Container createContainer(String type, double capacity) {
        Container container = null;
        if(type.equalsIgnoreCase("bowl"))
            container = new Bowl(capacity);
        else if(type.equalsIgnoreCase("bottle"))
            container = new Bottle(capacity);
        return container;
    }
}
