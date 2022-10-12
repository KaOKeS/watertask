package com.akvelon.watertask.entity;

import com.akvelon.watertask.entity.contract.ContainerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContainerFactoryImpl implements ContainerFactory {

    @Override
    public Container createContainer(String containerType, double capacity) {
        Container container = null;
        if(containerType.equalsIgnoreCase("bowl"))
            container = new Bowl(capacity);
        else if(containerType.equalsIgnoreCase("bottle"))
            container = new Bottle(capacity);
        return container;
    }
}
